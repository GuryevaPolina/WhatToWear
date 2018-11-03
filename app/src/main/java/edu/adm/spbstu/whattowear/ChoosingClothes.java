package edu.adm.spbstu.whattowear;

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

    ChoosingClothes(boolean isWoman, PrecipType precip) {
        if (isWoman) {
            switch (precip) {
                case RAIN:
                    clothesMap.put(Temperatures.more_then_twenty_five, new Clothes[]{Clothes.girl_more_20, Clothes.umbrella});
                    clothesMap.put(Temperatures.more_then_twenty, new Clothes[]{Clothes.girl_more_20, Clothes.umbrella});
                    clothesMap.put(Temperatures.from_ten_to_twenty, new Clothes[]{Clothes.girl_10_20, Clothes.boots, Clothes.umbrella});
                    clothesMap.put(Temperatures.from_zero_to_ten, new Clothes[]{Clothes.girl_0_10, Clothes.boots, Clothes.umbrella});
                    clothesMap.put(Temperatures.less_than_zero, new Clothes[]{Clothes.girl_less_0, Clothes.boots, Clothes.umbrella});

                    labelMap.put(Temperatures.more_then_twenty_five, new String[]{"Шорты", "Футболка", "Зонт"});
                    labelMap.put(Temperatures.more_then_twenty, new String[]{"Шорты", "Футболка", "Зонт"});
                    labelMap.put(Temperatures.from_ten_to_twenty, new String[]{"Брюки", "Сапоги", "Зонт"});
                    labelMap.put(Temperatures.from_zero_to_ten, new String[]{"Куртка", "Сапоги", "Зонт"});
                    labelMap.put(Temperatures.less_than_zero, new String[]{"Шапка", "Сапоги", "Зонт"});
                    break;

                case NONE:
                case SNOW:
                    clothesMap.put(Temperatures.more_then_twenty_five, new Clothes[]{Clothes.girl_more_20, Clothes.cap_girl});
                    clothesMap.put(Temperatures.more_then_twenty, new Clothes[]{Clothes.girl_more_20});
                    clothesMap.put(Temperatures.from_ten_to_twenty, new Clothes[]{Clothes.girl_10_20});
                    clothesMap.put(Temperatures.from_zero_to_ten, new Clothes[]{Clothes.girl_0_10});
                    clothesMap.put(Temperatures.less_than_zero, new Clothes[]{Clothes.girl_less_0});

                    labelMap.put(Temperatures.more_then_twenty_five, new String[]{"Шорты", "Футболка", "Кепка"});
                    labelMap.put(Temperatures.more_then_twenty, new String[]{"Шорты", "Футболка", "Босоножки"});
                    labelMap.put(Temperatures.from_ten_to_twenty, new String[]{"Брюки", "Водолазка", "Кроссовки"});
                    labelMap.put(Temperatures.from_zero_to_ten, new String[]{"Куртка", "Брюки", "Кроссовки"});
                    labelMap.put(Temperatures.less_than_zero, new String[]{"Шапка", "Куртка", "Теплая обувь"});
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

                    labelMap.put(Temperatures.more_then_twenty_five, new String[]{"Шорты", "Футболка", "Зонт"});
                    labelMap.put(Temperatures.more_then_twenty, new String[]{"Шорты", "Футболка", "Зонт"});
                    labelMap.put(Temperatures.from_ten_to_twenty, new String[]{"Брюки", "Сапоги", "Зонт"});
                    labelMap.put(Temperatures.from_zero_to_ten, new String[]{"Куртка", "Сапоги", "Зонт"});
                    labelMap.put(Temperatures.less_than_zero, new String[]{"Шапка", "Сапоги", "Зонт"});
                    break;

                case NONE:
                case SNOW:
                    clothesMap.put(Temperatures.more_then_twenty_five, new Clothes[]{Clothes.boy_more_20, Clothes.cap_boy});
                    clothesMap.put(Temperatures.more_then_twenty, new Clothes[]{Clothes.boy_more_20});
                    clothesMap.put(Temperatures.from_ten_to_twenty, new Clothes[]{Clothes.boy_10_20});
                    clothesMap.put(Temperatures.from_zero_to_ten, new Clothes[]{Clothes.boy_0_10});
                    clothesMap.put(Temperatures.less_than_zero, new Clothes[]{Clothes.boy_less_0});

                    labelMap.put(Temperatures.more_then_twenty_five, new String[]{"Шорты", "Футболка", "Кепка"});
                    labelMap.put(Temperatures.more_then_twenty, new String[]{"Шорты", "Футболка", "Летняя обувь"});
                    labelMap.put(Temperatures.from_ten_to_twenty, new String[]{"Брюки", "Водолазка", "Кроссовки"});
                    labelMap.put(Temperatures.from_zero_to_ten, new String[]{"Куртка", "Брюки", "Кроссовки"});
                    labelMap.put(Temperatures.less_than_zero, new String[]{"Шапка", "Куртка", "Теплая обувь"});
                    break;
            }
        }
    }
}
