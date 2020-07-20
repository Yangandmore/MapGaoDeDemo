package com.gd.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.os.Environment;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.gd.util.MapUtil;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GDMapView extends TextureMapView implements AMap.OnMapClickListener, AMap.OnMapLongClickListener, AMap.OnMyLocationChangeListener, AMap.OnCameraChangeListener, AMap.OnMapScreenShotListener, AMap.CancelableCallback {

    private Context context;

    // 控件内部回调工具
    private RCTEventEmitter eventEmitter;

    private MyLocationStyle locationStyle;

    // 缓存精度圈颜色信息
    private String strokeColor, radiusFillColor;

    public GDMapView(Context context) {
        super(context);
        super.onCreate(null);

        this.context = context;
        // 回调工具
        eventEmitter = ((ThemedReactContext)context).getJSModule(RCTEventEmitter.class);

        locationStyle = new MyLocationStyle();
        locationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);

        // 事件
        getMap().setOnMapClickListener(this);
        getMap().setOnMapLongClickListener(this);
        getMap().setOnMyLocationChangeListener(this);
        getMap().setOnCameraChangeListener(this);
    }

    // 设置定位
    public void setLocationEnable(boolean locationEnable) {
        getMap().setMyLocationEnabled(locationEnable);
        getMap().setMyLocationStyle(locationStyle);
    }

    // 设置定位模式
    public void setLocationType(int locationType) {
        locationStyle.myLocationType(locationType);
        getMap().setMyLocationStyle(locationStyle);
    }

    // 设置定点图标
    public void setLocationIcon(String icon) {
        int drawable = context.getResources().getIdentifier(icon, "drawable", context.getPackageName());
        locationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(drawable));
        getMap().setMyLocationStyle(locationStyle);
    }

    // 设置定点图标锚点
    public void setLocationAnchor(double u, double v) {
        locationStyle.anchor(Float.parseFloat(u+""), Float.parseFloat(v+""));
        getMap().setMyLocationStyle(locationStyle);
    }

    // 设定精度圈边线颜色
    public void setLocationStrokeColor(String color) {
        strokeColor = color;
        int c  = Color.parseColor(color);
        locationStyle.strokeColor(c);
        getMap().setMyLocationStyle(locationStyle);
    }

    // 设定精度圈半径
    public void setLocationRadiusFillColor(String color) {
        radiusFillColor = color;
        int c  = Color.parseColor(color);
        locationStyle.radiusFillColor(c);
        getMap().setMyLocationStyle(locationStyle);
    }

    // 设定精度圈填充色
    public void setStrokeWidth(double width) {
        locationStyle.strokeWidth(Float.parseFloat(width+""));
        getMap().setMyLocationStyle(locationStyle);
    }

    // 显示精度圈
    public void setShowsAccuracyRing(boolean show) {
        if (show) {
            int c1  = Color.parseColor(strokeColor);
            int c2  = Color.parseColor(radiusFillColor);
            locationStyle.strokeColor(c1);
            locationStyle.radiusFillColor(c2);
            getMap().setMyLocationStyle(locationStyle);
        } else {
            // 隐藏
            int c = Color.parseColor("#00000000");
            locationStyle.strokeColor(c);
            locationStyle.radiusFillColor(c);
            getMap().setMyLocationStyle(locationStyle);
        }
    }

    // 定位频率
    public void setLocationInterval(long locationInterval) {
        locationStyle.interval(locationInterval);
    }

    // 截屏
    public void setScreenShot() {
        getMap().getMapScreenShot(this);
    }

    // 动画跳跃
    public void animateTo(ReadableArray args) {
        ReadableMap locationData = args.getMap(0);
        long time = Long.parseLong(args.getInt(1) + "");

        // 获取原有数据
        CameraPosition cameraPosition = getMap().getCameraPosition();
        LatLng latLng = cameraPosition.target;
        float zoomLevel = cameraPosition.zoom;
        float tilt = cameraPosition.tilt;
        float bearing = cameraPosition.bearing;

        if (locationData.hasKey("coordinate")) {
            latLng = MapUtil.mapToLatLng(locationData.getMap("coordinate"));
        }
        if (locationData.hasKey("zoomLevel")) {
            zoomLevel = Float.parseFloat(locationData.getDouble("zoomLevel")+"");
        }
        if (locationData.hasKey("tilt")) {
            tilt = Float.parseFloat(locationData.getDouble("tilt")+"");
        }
        if (locationData.hasKey("rotation")) {
            bearing = Float.parseFloat(locationData.getDouble("rotation")+"");
        }
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, zoomLevel, tilt, bearing));
        getMap().animateCamera(cameraUpdate, time, this);
    }

    /***** 截屏回调 *****/

    /**
     * 截屏时回调的方法。
     *
     * @param bitmap 调用截屏接口返回的截屏对象。
     */
    @Override
    public void onMapScreenShot(Bitmap bitmap) {

    }

    /**
     * 带有地图渲染状态的截屏回调方法。
     * 根据返回的状态码，可以判断当前视图渲染是否完成。
     *
     * @param bitmap 调用截屏接口返回的截屏对象。
     * @param i 地图渲染状态， 1：地图渲染完成，0：未绘制完成
     */
    @Override
    public void onMapScreenShot(Bitmap bitmap, int i) {
        WritableMap map = Arguments.createMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        if(null == bitmap){
            return;
        }
        try {
            String path = Environment.getExternalStorageDirectory() + "/map_" + sdf.format(new Date()) + ".png";
            map.putString("path", path);
            FileOutputStream fos = new FileOutputStream(path);
            boolean b = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            if (b) {
                // 截屏成功
                map.putBoolean("isSuccess", true);
            } else {
                // 截屏失败
                map.putBoolean("isSuccess", false);
            }
            if (i != 0) {
                // 地图渲染完成，截屏无网格
                map.putBoolean("isRender", true);
            } else {
                // 地图未渲染完成，截屏有网格
                map.putBoolean("isRender", false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // 异常流程
            map.putBoolean("isSuccess", false);
            map.putBoolean("isRender", false);
        } finally {
            eventEmitter.receiveEvent(getId(), "onMapScreenShot", map);
        }
    }


    /***** 点击回调 *****/
    @Override
    public void onMapClick(LatLng latLng) {
        eventEmitter.receiveEvent(getId(), "onPress", MapUtil.latLngToMap(latLng));
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        eventEmitter.receiveEvent(getId(), "onLongPress", MapUtil.latLngToMap(latLng));
    }

    /***** 定位回调 *****/
    @Override
    public void onMyLocationChange(Location location) {
        WritableMap map = Arguments.createMap();
        map.putDouble("latitude", location.getLatitude());
        map.putDouble("longitude", location.getLongitude());
        map.putDouble("accuracy", location.getAccuracy());
        map.putDouble("altitude", location.getAltitude());
        map.putDouble("speed", location.getSpeed());
        map.putInt("timestamp", Integer.parseInt(location.getTime()+""));
    }

    /***** 动画跳转回调 *****/
    @Override
    public void onFinish() {
        WritableMap map = Arguments.createMap();
        map.putString("status", "finish");
        eventEmitter.receiveEvent(getId(), "onAnimateComplete", map);
    }

    @Override
    public void onCancel() {
        WritableMap map = Arguments.createMap();
        map.putString("status", "cancel");
        eventEmitter.receiveEvent(getId(), "onAnimateComplete", map);
    }

    /***** 状态 *****/
    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        WritableMap map = Arguments.createMap();
        map.putDouble("zoomLevel", cameraPosition.zoom);
        map.putDouble("tilt", cameraPosition.tilt);
        map.putDouble("rotation", cameraPosition.bearing);
        map.putDouble("centerLatitude", cameraPosition.target.latitude);
        map.putDouble("centerLongitude", cameraPosition.target.longitude);
        eventEmitter.receiveEvent(getId(), "onStatusChange", map);
    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        WritableMap map = Arguments.createMap();
        map.putDouble("zoomLevel", cameraPosition.zoom);
        map.putDouble("tilt", cameraPosition.tilt);
        map.putDouble("rotation", cameraPosition.bearing);
        map.putDouble("centerLatitude", cameraPosition.target.latitude);
        map.putDouble("centerLongitude", cameraPosition.target.longitude);

        LatLng southwest = getMap().getProjection().getVisibleRegion().latLngBounds.southwest;
        LatLng northeast = getMap().getProjection().getVisibleRegion().latLngBounds.northeast;
        map.putDouble("spanLatitude",  Math.abs(southwest.latitude - northeast.latitude));
        map.putDouble("spanLongitude", Math.abs(southwest.longitude - northeast.longitude));
        eventEmitter.receiveEvent(getId(), "onStatusChangeComplete", map);
    }
}
