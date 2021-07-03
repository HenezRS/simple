package com.henez.simple.utils;

import com.henez.simple.datastructures.GameList;
import com.henez.simple.datastructures.Rect;
import com.henez.simple.global.Global;
import com.henez.simple.misc.XY;
import com.henez.simple.world.mapobjects.Fighter;

import java.util.HashMap;
import java.util.Map;

public final class FighterUtils {
    private FighterUtils() {
    }

    public static Map<XY, Fighter> createFighterMap(GameList<Fighter> party) {
        Map<XY, Fighter> fighterPartyMap = new HashMap<>();
        party.forEach(p -> {
            fighterPartyMap.put(new XY(p.getGx(), p.getGy()), p);
            if (p.isLarge()) {
                fighterPartyMap.put(new XY(p.getGx() + 1, p.getGy()), p);
                fighterPartyMap.put(new XY(p.getGx() + 1, p.getGy() + 1), p);
                fighterPartyMap.put(new XY(p.getGx(), p.getGy() + 1), p);
            }
        });
        return fighterPartyMap;
    }

    public static Rect getPartyMapRect(Map<XY, Fighter> fighterPartyMap) {
        Rect rect = new Rect(9999, 9999, 0, 0);
        fighterPartyMap.forEach((xy, fighter) -> {
            if (xy.getX() < rect.x) {
                rect.x = xy.getX();
            }
            if (xy.getY() < rect.y) {
                rect.y = xy.getY();
            }
            if (xy.getX() > rect.width) {
                rect.width = xy.getX();
            }
            if (xy.getY() > rect.height) {
                rect.height = xy.getY();
            }

        });
        rect.width++;
        rect.height++;
        rect.width = (int) (rect.width - rect.x) * Global.tilePixelSize;
        rect.height = (int) (rect.height - rect.y) * Global.tilePixelSize;
        rect.x = rect.x * Global.tilePixelSize;
        rect.y = rect.y * Global.tilePixelSize;
        return rect;
    }
}
