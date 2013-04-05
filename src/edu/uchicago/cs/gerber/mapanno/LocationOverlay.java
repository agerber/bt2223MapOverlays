package edu.uchicago.cs.gerber.mapanno;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;



public class LocationOverlay extends ItemizedOverlay<OverlayItem> {
	private List<GeoPoint> gptItems;
	private List<Drawable> drwMarkers;
	private Context con;

	
	
	public LocationOverlay(Context context, Drawable marker) {
		super(boundCenterBottom(marker));
		con = context;
	}

	public void setItems(ArrayList<GeoPoint> items,
			ArrayList<Drawable> drawables) {
		gptItems = items;
		drwMarkers = drawables;
		populate();
	}

	
	@Override
	protected OverlayItem createItem(int nItem) {
		OverlayItem ovi = new OverlayItem(gptItems.get(nItem), null, null);
		ovi.setMarker(boundCenterBottom(drwMarkers.get(nItem)));
		return ovi;
	}

	@Override
	public int size() {
		return gptItems.size();
	}

	// ********************************
	// When user taps on an icon
	// ********************************
	@Override
	protected boolean onTap(int i) {

		switch (i) {
		case 0:
			Toast.makeText(con, "U of C quad", Toast.LENGTH_SHORT).show();
			break;
		case 1:
			Toast.makeText(con, "Medici cafe", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}

		return true;
	}

	// ********************************
	// When user clicks anywhere on the screen.
	//params set as final so that they can be used in thread, which simply delays this toast
	//by 150ms, so that the click icon toast above may go first.
	//you do not need the final params nor the Handler if you want to implement this without threads.
	// ********************************
	@Override
	public boolean onTouchEvent(final  MotionEvent event,  final MapView mapView) {

		// ---when user lifts his finger---
		if (event.getAction() == 1) {
		
			
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					GeoPoint gpt = mapView.getProjection().fromPixels(
							(int) event.getX(), (int) event.getY());
					Toast.makeText(
							con,
							gpt.getLatitudeE6() / 1E6 + "," + gpt.getLongitudeE6()
									/ 1E6, Toast.LENGTH_SHORT).show();
	
				}
			}, 150);

		}

		return false;
	}

}
