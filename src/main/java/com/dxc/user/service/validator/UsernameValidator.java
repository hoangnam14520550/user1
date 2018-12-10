package com.dxc.user.service.validator;

import com.dxc.user.common.StorageError;
import com.dxc.user.exception.StorageException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UsernameValidator {
    private static final String USERNAME_REGEX = "[A-Za-z0-9_]+";

    private static Pattern pattern;

    private Matcher matcher;

    public UsernameValidator(){
        pattern = Pattern.compile(USERNAME_REGEX);
    }

    public boolean validateUsername(String username){
        matcher = pattern.matcher(username);
        if(! matcher.matches()) {
            throw new StorageException(StorageError.USERNAME_NOT_VALIDATION, username);
        }
        else return true;
    }
}
