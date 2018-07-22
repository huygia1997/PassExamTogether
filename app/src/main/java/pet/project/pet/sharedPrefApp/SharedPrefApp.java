package pet.project.pet.sharedPrefApp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefApp {
    SharedPreferences sharepreferences;

    public static SharedPrefApp instance = null;

    public static SharedPrefApp getInstance() {

        if (instance == null) {
            synchronized (SharedPrefApp.class) {
                instance = new SharedPrefApp();
            }
        }
        return instance;
    }

    public void saveISLogged_IN(Context context, Boolean isLoggedin, int currentUserId, int currentUserRoleId) {
        sharepreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharepreferences.edit();
        editor.putBoolean("IS_LOGIN", isLoggedin);
        editor.putInt("CURENT_USER_ID", currentUserId);
        editor.putInt("CURRENT_USER_ROLE_ID", currentUserRoleId);
        editor.commit();
    }

    public boolean getISLogged_IN(Context context) {
        sharepreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sharepreferences.getBoolean("IS_LOGIN", false);
    }

    public int getCurrentUserId(Context context) {
        sharepreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sharepreferences.getInt("CURENT_USER_ID", -1);
    }

    public int getCurrentUserRoleId(Context context){
        sharepreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sharepreferences.getInt("CURRENT_USER_ROLE_ID", -1);
    }

    public void logout(Context context){
        sharepreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharepreferences.edit();
        editor.clear();
        editor.commit();
    }

}
