package com.willthishappen.infuture.util;

import com.willthishappen.infuture.domain.UserBean;

/**
 * Created by Yahor_Fralou on 8/14/2017 1:10 PM.
 */

public class SessionHelper {
    private static UserBean loggedUser;

    public static UserBean getCurrentUser() {
        return loggedUser;
    }

    public static void setCurrentUser(UserBean user) {
        if (loggedUser == null) {
            loggedUser = new UserBean();
        }

        loggedUser.setId(user.getId());
        loggedUser.setName(user.getName());
        loggedUser.setEmail(user.getEmail());
        loggedUser.setPhotoUrl(user.getPhotoUrl());
    }

    public static void signOut() {
        loggedUser = null;
    }

    public static boolean isAuthorized() {
        return loggedUser != null;
    }
}
