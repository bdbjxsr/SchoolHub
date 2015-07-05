package id.hub.school.schoolhub.model;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.SchoolHubApp;

@Singleton
public class AccountPreferences {

    public static final String KEY_ID = "account_id";
    public static final String KEY_USERNAME = "username";

    private static final String SHARED_PREFERENCES_NAME = "account";

    private SharedPreferences sharedPreferences;

    @Inject
    public AccountPreferences(Application application) {
        sharedPreferences = application.getSharedPreferences(SHARED_PREFERENCES_NAME,
                Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPreferences() { return sharedPreferences; }

    public void setAccountName(String accountName) {
        getSharedPreferences().edit().putString(KEY_USERNAME, accountName).apply();
    }

    public void setAccountID(long accountID) {
        getSharedPreferences().edit().putLong(KEY_ID, accountID).apply();
    }

    public String accountName() {
        return getSharedPreferences().getString(KEY_USERNAME, null);
    }

    public long accountID() {
        return getSharedPreferences().getLong(KEY_ID, -1);
    }


}
