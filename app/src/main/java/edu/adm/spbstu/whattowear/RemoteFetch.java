package edu.adm.spbstu.whattowear;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;

public class RemoteFetch extends AsyncTask<String, Integer, String> {

    private static final String API_BASE_PATH = "https://api.darksky.net/forecast/";
    private static final String API_KEY = "a51089b1b0c72c20811d13bd44f4af3d";

    private Context context;
    private JSONObject data;

    RemoteFetch(Context context) {
        this.context = context;
    }

    JSONObject getJson() {
        return data;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL("https://api.darksky.net/forecast/a51089b1b0c72c20811d13bd44f4af3d/60,30");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("api_key", API_KEY);
            connection.setRequestProperty("base_path", API_BASE_PATH);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder json = new StringBuilder(1024);

            String tmp;
            while((tmp = reader.readLine()) != null)
                json.append(tmp).append("\n");
            reader.close();

            data = new JSONObject(json.toString());
            return "Weather was updated";
        } catch(Exception e) {
            return null;
        }
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}