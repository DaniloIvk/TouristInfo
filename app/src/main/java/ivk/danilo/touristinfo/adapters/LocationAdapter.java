package ivk.danilo.touristinfo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import ivk.danilo.touristinfo.R;
import ivk.danilo.touristinfo.models.Location;

public class LocationAdapter extends ArrayAdapter<Location> {
    private final Context context;

    public LocationAdapter(@NonNull Context context, List<Location> locations) {
        super(context, R.layout.fragment_location_details, locations);

        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Location location = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                                        .inflate(R.layout.fragment_location_details, parent, false);
        }

        TextView locationNameTextView = convertView.findViewById(R.id.location_name);
        TextView locationDescriptionTextView = convertView.findViewById(R.id.location_description);
        TextView locationContactInfoTextView = convertView.findViewById(R.id.location_contact_info);
        TextView locationWorkingHoursTextView = convertView.findViewById(R.id.location_working_hours);
        ImageView locationImageImageView = convertView.findViewById(R.id.location_image);

        if (location != null) {
            locationNameTextView.setText(location.getName());
            locationDescriptionTextView.setText(location.getDescription());
            locationContactInfoTextView.setText(location.getContactInfo());
            locationWorkingHoursTextView.setText(location.getWorkingHours());
            locationImageImageView.setImageResource(this.getResourceByName(location.getImage(), "drawable"));
        }

        return convertView;
    }

    private int getResourceByName(String name, String type) {
        return this.context.getResources()
                           .getIdentifier(
                                   name,
                                   type,
                                   this.context.getPackageName()
                           );
    }
}
