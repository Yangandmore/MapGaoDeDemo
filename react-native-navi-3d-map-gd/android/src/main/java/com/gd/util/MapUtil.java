package com.gd.util;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;

public class MapUtil {
    public static LatLngBounds mapToLatLngBound(ReadableArray array) {
        ReadableMap map = array.getMap(0);
        double latitude = map.getDouble("centerLatitude");
        double longitude = map.getDouble("centerLongitude");
        double spanLatitude = map.getDouble("spanLatitude");
        double spanLongitude = map.getDouble("spanLongitude");
        LatLngBounds latLngBounds = new LatLngBounds(
                new LatLng( - spanLatitude / 2, longitude - spanLongitude / 2),
                new LatLng(latitude + spanLatitude / 2, longitude + spanLongitude / 2)
        );
        return latLngBounds;
    }

    public static WritableMap latLngToMap(LatLng latLng) {
        WritableMap map = Arguments.createMap();
        map.putDouble("latitude", latLng.latitude);
        map.putDouble("longitude", latLng.longitude);
        return map;
    }

    public static LatLng mapToLatLng(ReadableMap map) {
        LatLng latLng = null;
        if (map.hasKey("latitude") && map.hasKey("longitude")) {
            latLng = new LatLng(map.getDouble("latitude"), map.getDouble("longitude"));
        }
        return latLng;
    }

}
