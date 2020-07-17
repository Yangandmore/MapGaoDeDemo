package com.gd.map;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.gd.view.GDMapView;

public class GDMapViewManager extends ViewGroupManager<GDMapView> {

    GDMapView mapView;

    @NonNull
    @Override
    public String getName() {
        return "GDMapView";
    }

    @NonNull
    @Override
    protected GDMapView createViewInstance(@NonNull ThemedReactContext reactContext) {
        mapView = new GDMapView(reactContext);
        return mapView;
    }
}
