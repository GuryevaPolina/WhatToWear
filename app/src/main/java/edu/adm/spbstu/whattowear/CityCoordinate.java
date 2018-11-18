package edu.adm.spbstu.whattowear;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class CityCoordinate {

    private TreeMap<String, String> map;

    CityCoordinate(Context context) {
        map = new TreeMap<>();
        map.put(context.getString(R.string.saintPetersburg), "59.976665,30.320833");
        map.put(context.getString(R.string.moscow), "55.751244,37.618423");
        map.put(context.getString(R.string.kaliningrad), "54.715424,20.509207");
        map.put(context.getString(R.string.vladivostok), "43.10562,131.87353");
        map.put(context.getString(R.string.volgograd), "48.700001,44.516666");
        map.put(context.getString(R.string.samara), "53.241505,50.221245");
        map.put(context.getString(R.string.murmansk), "68.969563,33.074540");
        map.put(context.getString(R.string.arkhangelsk), "64.555193,40.630101");
        map.put(context.getString(R.string.belgorod), "50.593680,36.584136");
        map.put(context.getString(R.string.voronezh), "51.656106,39.206133");
        map.put(context.getString(R.string.ivanovo), "56.999493,40.977977");
        map.put(context.getString(R.string.irkutsk), "52.292256,104.293919");
        map.put(context.getString(R.string.kamchatka), "53.026077,158.647087");
        map.put(context.getString(R.string.krasnodar), "45.047590,38.984455");
        map.put(context.getString(R.string.murmansk), "59.565070,150.821720");
        map.put(context.getString(R.string.novosibirsk), "55.023783,82.925027");
        map.put(context.getString(R.string.orenburg), "51.774631,55.104433");
        map.put(context.getString(R.string.perm), "58.019807,56.261747");
        map.put(context.getString(R.string.kislovodsk), "43.908099,42.724726");
        map.put(context.getString(R.string.khabarovsk), "48.505690,135.078489");
        map.put(context.getString(R.string.chelyabinsk), "55.173328,61.414222");
    }

    String getCoordinate(String city) {
        return map.get(city);
    }

    ArrayList<String> getCities() {
        return  new ArrayList<>(map.keySet());
    }
}
