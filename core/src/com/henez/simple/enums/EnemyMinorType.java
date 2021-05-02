package com.henez.simple.enums;

import com.henez.simple.atlas.imgs.ImgIcon7;
import lombok.Getter;

@Getter
public enum EnemyMinorType {
    DAMAGE(ImgIcon7.attack),
    SPELLS(ImgIcon7.mattack),
    HEALING(ImgIcon7.greenheart),
    BUFFING(ImgIcon7.rank),
    DEBUFFING(ImgIcon7.rankdown);

    private ImgIcon7 img;

    EnemyMinorType(ImgIcon7 img) {
        this.img = img;
    }
}
