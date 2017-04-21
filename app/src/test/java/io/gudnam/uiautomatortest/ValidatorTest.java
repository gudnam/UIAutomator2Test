package io.gudnam.uiautomatortest;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by gudnam on 2017. 4. 21..
 */
public class ValidatorTest {

    @Test
    public void shouldReturnTrueWhenIsPasswordValidIfPasswordLengthMoreThan4() {
        assertTrue(Validator.isPasswordValid("12345"));
    }
}