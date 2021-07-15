package uteq.appmoviles.marcadoresgooglemaps;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import uteq.appmoviles.marcadoresgooglemaps.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private DatabaseReference databaseReference;
    private GoogleMap mMap;
    private Marker mMarker;
    private ActivityMapsBinding binding;
    private Gson gson;
    List<MarkerOptions> markers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gson = new Gson();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        markers = new ArrayList<MarkerOptions>();
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        mMap.setInfoWindowAdapter(new UteqMarkerWindowsAdapter(MapsActivity.this));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    LatLng ubicacion = new LatLng(
                            (double)dataSnapshot.child("latitud").getValue(),
                            (double)dataSnapshot.child("longitud").getValue());

                    MarkerOptions options = new MarkerOptions()
                            .position(ubicacion)
                            .title(dataSnapshot.child("nom_fac")
                                    .getValue().toString())
                            .snippet(gson.toJson(dataSnapshot.getValue()));

                    Log.println(Log.INFO, "Info", gson.toJson(dataSnapshot.getValue()));

                    mMarker = mMap.addMarker(options);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}