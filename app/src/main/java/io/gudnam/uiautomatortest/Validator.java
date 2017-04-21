package io.gudnam.uiautomatortest;

/**
 * Created by gudnam on 2017. 4. 21..
 */

public class Validator {

    public static boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    public static boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}
