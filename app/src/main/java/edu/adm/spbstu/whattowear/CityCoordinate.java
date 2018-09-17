package edu.adm.spbstu.whattowear;

import java.util.HashMap;

public class CityCoordinate {

    static final HashMap<String, String> map = new HashMap<>();
    static {
        map.put("Saint-Petersburg", "59.976665,30.320833");
        map.put("Moscow", "55.751244,37.618423");
        map.put("Kaliningrad", "54.715424,20.509207");
        map.put("Vladivistok", "43.10562,131.87353");
        map.put("Volgograd", "48.700001,44.516666");
        map.put("Samara", "53.241505,50.221245");
    }
}
