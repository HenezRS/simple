package com.henez.simple.world.battle;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.enums.Facing;
import com.henez.simple.misc.XY;
import com.henez.simple.misc.timer.Timer;
import com.henez.simple.skills.SkillTargetBuilder;
import com.henez.simple.utils.FighterUtils;
import com.henez.simple.world.mapobjects.Fighter;
import com.henez.simple.world.mapobjects.FighterState;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Getter
public class BattleMembers {
    private GameList<Fighter> playerParty;
    private GameList<Fighter> enemyParty;
    private Map<XY, Fighter> enemyPartyMap;
    private GameList<Fighter> fighters;
    private GameList<Fighter> fightersShuffled;
    private GameList<Fighter> fightersWaiting;
    private GameList<Fighter> fightersCasting;
    private GameList<Fighter> fightersExecuting;
    private GameList<Fighter> fightersChannelling;
    private int cur = 0;

    Timer timer;

    public BattleMembers(GameList<Fighter> playerParty, GameList<Fighter> enemyParty) {
        this.playerParty = playerParty;
        this.enemyParty = enemyParty;

        enemyPartyMap = FighterUtils.createFighterMap(this.enemyParty);

        fighters = new GameList<>();
        fighters.addAll(playerParty);
        fighters.addAll(enemyParty);

        fightersShuffled = new GameList<>();
        fightersShuffled.addAll(fighters);
        Collections.shuffle(fightersShuffled);

        fightersWaiting = new GameList<>();
        fightersCasting = new GameList<>();
        fightersExecuting = new GameList<>();
        fightersChannelling = new GameList<>();

        AtomicInteger pos = new AtomicInteger();
        fightersShuffled.forEach(f -> f.battleStart(pos.getAndIncrement(), fighters.size()));

        setFighterFacing();

        timer = new Timer(60 / 4);
    }

    public boolean playerWin() {
        return enemyParty.stream().allMatch(Fighter::isDead);
    }

    public boolean enemyWin() {
        return playerParty.stream().allMatch(Fighter::isDead);
    }

    public void processTurn() {
        processFightersWaiting();
        processFightersCasting();
        processFightersChannelling();
        processFightersExecuting();

        /*if (timer.update()) {
            timer.reset();
            fighters.get(cur).getSprite().getSpriteEffectManager().createBlink(Colors.white.color);
            cur++;
            if (cur >= fighters.size()) {
                cur = 0;
            }
        }*/
    }

    private void processFightersWaiting() {
        populateFightersWaiting();
        tickFightersWaiting();
    }

    private void processFightersCasting() {
        populateFightersCasting();
        tickFightersCasting();
    }

    private void processFightersExecuting() {
        populateFightersExecuting();
        tickFightersExecuting();
    }

    private void processFightersChannelling() {
        populateFightersChannelling();
        tickFightersChannelling();
    }

    private void populateFightersWaiting() {
        fightersWaiting = fighters.stream().filter(fighter -> fighter.canAct() && fighter.getFighterState() == FighterState.WAITING).collect(Collectors.toCollection(GameList::new));
    }

    private void populateFightersCasting() {
        fightersCasting = fighters.stream().filter(fighter -> fighter.canAct() && fighter.getFighterState() == FighterState.CASTING).collect(Collectors.toCollection(GameList::new));
    }

    private void populateFightersExecuting() {
        fightersExecuting = fighters.stream().filter(fighter -> fighter.canAct() && fighter.getFighterState() == FighterState.EXECUTING).collect(Collectors.toCollection(GameList::new));
    }

    private void populateFightersChannelling() {
        fightersChannelling = fighters.stream().filter(fighter -> fighter.canAct() && fighter.getFighterState() == FighterState.CHANNELLING).collect(Collectors.toCollection(GameList::new));
    }

    public void tickFightersWaiting() {
        fightersWaiting.forEach(fighter -> {
            fighter.battleUpdate();
            if (fighter.readyToAct()) {
                fighter.determineSkillCast(target(fighter));
            }
        });
    }

    public void tickFightersCasting() {
        fightersCasting.forEach(fighter -> {
            fighter.castingUpdate();
            if (fighter.getCast().isDone()) {
                fighter.skillBeginCastExecution();
            }
        });
    }

    public void tickFightersChannelling() {
        fightersChannelling.forEach(fighter -> {
            fighter.castingUpdate();
            fighter.executionUpdate();
            if (fighter.getCast().isChannelCastReady()) {
                fighter.skillBeginChannelExecution();
                fighter.cancelChannelIfTargetNoLongerValid();
            } else if (fighter.getCast().isDone()) {
                fighter.skillBeginCastExecution();
            }
        });
    }

    public void tickFightersExecuting() {
        fightersExecuting.forEach(fighter -> {
            fighter.executionUpdate();
            if (fighter.getSkillExecution().isDone()) {
                fighter.turnEnd();
            }
        });
    }

    private void setFighterFacing() {
        int playerX = playerParty.last().getGx();
        int enemyX = enemyParty.last().getGx();

        playerParty.forEach(f -> f.setFacing2(Facing.byDir2(f.getGx(), enemyX)));
        enemyParty.forEach(f -> f.setFacing2(Facing.byDir2(f.getGx(), playerX)));
    }

    private SkillTargetBuilder target(Fighter fighterActing) {
        return new SkillTargetBuilder(fighterActing, fighters);
    }
}
