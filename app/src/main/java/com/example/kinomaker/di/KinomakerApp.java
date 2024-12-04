package com.example.kinomaker.di;

import android.app.Application;
import com.github.terrakok.cicerone.Cicerone;
import com.github.terrakok.cicerone.Router;
import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class KinomakerApp extends Application {

    private static Cicerone<Router> cicerone;
    private static Router router;

    @Override
    public void onCreate() {
        super.onCreate();

        cicerone = Cicerone.create();
        router = cicerone.getRouter();
    }

    public static Cicerone<Router> getCicerone() {
        return cicerone;
    }

    public static Router getRouter() {
        return router;
    }
}
