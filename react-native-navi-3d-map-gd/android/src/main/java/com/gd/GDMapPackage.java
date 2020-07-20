package com.gd;

import androidx.annotation.NonNull;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.gd.viewmanager.GDMapViewManager;

import java.util.Arrays;
import java.util.List;

public class GDMapPackage implements ReactPackage {

    @NonNull
    @Override
    public List<NativeModule> createNativeModules(@NonNull ReactApplicationContext reactContext) {

        return Arrays.<NativeModule>asList();
    }

    @NonNull
    @Override
    public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactContext) {

        return Arrays.<ViewManager>asList(new GDMapViewManager());
    }
}
