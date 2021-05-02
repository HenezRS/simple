package com.henez.simple.debug;

public class DebugFlags {
    private DebugFlags() {
    }

    public static boolean canEncounterWithSteps = true;
    public static boolean canEnemiesAct = true;

    //visual
    public static boolean drawBasicInfo = false;
    public static boolean drawPlayerAnimationInfo = false;
    public static boolean drawBattleQueues = false;

    public static float mulSkillNamePower = 1.0f;

    public static void override() {
        //canEnemiesAct = false;
        //canEncounterWithSteps = false;
        //drawPlayerAnimationInfo = true;
    }
}
