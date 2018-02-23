package it.matbell.ask.controllers;

import android.content.Context;
import android.util.Log;

import java.util.UUID;

public class PreferencesController {

    private static final String PREFS = "it.matbell.ask";
    private static final String PREF_LAST_CONFIG_KEY = "lastConfig";
    private static final String PREF_FIRST_RUN_KEY = "firstRun";
    private static final String PREF_DEVICE_ID = "deviceId";

    /**
     * Checks if this is the first time that the service has been executed.
     *
     * @return      True if this is the first execution, False otherwise.
     */
    public static boolean isFirstRun(Context context){
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getBoolean(
                PREF_FIRST_RUN_KEY, true);
    }

    /**
     * Sets a flag in the shared preferences to remember if the first run has been executed or not.
     */
    public static void firstRunDone(Context context){
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit().putBoolean(
                PREF_FIRST_RUN_KEY, false).apply();
    }

    public static void generateUniqueDeviceID(Context context){
        Log.d("PREF_CONTROLLER", "First run, generating the UUID");
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit().putString(
                PREF_DEVICE_ID, UUID.randomUUID().toString()).apply();
    }

    public static String getUniqueDeviceID(Context context){
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(
                PREF_DEVICE_ID, null);
    }

    public static String getSavedConfiguration(Context context){
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(
                PREF_LAST_CONFIG_KEY, null);
    }

    public static void saveConfiguration(Context context, String configuration){
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit().putString(
                PREF_LAST_CONFIG_KEY, configuration).apply();
    }
}
