package com.example.smdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

public class WorldClock extends AppCompatActivity {
    Date localDate = new Date();
    TimeZone userTimeZone;
    String[] selectedTimezones = new String[] {"Europe/Bucharest", "Europe/London", "Europe/Paris"};
    TimeZone selectedTimeZone;
    TextView convertedTimeTv;
    TextView convertedDateTv;
    ListView listView;
    ArrayAdapter<String> adapter;

    private static int CHOOSE_TIME_ZONE_REQUEST_CODE = 1;
    private static int SELECT_TIME_ZONES_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_clock);

        loadPreferences();


        convertedTimeTv = findViewById(R.id.convertedTime);
        convertedDateTv = findViewById(R.id.convertedDate);


        listView = findViewById(R.id.listView);
        setupAdapter();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedTimeZone = TimeZone.getTimeZone(selectedTimezones[i]);
                convertDate(userTimeZone, selectedTimeZone);
                savePreferences();
            }
        });


        if (selectedTimezones.length > 0 && selectedTimeZone != null) {
            int selectedTimeZonePosition = 0;
            for (int i = 0; i < this.selectedTimezones.length; i++) {
                if (selectedTimeZone.getID().equals(this.selectedTimezones[i])) {
                    selectedTimeZonePosition = i;
                    break;
                }
            }
            listView.setItemChecked(selectedTimeZonePosition, true);
            listView.setSelection(selectedTimeZonePosition);
            selectedTimeZone = TimeZone.getTimeZone(selectedTimezones[selectedTimeZonePosition]);
        }
        convertDate(userTimeZone, selectedTimeZone);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_TIME_ZONE_REQUEST_CODE && resultCode == RESULT_OK) {
            String timezone = data.getStringExtra("timezone");

            Date now = Calendar.getInstance().getTime();
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(now);
            userTimeZone = calendar.getTimeZone();//TimeZone.getTimeZone(timezone);
        }
        if (requestCode == SELECT_TIME_ZONES_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getBundleExtra("selectedTimezonesBundle");
            ArrayList<String> selectedTimezonesArrayList = bundle.getStringArrayList("selectedTimezones");
            Collections.sort(selectedTimezonesArrayList, new Comparator<String>() {
                @Override
                public int compare(String s, String t1) {
                    return s.compareToIgnoreCase(t1);
                }
            });
            selectedTimezones = new String[selectedTimezonesArrayList.size()];
            selectedTimezonesArrayList.toArray(selectedTimezones);
            setupAdapter();
        }
        convertDate(userTimeZone, selectedTimeZone);
        savePreferences();
    }

    private void convertDate(TimeZone fromTimeZone, TimeZone toTimeZone) {
        if (fromTimeZone != null && toTimeZone != null) {
            long fromOffset = fromTimeZone.getOffset(localDate.getTime());
            long toOffset = toTimeZone.getOffset(localDate.getTime());
            long convertedTime = localDate.getTime() - (fromOffset - toOffset);
            Date convertedDate = new Date(convertedTime);
            int hours = convertedDate.getHours();
//            hours -= 4;
//            if(hours < 0) {
//                hours = hours + 24;
//                convertedDate.setDate(convertedDate.getDate() - 1);
//            }
            int minutes = convertedDate.getMinutes();
            String time = (hours < 10 ? "0" + Integer.toString(hours) : Integer.toString(hours))
                    + ":" + (minutes < 10 ? "0" + Integer.toString(minutes) : Integer.toString(minutes));
            convertedTimeTv.setText(time);
            convertedDateTv.setText(DateFormat.getDateInstance().format(convertedDate));
        }
    }

    public void selectTimezones(View view) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("selectedTimezones", new ArrayList<String>(Arrays.asList(selectedTimezones)));
        Intent intent = new Intent(this, SelectTimezonesActivity.class);
        intent.putExtra("selectedTimezonesBundle", bundle);
        startActivityForResult(intent, 2);
    }

    private void setupAdapter() {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, android.R.id.text1, selectedTimezones);
        listView.setAdapter(adapter);
    }

    private void savePreferences() {
        SharedPreferences preferences = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if (userTimeZone != null) {
            editor.putString("userTimezone", userTimeZone.getID());
        }

        if (selectedTimeZone != null) {
            editor.putString("selectedTimezone", selectedTimeZone.getID());
        }

        editor.putStringSet("selectedTimezones", new HashSet<>(Arrays.asList(selectedTimezones)));

        editor.commit();
    }

    private void loadPreferences() {
        SharedPreferences preferences = this.getPreferences(Context.MODE_PRIVATE);

        String userTimezone = preferences.getString("userTimezone", TimeZone.getDefault().getID());
        this.userTimeZone = TimeZone.getTimeZone(userTimezone);

        Set<String> defaultSelectedTimezones = new HashSet<>(Arrays.asList(new String[] {"America/Los_Angeles", "America/New_York", "Asia/Hong_Kong", "Asia/Tokyo", "Europe/London", "Europe/Moscow", "Europe/Paris"}));
        Set<String> selectedTimezones = preferences.getStringSet("selectedTimezones", defaultSelectedTimezones);
        ArrayList<String> selectedTimezonesArrayList = new ArrayList<>(selectedTimezones);
        Collections.sort(selectedTimezonesArrayList, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return s.compareToIgnoreCase(t1);
            }
        });
        this.selectedTimezones = selectedTimezonesArrayList.toArray(new String[selectedTimezonesArrayList.size()]);

        String selectedTimezoneID = preferences.getString("selectedTimezone", null);
        if (selectedTimezoneID != null) {
            if (selectedTimezones.contains(selectedTimezoneID)) {
                this.selectedTimeZone = TimeZone.getTimeZone(selectedTimezoneID);
            }
        }
    }

    public void convertCurrentDate(View view) {
        localDate = new Date();
        convertDate(userTimeZone, selectedTimeZone);
    }
}
