package ru.arnis.izum;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

public class Settings extends AppCompatActivity {

    public static final String SETTINGS_DATA = "settings";

    public static final String SETTINGS_AUTOSCROLL = "autoscroll";
    public static final String SETTINGS_AUTOSCROLL_INTERVAL = "autoscroll_interval";
    public static final String SETTINGS_ANIM = "animation";
    public static final String SETTINGS_SHOW_FAVOURITES = "show_favourites";
    public static final String SETTINGS_SHOW_RANDOM = "show_random";

    public static final boolean DEFAULT_AUTOSCROLL = false;
    public static final int DEFAULT_AUTOSCROLL_INTERVAL =2000;
    public static final int DEFAULT_ANIM = 0;
    public static final boolean DEFAULT_SHOW_FAVOURITES = false;
    public static final boolean DEFAULT_SHOW_RANDOM = false;


    private ToggleButton toggleAutoScroll;
    private EditText autoScrollInterval;
    private Spinner animationPicker;
    private ToggleButton onlyFavourites;
    private ToggleButton showRandom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toggleAutoScroll = (ToggleButton)findViewById(R.id.auto_scroll);
        autoScrollInterval = (EditText)findViewById(R.id.auto_scroll_interval);
        animationPicker = (Spinner)findViewById(R.id.animation_picker);
        onlyFavourites = (ToggleButton)findViewById(R.id.only_favourite);
        showRandom = (ToggleButton)findViewById(R.id.show_random);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.animations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        animationPicker.setAdapter(adapter);

    }

    @Override
    protected void onPause() {
        saveSettings();
        super.onPause();
    }


    @Override
    protected void onResume() {
        loadSettings();
        super.onResume();
    }
    //сохраняем настройки
    private void saveSettings() {
        SharedPreferences.Editor editor = getSharedPreferences(SETTINGS_DATA,MODE_PRIVATE).edit();
        editor.putBoolean(SETTINGS_AUTOSCROLL,toggleAutoScroll.isChecked());
        String intervalS = autoScrollInterval.getText().toString();
        int interval;
        if (intervalS.equals("")) //проверка если пользователь не ввел интервал
            interval=DEFAULT_AUTOSCROLL_INTERVAL;
        else interval = Integer.parseInt(intervalS)*1000;
        editor.putInt(SETTINGS_AUTOSCROLL_INTERVAL,interval);
        editor.putInt(SETTINGS_ANIM,animationPicker.getSelectedItemPosition());
        editor.putBoolean(SETTINGS_SHOW_FAVOURITES,onlyFavourites.isChecked());
        editor.putBoolean(SETTINGS_SHOW_RANDOM,showRandom.isChecked());
        editor.apply();
    }
    //загружаем настройки
    private void loadSettings() {
        SharedPreferences prefs = getSharedPreferences(SETTINGS_DATA,MODE_PRIVATE);
        toggleAutoScroll.setChecked(prefs.getBoolean(SETTINGS_AUTOSCROLL,DEFAULT_AUTOSCROLL));
        autoScrollInterval.setText(Integer.toString(prefs.getInt(SETTINGS_AUTOSCROLL_INTERVAL, DEFAULT_AUTOSCROLL_INTERVAL)/1000));
        animationPicker.setSelection(prefs.getInt(SETTINGS_ANIM,DEFAULT_ANIM));
        onlyFavourites.setChecked(prefs.getBoolean(SETTINGS_SHOW_FAVOURITES,DEFAULT_SHOW_FAVOURITES));
        showRandom.setChecked(prefs.getBoolean(SETTINGS_SHOW_RANDOM,DEFAULT_SHOW_RANDOM));

    }

    @Override
    public void finish() {
        super.finish();
        // кастомная анимация
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
    }
}
