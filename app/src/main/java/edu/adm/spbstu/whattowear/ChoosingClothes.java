package edu.adm.spbstu.whattowear;

import java.util.TreeMap;

public class ChoosingClothes {

    private boolean isWoman;
    private  int temperature;

    ChoosingClothes(boolean isWoman, int temperature) {
        this.isWoman = isWoman;
        this.temperature = temperature;
    }

    static final TreeMap<PrecipType, Clothes[]> map = new TreeMap<>();
    static {
        map.put(PrecipType.RAIN, new Clothes[]{Clothes.boots, Clothes.umbrella, Clothes.girl_10_20, Clothes.boy_10_20});
    }
}
