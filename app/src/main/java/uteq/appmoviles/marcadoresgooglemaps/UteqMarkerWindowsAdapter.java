package uteq.appmoviles.marcadoresgooglemaps;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;

import org.w3c.dom.Text;

public class UteqMarkerWindowsAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mMarkerWindow;
    private Context mContext;
    private UteqMarker mUteqMarker;
    private Gson gson;

    public UteqMarkerWindowsAdapter(Context mContext) {
        this.mContext = mContext;
        this.mUteqMarker = new UteqMarker();
        gson = new Gson();
        mMarkerWindow = LayoutInflater.from(mContext).inflate(R.layout.ubic_info_window, null);
    }

    private void configureWindow(Marker marker){
        mUteqMarker = gson.fromJson(marker.getSnippet(), UteqMarker.class);

        TextView txtNomFac = mMarkerWindow.findViewById(R.id.txtNomFac);
        TextView txtNomDec = mMarkerWindow.findViewById(R.id.txtNomDecano);
        TextView txtUbic = mMarkerWindow.findViewById(R.id.txtUbicacion);
        ImageView imgLogoFac = mMarkerWindow.findViewById(R.id.imgLogoFac);

        txtNomFac.setText(mUteqMarker.getNom_fac());
        txtNomDec.setText(mUteqMarker.getNom_decano());
        txtUbic.setText(mUteqMarker.getUbicacion());

        Glide.with(mContext).load(mUteqMarker.getLogo_url())
                .override(400,400)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        e.printStackTrace();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imgLogoFac);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        configureWindow(marker);
        return mMarkerWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        configureWindow(marker);
        return mMarkerWindow;
    }
}
