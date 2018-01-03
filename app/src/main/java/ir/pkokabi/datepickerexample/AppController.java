package ir.pkokabi.datepickerexample;

import android.app.Application;

public class AppController extends Application {

    private static AppController instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        FontsOverride.getInstance().setMainFont(FontsOverride.SANS);

    }

    public static synchronized AppController getInstance() {
        return instance;
    }

}