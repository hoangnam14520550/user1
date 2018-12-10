package com.dxc.user.service.validator;

import com.dxc.user.exception.StorageException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmailValidatorTest {
    private static final String EMAIL_TRUE = "nam123@gmail.com";
    private static final String EMAIL_FALSE = "na@#!@me123@";
    private boolean RESULT;

    @Mock
    EmailValidator emailValidator;

    @Test(expected = StorageException.class)
    public void validateEmailTest() {
//        when(emailValidator.validateEmail(EMAIL_FALSE)).thenThrow(new StorageException(StorageError.EMAIL_NOT_VALIDATION, EMAIL_FALSE));
        emailValidator.validateEmail(EMAIL_FALSE);
    }
}
