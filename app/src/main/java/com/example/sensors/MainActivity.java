package com.example.sensors;

import android.hardware.Sensor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.sensors.fragments.ActivityRecognitionFragment;
import com.example.sensors.fragments.CompassFragment;
import com.example.sensors.fragments.MotionSensorFragment;
import com.example.sensors.fragments.SensorGraphFragment;
import com.example.sensors.fragments.SensorsListFragment;
import com.example.sensors.fragments.StepCounterFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Configuration de la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (savedInstanceState == null) {
            openFragment(new SensorsListFragment());
        }
    }

    // Cette méthode indique à Android de charger ton fichier XML de menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sensor_menu, menu);
        return true;
    }

    // Cette méthode gère les clics sur les éléments du menu déroulant
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        handleNavigation(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void handleNavigation(int id) {
        if (id == R.id.menu_sensors) {
            openFragment(new SensorsListFragment());
        } else if (id == R.id.menu_temperature) {
            openFragment(SensorGraphFragment.newInstance(Sensor.TYPE_AMBIENT_TEMPERATURE, "Température ambiante", "FIRST_VALUE"));
        } else if (id == R.id.menu_humidity) {
            openFragment(SensorGraphFragment.newInstance(Sensor.TYPE_RELATIVE_HUMIDITY, "Humidité relative", "FIRST_VALUE"));
        } else if (id == R.id.menu_proximity) {
            openFragment(SensorGraphFragment.newInstance(Sensor.TYPE_PROXIMITY, "Capteur de proximité", "FIRST_VALUE"));
        } else if (id == R.id.menu_magnetic) {
            openFragment(SensorGraphFragment.newInstance(Sensor.TYPE_MAGNETIC_FIELD, "Champ magnétique", "MAGNITUDE"));
        } else if (id == R.id.menu_accelerometer) {
            openFragment(MotionSensorFragment.newInstance(Sensor.TYPE_ACCELEROMETER, "Accéléromètre : x, y, z"));
        } else if (id == R.id.menu_gravity) {
            openFragment(MotionSensorFragment.newInstance(Sensor.TYPE_GRAVITY, "Gravité : x, y, z"));
        } else if (id == R.id.menu_gyroscope) {
            openFragment(MotionSensorFragment.newInstance(Sensor.TYPE_GYROSCOPE, "Gyroscope : rad/s"));
        } else if (id == R.id.menu_steps) {
            openFragment(new StepCounterFragment());
        } else if (id == R.id.menu_compass) {
            openFragment(new CompassFragment());
        } else if (id == R.id.menu_activity) {
            openFragment(new ActivityRecognitionFragment());
        }
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}