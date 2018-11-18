package edu.adm.spbstu.whattowear;

import android.content.Context;
import android.content.res.Resources;

import java.util.TreeMap;

public class ChoosingClothes {

    private static final TreeMap<Temperatures, Clothes[]> clothesMap = new TreeMap<>();
    private  static final TreeMap<Temperatures, String[]> labelMap = new TreeMap<>();

    Clothes[] getClothes(Temperatures temperature) {
        return clothesMap.get(temperature);
    }

    String[] getClothesLabels(Temperatures temperature) {
        return labelMap.get(temperature);
    }

    ChoosingClothes(boolean isWoman, PrecipType precip, Context context) {

        String shorts = context.getString(R.string.shorts);
        String tShirt = context.getString(R.string.tshirt);
        String umbrella = context.getString(R.string.umbrel);
        String trousers = context.getString(R.string.trousers);
        String boots = context.getString(R.string.boots);
        String hat = context.getString(R.string.hat);
        String jacket = context.getString(R.string.jacket);
        String cap = context.getString(R.string.cap);
        String sandals = context.getString(R.string.sandals);
        String jumper = context.getString(R.string.jumper);
        String sneakers = context.getString(R.string.sneakers);
        String summerShoes = context.getString(R.string.summerShoes);

        if (isWoman) {
            switch (precip) {
                case RAIN:
                    clothesMap.put(Temperatures.more_then_twenty_five, new Clothes[]{Clothes.girl_more_20, Clothes.umbrella});
                    clothesMap.put(Temperatures.more_then_twenty, new Clothes[]{Clothes.girl_more_20, Clothes.umbrella});
                    clothesMap.put(Temperatures.from_ten_to_twenty, new Clothes[]{Clothes.girl_10_20, Clothes.boots, Clothes.umbrella});
                    clothesMap.put(Temperatures.from_zero_to_ten, new Clothes[]{Clothes.girl_0_10, Clothes.boots, Clothes.umbrella});
                    clothesMap.put(Temperatures.less_than_zero, new Clothes[]{Clothes.girl_less_0, Clothes.boots, Clothes.umbrella});

                    labelMap.put(Temperatures.more_then_twenty_five, new String[]{shorts, tShirt, umbrella});
                    labelMap.put(Temperatures.more_then_twenty, new String[]{shorts, tShirt, umbrella});
                    labelMap.put(Temperatures.from_ten_to_twenty, new String[]{trousers, boots, umbrella});
                    labelMap.put(Temperatures.from_zero_to_ten, new String[]{jacket, boots, umbrella});
                    labelMap.put(Temperatures.less_than_zero, new String[]{hat, boots, umbrella});
                    break;

                case NONE:
                case SNOW:
                    clothesMap.put(Temperatures.more_then_twenty_five, new Clothes[]{Clothes.girl_more_20, Clothes.cap_girl});
                    clothesMap.put(Temperatures.more_then_twenty, new Clothes[]{Clothes.girl_more_20});
                    clothesMap.put(Temperatures.from_ten_to_twenty, new Clothes[]{Clothes.girl_10_20});
                    clothesMap.put(Temperatures.from_zero_to_ten, new Clothes[]{Clothes.girl_0_10});
                    clothesMap.put(Temperatures.less_than_zero, new Clothes[]{Clothes.girl_less_0});

                    labelMap.put(Temperatures.more_then_twenty_five, new String[]{shorts, tShirt, cap});
                    labelMap.put(Temperatures.more_then_twenty, new String[]{shorts, tShirt, sandals});
                    labelMap.put(Temperatures.from_ten_to_twenty, new String[]{trousers, jumper, sneakers});
                    labelMap.put(Temperatures.from_zero_to_ten, new String[]{jacket, trousers, sneakers});
                    labelMap.put(Temperatures.less_than_zero, new String[]{hat, jacket, boots});
                    break;
            }
        } else { // is man
            switch (precip) {
                case RAIN:
                    clothesMap.put(Temperatures.more_then_twenty_five, new Clothes[]{Clothes.boy_more_20, Clothes.umbrella});
                    clothesMap.put(Temperatures.more_then_twenty, new Clothes[]{Clothes.boy_more_20, Clothes.umbrella});
                    clothesMap.put(Temperatures.from_ten_to_twenty, new Clothes[]{Clothes.boy_10_20, Clothes.boots, Clothes.umbrella});
                    clothesMap.put(Temperatures.from_zero_to_ten, new Clothes[]{Clothes.boy_0_10, Clothes.boots, Clothes.umbrella});
                    clothesMap.put(Temperatures.less_than_zero, new Clothes[]{Clothes.boy_less_0, Clothes.boots, Clothes.umbrella});

                    labelMap.put(Temperatures.more_then_twenty_five, new String[]{shorts, tShirt, umbrella});
                    labelMap.put(Temperatures.more_then_twenty, new String[]{shorts, tShirt, umbrella});
                    labelMap.put(Temperatures.from_ten_to_twenty, new String[]{trousers, boots, umbrella});
                    labelMap.put(Temperatures.from_zero_to_ten, new String[]{jacket, boots, umbrella});
                    labelMap.put(Temperatures.less_than_zero, new String[]{hat, boots, umbrella});
                    break;

                case NONE:
                case SNOW:
                    clothesMap.put(Temperatures.more_then_twenty_five, new Clothes[]{Clothes.boy_more_20, Clothes.cap_boy});
                    clothesMap.put(Temperatures.more_then_twenty, new Clothes[]{Clothes.boy_more_20});
                    clothesMap.put(Temperatures.from_ten_to_twenty, new Clothes[]{Clothes.boy_10_20});
                    clothesMap.put(Temperatures.from_zero_to_ten, new Clothes[]{Clothes.boy_0_10});
                    clothesMap.put(Temperatures.less_than_zero, new Clothes[]{Clothes.boy_less_0});

                    labelMap.put(Temperatures.more_then_twenty_five, new String[]{shorts, tShirt, cap});
                    labelMap.put(Temperatures.more_then_twenty, new String[]{shorts, tShirt, summerShoes});
                    labelMap.put(Temperatures.from_ten_to_twenty, new String[]{trousers, jumper, sneakers});
                    labelMap.put(Temperatures.from_zero_to_ten, new String[]{jacket, trousers, sneakers});
                    labelMap.put(Temperatures.less_than_zero, new String[]{hat, jacket, boots});
                    break;
            }
        }
    }
}
