package id.digilabyte.sipelita.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserPreferences {

    static final String KEY_USER_TOKEN = "token";
    static final String KEY_USER_TYPE = "bearer";

    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setKeyUserToken(Context context, String token){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_TOKEN, token);
        editor.apply();
    }

    public static String getKeyUserToken(Context context){
        return getSharedPreference(context).getString(KEY_USER_TOKEN,"");
    }

    public static void setKeyUserType(Context context, String type){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_TYPE, type);
        editor.apply();
    }

    public static String getKeyUserType(Context context){
        return getSharedPreference(context).getString(KEY_USER_TYPE,"");
    }

    public static void clearKeyUserToken (Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_USER_TOKEN);
        editor.remove(KEY_USER_TYPE);
        editor.apply();
    }

}
