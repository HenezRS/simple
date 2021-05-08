package com.henez.simple.drawer;

import com.henez.simple.drawer.battlecontrols.BattleControlDrawer;
import com.henez.simple.drawer.fighterpanel.FighterPanelDrawer;
import com.henez.simple.renderer.Batcher;
import com.henez.simple.renderer.Shaper;
import com.henez.simple.world.World;

public class BattleDrawer {
    private World world;
    private FighterPanelDrawer fighterPanelDrawer;
    private BattleControlDrawer battleControlDrawer;

    public BattleDrawer(World world) {
        this.world = world;
        fighterPanelDrawer = new FighterPanelDrawer(world);
        battleControlDrawer = new BattleControlDrawer();
    }

    public void drawBattleActorsExecuting(Batcher batch) {
        world.getBattle().getBattleMembers().getFightersExecuting().forEach(f -> f.getSkillExecution().draw(batch));
        world.getBattle().getBattleMembers().getFightersChannelling().forEach(f -> {
            if (f.getSkillExecution().isExecuting()) {
                f.getSkillExecution().draw(batch);
            }
        });
    }

    public void drawUnderActorsBatch(Batcher batch) {
        battleControlDrawer.drawTargetBatch(world, batch);
    }

    public void drawUnderActorsShape(Shaper shape) {
        battleControlDrawer.drawTargetShape(world, shape);
    }

    public void drawUiBatch(Batcher batch) {
        fighterPanelDrawer.drawPanelsBatch(batch);
        battleControlDrawer.drawBatch(world, batch);
    }

    public void drawUiShape(Shaper shape) {
        fighterPanelDrawer.drawPanelsShape(shape);
        battleControlDrawer.drawShape(world, shape);
    }
}
