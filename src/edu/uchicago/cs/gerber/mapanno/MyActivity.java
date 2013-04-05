package edu.uchicago.cs.gerber.mapanno;

import java.util.ArrayList;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
//from www.apress.com/9781430234135/
//modified and refactored by Adam Gerber, University of Chicago
//icons set to approx 50px x 50px
public class MyActivity extends MapActivity implements OnCheckedChangeListener {

	//UI elements
    MapView map;
    ToggleButton tglView;
    
    
    MapController controller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tglView = (ToggleButton) findViewById(R.id.tglView  );
        tglView.setOnCheckedChangeListener(this);
        
 
        
        map = (MapView)findViewById(R.id.map);
        controller = map.getController();
        
        ArrayList<GeoPoint> gptLocations = new ArrayList<GeoPoint>();
        ArrayList<Drawable> drwImages = new ArrayList<Drawable>();
        
        
        //Quad U of C Hyde Park Campus: Latitude: 41789756, Longitude: -87599771
        gptLocations.add(new GeoPoint(41789756,-87599771));
        drwImages.add(getResources().getDrawable(R.drawable.uc));
        
        //medici cafe.
        //http://geocoder.us/demo.cgi?address=1327+E+57th+St%2C+Chicago%2C+IL+60637
        gptLocations.add(new GeoPoint(41791439, -87593357));
        drwImages.add(getResources().getDrawable(R.drawable.gargoyle));

        LocationOverlay lovOverlay = new LocationOverlay(this, getResources().getDrawable(R.drawable.uc));
        lovOverlay.setItems(gptLocations, drwImages);
        map.getOverlays().add(lovOverlay);
        controller.setCenter(gptLocations.get(0));
        controller.setZoom(15);
        
        
        map.setSatellite(false);
        map.setBuiltInZoomControls(true);
        //too bad this is deprecated.
        // map.setStreetView(true);
        
    }
    
    
    //not used but requird 
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
    


	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if(isChecked){
			map.setSatellite(true);
		} else {
			map.setSatellite(false);
		}
		
	}
}