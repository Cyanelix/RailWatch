package com.cyanelix.railwatch.service.user;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.cyanelix.railwatch.client.UserClient;
import com.cyanelix.railwatch.domain.User;
import com.cyanelix.railwatch.domain.UserId;
import com.cyanelix.railwatch.service.notification.FirebaseIdFacade;

import java.util.Objects;
import java.util.function.Consumer;

import javax.inject.Inject;

public class UserService {
    private static final String USER_ID_KEY = "userId";

    @Inject
    UserClient userClient;

    @Inject
    FirebaseIdFacade firebaseIdFacade;

    @Inject
    public UserService() { }

    @SuppressLint("StaticFieldLeak")
    public void createUserIfNotExists(SharedPreferences preferences) {
        new AsyncTask<SharedPreferences, Void, Void>() {
            @Override
            protected Void doInBackground(SharedPreferences... sharedPreferences) {
                final SharedPreferences preferences = sharedPreferences[0];

                if (!preferences.contains(USER_ID_KEY)) {
                    firebaseIdFacade.doWithFirebaseId(new Consumer<String>() {
                        @Override
                        public void accept(String firebaseId) {
                            User user = new User(firebaseId);
                            UserId userId = userClient.createUser(user);

                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString(USER_ID_KEY, userId.getUuid().toString());
                            editor.apply();
                        }
                    });
                }

                return null;
            }
        }.execute(preferences);

    }

    public String getUserId(SharedPreferences sharedPreferences) {
        String userId = sharedPreferences.getString(USER_ID_KEY, null);
        return Objects.requireNonNull(userId, "User ID not found in shared preferences");
    }
}
