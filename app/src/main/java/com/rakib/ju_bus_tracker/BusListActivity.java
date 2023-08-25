package com.rakib.ju_bus_tracker;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rakib.ju_bus_tracker.adapter.BusListAdapter;
import com.rakib.ju_bus_tracker.model.Bus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BusListActivity extends AppCompatActivity {
    private final List<Object> viewItems = new ArrayList<>();
    private static final String TAG = "BusListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);

        RecyclerView busListRecyclerView = findViewById(R.id.busListRecyclerView);
        busListRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        busListRecyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter = new BusListAdapter(this, viewItems);
        busListRecyclerView.setAdapter(mAdapter);

        addItemsFromJSON();
    }

    private void addItemsFromJSON() {
        try {
            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject itemObj = jsonArray.getJSONObject(i);
                String busNo = itemObj.getString("busNo");
                String regNo = itemObj.getString("regNo");
                String route = itemObj.getString("route");
                Bus bus = new Bus(busNo, regNo, route);

                viewItems.add(bus);
            }

        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }

    private String readJSONDataFromFile() throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {
            String jsonString;
            inputStream = getResources().openRawResource(R.raw.bus_list);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }
}
