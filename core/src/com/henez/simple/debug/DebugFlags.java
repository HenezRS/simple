package com.henez.simple.debug;

import com.henez.simple.world.enemies.EnemyPartyName;

public class DebugFlags {
    private DebugFlags() {
    }

    public static boolean canEncounterWithSteps = true;
    public static boolean canEnemiesAct = true;
    public static boolean canPlayersAct = true;
    public static boolean canNonLeadersAct = true;
    public static EnemyPartyName debugEncounter = EnemyPartyName.octoBig;

    //visual
    public static boolean drawBasicInfo = false;
    public static boolean drawPlayerAnimationInfo = false;
    public static boolean drawBattleQueues = false;
    public static boolean drawEnemyPartyMap = false;

    public static float mulSkillNamePowerPlayer = 1.0f;
    public static float mulSkillNamePowerEnemy = 1.0f;
    public static float mulBattleSpeed = 1.0f;

    //cheats
    public static boolean canLeftClickToTeleport = false;

    public static void override() {
        //canNonLeadersAct = false;
        //canEnemiesAct = false;
        //canPlayersAct = false;
        //canEncounterWithSteps = false;
        //drawPlayerAnimationInfo = true;
        drawEnemyPartyMap = true;
        mulSkillNamePowerPlayer = 0.1f;
        mulSkillNamePowerEnemy = 0.1f;
        mulBattleSpeed = 1.0f;
    }
}
