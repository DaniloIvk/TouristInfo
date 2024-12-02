package ivk.danilo.touristinfo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ivk.danilo.touristinfo.R;
import ivk.danilo.touristinfo.models.Location;

public class LocationDetailsFragment extends Fragment {
    private final Context context;

    private LocationDetailsFragment(Context context) {
        this.context = context;
    }

    @NonNull
    public static LocationDetailsFragment newInstance(Context context, Location location) {
        LocationDetailsFragment fragment = new LocationDetailsFragment(context);
        Bundle args = new Bundle();

        args.putSerializable(Location.SERIALIZABLE, location);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_location_details, container, false);
        TextView locationNameTextView = view.findViewById(R.id.location_name);
        TextView locationDescriptionTextView = view.findViewById(R.id.location_description);
        TextView locationContactInfoTextView = view.findViewById(R.id.location_contact_info);
        TextView locationWorkingHoursTextView = view.findViewById(R.id.location_working_hours);
        ImageView locationImageImageView = view.findViewById(R.id.location_image);

        Location location = getArguments() != null
                ? (Location) getArguments().getSerializable(Location.SERIALIZABLE)
                : null;

        if (location != null) {
            locationNameTextView.setText(location.getName());
            locationDescriptionTextView.setText(location.getDescription());
            locationContactInfoTextView.setText(location.getContactInfo());
            locationWorkingHoursTextView.setText(location.getWorkingHours());
            locationImageImageView.setImageResource(this.getResourceByName(location.getImage(), "drawable"));
        }

        return view;
    }

    private int getResourceByName(String name, String type) {
        return context.getResources()
                      .getIdentifier(
                              name,
                              type,
                              context.getPackageName()
                      );
    }
}
