package com.dxc.user.service.validator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UsernameValidatorTest {
    private static final String USERNAME_TRUE = "nam123";
    private static final String USERNAME_FALSE = "nam!23";
    @Mock
    UsernameValidator usernameValidator;

    @Test
    public void validateUsernameTest(){
        Assert.assertEquals(false, usernameValidator.validateUsername(USERNAME_FALSE));
    }
}
