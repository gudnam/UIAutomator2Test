package io.gudnam.uiautomatortest;

import android.support.test.uiautomator.Until;

import org.junit.Test;

/**
 * Created by gudnam on 2017. 4. 12..
 */
public class LoginActivityTest extends BaseTest {

    @Test
    public void testActionSignInButton() {
        findByRes("email").setText("gudnam@switcher.co.kr");
        findByRes("password").setText("12341234");
        findButton(R.string.action_sign_in).click();
        device.wait(Until.hasObject(byRes("email")), DEFAULT_TIMEOUT);
        assertHas(byText(R.string.logined));
    }

}