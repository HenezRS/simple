package com.henez.simple.debug;

public class DebugFlags {
    private DebugFlags() {
    }

    public static boolean canEncounterWithSteps = true;
    public static boolean canEnemiesAct = true;
    public static boolean canPlayersAct = true;
    public static boolean canNonLeadersAct = true;

    //visual
    public static boolean drawBasicInfo = false;
    public static boolean drawPlayerAnimationInfo = false;
    public static boolean drawBattleQueues = false;

    public static float mulSkillNamePower = 1.0f;

    //cheats
    public static boolean canLeftClickToTeleport = false;

    public static void override() {
        //canNonLeadersAct = false;
        //canEnemiesAct = false;
        //canPlayersAct = false;
        //canEncounterWithSteps = false;
        //drawPlayerAnimationInfo = true;
    }
}
