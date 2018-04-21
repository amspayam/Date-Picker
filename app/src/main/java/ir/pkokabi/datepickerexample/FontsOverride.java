package ir.pkokabi.datepickerexample;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.Hashtable;

public class FontsOverride {

    private static FontsOverride instance;
    private static Hashtable<String, Typeface> fontCache = new Hashtable<>();
    public static String SANS = "fonts/iran_sans.ttf";
    public static String ENGLISH = "fonts/english.ttf";

    private FontsOverride() {
    }

    public static FontsOverride getInstance() {
        if (instance == null) instance = new FontsOverride();
        return instance;
    }

    void setMainFont(String fontName) {
        try {
            Field staticField = Typeface.class
                    .getDeclaredField("MONOSPACE");
            staticField.setAccessible(true);
            staticField.set(null, Typeface.createFromAsset(AppController.getInstance()
                    .getApplicationContext().getAssets(), fontName));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    private Typeface getTypeface(Context context, String font) {
        Typeface tf = fontCache.get(font);
        if (tf == null) {
            try {
                if (font.equals(SANS))
                    tf = Typeface.createFromAsset(context.getAssets(), SANS);
                else if (font.equals(ENGLISH))
                    tf = Typeface.createFromAsset(context.getAssets(), ENGLISH);
            } catch (Exception e) {
                return null;
            }
            fontCache.put(font, tf);
        }
        return tf;
    }

}
