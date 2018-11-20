package com.cyanelix.railwatch.service.user;

import android.content.SharedPreferences;

import com.cyanelix.railwatch.client.UserClient;
import com.cyanelix.railwatch.domain.User;
import com.cyanelix.railwatch.domain.UserId;
import com.cyanelix.railwatch.service.notification.FirebaseIdFacade;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class UserServiceTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private SharedPreferences sharedPreferences;

    @Mock
    private SharedPreferences.Editor editor;

    @Mock
    private UserClient userClient;

    @Mock
    private FirebaseIdFacade firebaseIdFacade;

    @InjectMocks
    private UserService userService;

    @Test
    public void noUserInPreferences_createUser_saveInPreferences() throws URISyntaxException {
        // Given...
        given(sharedPreferences.contains("userId")).willReturn(false);
        given(sharedPreferences.edit()).willReturn(editor);

        given(firebaseIdFacade.getFirebaseId()).willReturn("foo");
        User user = new User("foo");

        String userIdString = "9d35efcb-77da-4690-a9c9-b57dcc5ed50c";
        UserId userId = new UserId(new URI("http://www.example.com/" + userIdString));
        given(userClient.createUser(refEq(user))).willReturn(userId);

        // When...
        userService.createUserIfNotExists(sharedPreferences);

        // Then...
        verify(editor).putString("userId", userIdString);
    }

    @Test
    public void userInPreferences_doNothing() {
        // Given...
        given(sharedPreferences.contains("userId")).willReturn(true);

        // When...
        userService.createUserIfNotExists(sharedPreferences);

        // Then...
        verify(firebaseIdFacade, never()).getFirebaseId();
        verify(userClient, never()).createUser(any(User.class));
        verify(sharedPreferences, never()).edit();
    }
}