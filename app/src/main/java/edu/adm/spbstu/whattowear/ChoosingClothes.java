package edu.adm.spbstu.whattowear;

import java.util.TreeMap;

public class ChoosingClothes {

    private static final TreeMap<Temperatures, Clothes[]> map = new TreeMap<>();

    TreeMap<Temperatures, Clothes[]> getMap() {
        return map;
    }

    ChoosingClothes(boolean isWoman, PrecipType precip) {
        if (isWoman) {
            switch (precip) {
                case RAIN:
                    map.put(Temperatures.more_then_twenty, new Clothes[]{Clothes.girl_more_20, Clothes.umbrella});
                    map.put(Temperatures.from_ten_to_twenty, new Clothes[]{Clothes.girl_10_20, Clothes.boots, Clothes.umbrella});
                    map.put(Temperatures.from_zero_to_ten, new Clothes[]{Clothes.girl_0_10, Clothes.boots, Clothes.umbrella});
                    map.put(Temperatures.less_than_zero, new Clothes[]{Clothes.girl_less_0, Clothes.boots, Clothes.umbrella});
                    break;

                case NONE:
                    map.put(Temperatures.more_then_twenty, new Clothes[]{Clothes.girl_more_20});
                    map.put(Temperatures.from_ten_to_twenty, new Clothes[]{Clothes.girl_10_20});
                    map.put(Temperatures.from_zero_to_ten, new Clothes[]{Clothes.girl_0_10});
                    map.put(Temperatures.less_than_zero, new Clothes[]{Clothes.girl_less_0});
                    break;
            }
        } else { // is man
            switch (precip) {
                case RAIN:
                    map.put(Temperatures.more_then_twenty, new Clothes[]{Clothes.boy_more_20, Clothes.umbrella});
                    map.put(Temperatures.from_ten_to_twenty, new Clothes[]{Clothes.boy_10_20, Clothes.boots, Clothes.umbrella});
                    map.put(Temperatures.from_zero_to_ten, new Clothes[]{Clothes.boy_0_10, Clothes.boots, Clothes.umbrella});
                    map.put(Temperatures.less_than_zero, new Clothes[]{Clothes.boy_less_0, Clothes.boots, Clothes.umbrella});
                    break;

                case NONE:
                    map.put(Temperatures.more_then_twenty, new Clothes[]{Clothes.boy_more_20});
                    map.put(Temperatures.from_ten_to_twenty, new Clothes[]{Clothes.boy_10_20});
                    map.put(Temperatures.from_zero_to_ten, new Clothes[]{Clothes.boy_0_10});
                    map.put(Temperatures.less_than_zero, new Clothes[]{Clothes.boy_less_0});
                    break;
            }
        }
    }

    void updateClothes() {

    }
}
