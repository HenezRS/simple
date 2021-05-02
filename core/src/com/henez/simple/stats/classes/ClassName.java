package com.henez.simple.stats.classes;

import com.henez.simple.atlas.imgset.ImgSetFighters;
import com.henez.simple.enums.EnemyMinorType;
import com.henez.simple.enums.EnemyRank;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ClassName {
    kni("kni", "knight", ImgSetFighters.class_kni),
    ran("ran", "ranger", ImgSetFighters.class_ran),
    mag("mag", "mage", ImgSetFighters.class_mag),
    cle("cle", "cleric", ImgSetFighters.class_cle),
    rog("rog", "rogue", ImgSetFighters.class_rog),
    tri("tri", "trickster", ImgSetFighters.class_tri),
    bru("bru", "brute", ImgSetFighters.class_bru),
    foo("foo", "fool", ImgSetFighters.class_foo),
    dru("dru", "druid", ImgSetFighters.class_dru),
    cul("cul", "cultist", ImgSetFighters.class_cul),

    enemy_octo("en", "octo", ImgSetFighters.enemy_octo),
    enemy_octoMinor("en", "octoMinor", ImgSetFighters.enemy_octoMinor, EnemyMinorType.DAMAGE),
    enemy_octo3("en", "octo3", ImgSetFighters.enemy_octo3),
    enemy_octo4("en", "octo4", ImgSetFighters.enemy_octo4),
    ;

    private String nameShort;
    private String name;
    private ImgSetFighters imgSet;
    private EnemyRank enemyRank;
    private EnemyMinorType enemyMinorType;

    ClassName(String nameShort, String name, ImgSetFighters imgSet) {
        this.nameShort = nameShort.toUpperCase();
        this.name = name;
        this.imgSet = imgSet;
        this.enemyRank = EnemyRank.MAJOR;
    }

    ClassName(String nameShort, String name, ImgSetFighters imgSet, EnemyMinorType enemyMinorType) {
        this.nameShort = nameShort.toUpperCase();
        this.name = name;
        this.imgSet = imgSet;
        this.enemyRank = EnemyRank.MINOR;
        this.enemyMinorType = enemyMinorType;
    }

    public static ClassName getByName(String name) {
        return Arrays.stream(ClassName.values()).filter(className -> className.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public boolean isMinor() {
        return enemyRank == EnemyRank.MINOR;
    }
}
