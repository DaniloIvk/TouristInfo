package ivk.danilo.touristinfo;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ivk.danilo.touristinfo.adapters.LocationAdapter;
import ivk.danilo.touristinfo.fragments.LocationDetailsFragment;
import ivk.danilo.touristinfo.models.Location;

public class MainActivity extends AppCompatActivity {
    private List<Location> locations;
    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView locationsListView = findViewById(R.id.locations);

        this.setLocations();

        LocationAdapter locationAdapter = new LocationAdapter(this, this.locations);

        locationsListView.setAdapter(locationAdapter);

        locationsListView.setOnItemClickListener((parent, view, position, id) -> {
            this.position = position;
        });

        Button showDetailsButton = findViewById(R.id.show_location_details);

        showDetailsButton.setOnClickListener(v -> {
            if (this.position >= 0) {
                LocationDetailsFragment locationDetailsFragment = LocationDetailsFragment.newInstance(this, this.locations.get(this.position));

                getSupportFragmentManager().beginTransaction()
                                           .replace(R.id.location_fragment_container, locationDetailsFragment)
                                           .addToBackStack(null)
                                           .commit();
            } else {
                Toast.makeText(MainActivity.this, getText(R.string.location_not_chosen_toast), Toast.LENGTH_SHORT)
                     .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    private void setLocations() {
        List<Location> locations = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.dummy_data)));
            StringBuilder stringBuilder = new StringBuilder();
            Gson gson = new Gson();

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String jsonString = stringBuilder.toString();

            reader.close();

            Location[] locationsArray = gson.fromJson(jsonString, Location[].class);
            locations.addAll(Arrays.asList(locationsArray));

        } catch (IOException e) {
            Log.e("Locations", e.toString());
        }

        this.locations = locations;
    }
}