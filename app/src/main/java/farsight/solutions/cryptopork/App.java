package farsight.solutions.cryptopork;

import android.app.Application;

import farsight.solutions.cryptopork.injection.AppComponent;
import farsight.solutions.cryptopork.injection.AppModule;
import farsight.solutions.cryptopork.injection.DaggerAppComponent;

public class App extends Application {
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    protected AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }
}