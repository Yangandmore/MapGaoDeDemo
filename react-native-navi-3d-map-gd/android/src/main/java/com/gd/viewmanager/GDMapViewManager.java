package com.gd.viewmanager;

import androidx.annotation.NonNull;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
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

    @Override
    public void onDropViewInstance(@NonNull GDMapView view) {
        super.onDropViewInstance(view);
        mapView.onDestroy();
    }

    /**
     * 设置地图图层
     */
    @ReactProp(name = "mapType")
    public void setMapType(GDMapView view, String mapType) {
        switch (mapType) {
            case "normal":
                view.getMap().setMapType(AMap.MAP_TYPE_NORMAL);
                break;
            case "satellite":
                view.getMap().setMapType(AMap.MAP_TYPE_SATELLITE);
                break;
            case "night":
                view.getMap().setMapType(AMap.MAP_TYPE_NIGHT);
                break;
            case "navi":
                view.getMap().setMapType(AMap.MAP_TYPE_NAVI);
                break;
            case "bus":
                view.getMap().setMapType(AMap.MAP_TYPE_BUS);
                break;
        }
    }

    /**
     * 设置定位
     */
    @ReactProp(name = "locationEnable")
    public void setLocationEnable(GDMapView view, boolean locationEnable) {
        view.setLocationEnable(locationEnable);
    }

    /**
     * 设置定位模式
     */
    @ReactProp(name = "locationTypeAndroid")
    public void setLocationType(GDMapView view, String locationType) {
        switch (locationType) {
            case "show":
                view.setLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);
                break;
            case "locate":
                view.setLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
                break;
            case "follow":
                view.setLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);
                break;
            case "map_rotate":
                view.setLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);
                break;
            case "location_rotate":
                view.setLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
                break;
            case "location_rotate_no_center":
                view.setLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
                break;
            case "follow_no_center":
                view.setLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);
                break;
            case "map_rotate_no_center":
                view.setLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE_NO_CENTER);
                break;
        }
    }

    /**
     * 点位的部分style
     */
    @ReactProp(name = "locationStyle")
    public void setLocationStyle(GDMapView view, ReadableMap locationStyle) {
        if (locationStyle.hasKey("locationIcon")) {
            view.setLocationIcon(locationStyle.getString("locationIcon"));
        }
        if (locationStyle.hasKey("locationAnchorX") && locationStyle.hasKey("locationAnchorY")) {
            view.setLocationAnchor(locationStyle.getDouble("locationAnchorX"),
                    locationStyle.getDouble("locationAnchorY") );
        }
        if (locationStyle.hasKey("strokeColor")) {
            view.setLocationStrokeColor(locationStyle.getString("strokeColor"));
        }
        if (locationStyle.hasKey("radiusFillColor")) {
            view.setLocationRadiusFillColor(locationStyle.getString("radiusFillColor"));
        }
        if (locationStyle.hasKey("strokeWidth")) {
            view.setStrokeWidth(locationStyle.getInt("strokeWidth"));
        }
        if (locationStyle.hasKey("showsAccuracyRing")) {
            view.setShowsAccuracyRing(locationStyle.getBoolean("showsAccuracyRing"));
        }
    }

    /**
     * 定位频率
     */
    @ReactProp(name = "locationInterval")
    public void setLocationInterval(GDMapView view, Integer locationInterval) {
        view.setLocationInterval(locationInterval.longValue());
    }


    /**
     * 显示室内
     */
    @ReactProp(name = "showsIndoorMap")
    public void showIndoorMap(GDMapView view, Boolean show) {
        view.getMap().showIndoorMap(show);
    }

    /**
     * 3D地图
     */
    @ReactProp(name = "showsBuildings")
    public void showBuildings(GDMapView view, Boolean show) {
        view.getMap().showBuildings(show);
    }

    /**
     * 地图文本
     */
    @ReactProp(name = "showsLabels")
    public void showMapText(GDMapView view, Boolean show) {
        view.getMap().showMapText(show);
    }

    /**
     * 路况
     */
    @ReactProp(name = "showsTraffic")
    public void setTrafficEnabled(GDMapView view, Boolean show) {
        view.getMap().setTrafficEnabled(show);
    }

    /**
     * 缩放控件
     */
    @ReactProp(name = "showsZoomControls")
    public void setZoomControls(GDMapView view, Boolean show) {
        view.getMap().getUiSettings().setZoomControlsEnabled(show);
    }

    /**
     * 指南针控件
     */
    @ReactProp(name = "showsCompass")
    public void setCompass(GDMapView view, Boolean show) {
        view.getMap().getUiSettings().setCompassEnabled(show);
    }

    /**
     * 定位控件
     */
    @ReactProp(name = "showsLocationButton")
    public void setLocationButton(GDMapView view, Boolean show) {
        view.getMap().getUiSettings().setMyLocationButtonEnabled(show);
    }

    /**
     * 控件
     */
    @ReactProp(name = "showsScale")
    public void setScaleControls(GDMapView view, Boolean show) {
        view.getMap().getUiSettings().setScaleControlsEnabled(show);
    }

    /**
     * 缩放手势
     */
    @ReactProp(name = "zoomEnabled")
    public void setZoomEnable(GDMapView view, Boolean show) {
        view.getMap().getUiSettings().setZoomGesturesEnabled(show);
    }

    /**
     * 滑动手势
     */
    @ReactProp(name = "scrollEnabled")
    public void setScrollEnable(GDMapView view, Boolean show) {
        view.getMap().getUiSettings().setScrollGesturesEnabled(show);
    }

    /**
     * 旋转手势
     */
    @ReactProp(name = "rotateEnabled")
    public void setRotateEnable(GDMapView view, Boolean show) {
        view.getMap().getUiSettings().setRotateGesturesEnabled(show);
    }

    /**
     * 倾斜控件
     */
    @ReactProp(name = "tiltEnabled")
    public void setTiltEnable(GDMapView view, Boolean show) {
        view.getMap().getUiSettings().setTiltGesturesEnabled(show);
    }

    /**
     * 缩放等级
     */
    @ReactProp(name = "zoomLevel")
    public void setZoomLevel(GDMapView view, double zoomLevel) {
        view.getMap().moveCamera(CameraUpdateFactory.zoomTo(Float.parseFloat(zoomLevel+"")));
    }

    /**
     * 滑动中心
     */
    @ReactProp(name = "centerCoordinates")
    public void setCenterCoordinates(GDMapView view, ReadableMap readableMap) {
        if (readableMap.hasKey("latitude") && readableMap.hasKey("longitude")) {
            view.getMap().moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(
                    readableMap.getDouble("latitude"),
                    readableMap.getDouble("longitude"))));
        }
    }

    /**
     * 旋转方向
     */
    @ReactProp(name = "rotation")
    public void setRotation(GDMapView view, double rotation) {
        view.getMap().moveCamera(CameraUpdateFactory.changeBearing(Float.parseFloat(rotation+"")));
    }

    /**
     * 倾斜角度
     */
    @ReactProp(name = "tilt")
    public void setTilt(GDMapView view, double tilt) {
        view.getMap().moveCamera(CameraUpdateFactory.changeTilt(Float.parseFloat(tilt+"")));
    }

}
