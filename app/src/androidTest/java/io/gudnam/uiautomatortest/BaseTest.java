package io.gudnam.uiautomatortest;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.view.inputmethod.InputMethodManager;

import org.junit.Before;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by gudnam on 2017. 4. 12..
 */

@RunWith(AndroidJUnit4.class)
public abstract class BaseTest {
    public static final long LAUNCH_TIMEOUT = 10000;
    public static final long DEFAULT_TIMEOUT = 1500;

    protected UiDevice device;

    @Before
    public void before() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        assertNotNull(device);
        openApp();
    }

    private void openApp() {
        // Home 화면으로 이동
        device.pressHome();

        // App 실행
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(BuildConfig.APPLICATION_ID);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // 앱을 실행하는 동안 비동기적으로 로딩되는 UI 요소들을 LAUNCH_TIMEOUT 동안 기다려 동기화 해줌
        device.wait(Until.hasObject(By.pkg(BuildConfig.APPLICATION_ID).depth(0)), LAUNCH_TIMEOUT);
    }

    protected UiObject2 findButton(int textResourceId) {
        // textResourceId가 표시된 버튼을 찾는다.
        return findObject(button(textResourceId));
    }

    protected BySelector button(int textResourceId) {
        // textResourceId가 표시된 버튼을 나타내는 BySelector.
        return By.text(string(textResourceId)).clickable(true);
    }

    protected UiObject2 findByText(int textResourceId) {
        // textResourceId가 표시된 요소를 찾는다.
        return findObject(byText(textResourceId));
    }

    protected UiObject2 findByRes(String resourceIdString) {
        // resourceId가 표시된 요소를 찾는다.
        return findObject(byRes(resourceIdString));
    }

    protected BySelector byText(int textResourceId) {
        // textResourceId가 표시된 요소를 나타내는 BySelector.
        return By.text(string(textResourceId));
    }

    protected BySelector byRes(String resourceIdString) {
        // resourceId가 표시된 요소를 나타내는 BySelector.
        return By.res(BuildConfig.APPLICATION_ID, resourceIdString);
    }

    protected UiObject2 findByDesc(int textResourceId) {
        // textResourceId를 컨텐트 설명으로 갖는 요소를 찾는다.
        return findObject(byDesc(textResourceId));
    }

    protected BySelector byDesc(int textResourceId) {
        // textResourceId를 컨텐트 설명으로 갖는 BySelector.
        return By.desc(string(textResourceId));
    }

    protected String string(int textResourceId) {
        // textResourceId에 해당하는 문자열.
        return getTargetContext().getString(textResourceId);
    }

    protected UiObject2 findObject(BySelector selector) {
        device.wait(Until.hasObject(selector), DEFAULT_TIMEOUT);
        return device.findObject(selector);
    }

    protected void hideKeyboard() {
        InputMethodManager manager = (InputMethodManager) getTargetContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager.isAcceptingText()) {
            device.pressBack();
        }
    }

    protected void assertHas(BySelector selector) {
        assertTrue(device.hasObject(selector));
    }
}
