package net.slipp.security;

import net.slipp.domain.User;

import javax.servlet.http.HttpSession;

/**
 * Created by woowahan on 2017. 5. 22..
 */
public class HttpSessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";

    public static boolean isLoginUser(HttpSession session) {
        Object sessionedUser = session.getAttribute(USER_SESSION_KEY);
        if (sessionedUser == null) {
            return false;
        }
        return true;
    }

    public static User getUserFromSession(HttpSession session) {
        if (!isLoginUser(session)) {
            return null;
        }

        return (User)session.getAttribute(USER_SESSION_KEY);
    }
}
