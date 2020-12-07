package com.henez.simple.atlas.imgs;

import lombok.Getter;

@Getter
public enum ImgTiles {
    none,
    floor,
    cave_wall_1,
    cave_wall_2,
    cave_wall_3,
    cave_wall_4,
    cave_wall_5,
    cave_wall_6,
    cave_spike_1,
    cave_spike_2,
    cave_ground_1,
    cave_ground_2,
    cave_ground_3,
    cave_ground_4,
    cave_mushroom_1,
    cave_mushroom_2,
    cave_mushroom_3,
    cave_remains_1,
    cave_eyes_1,
    cave_eyes_2,
    cave_background_wall_1,
    cave_background_spike_1,
    cave_background_spike_2,
    sack,
    sack_open,
    chest,
    chest_open,
    cave_bridge_1,
    cave_bridge_2,
    stairs_down,
    stairs_up,
    error,
    debug_y,
    debug_b,
    debug_g,
    debug_r,
    ;

    private final int x;
    private final int y;

    ImgTiles() {
        this.x = this.ordinal() % 16;
        this.y = this.ordinal() / 16;
    }
}
