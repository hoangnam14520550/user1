package com.dxc.user.delegate;

import com.dxc.user.api.V1ApiDelegate;
import com.dxc.user.api.model.User;
import com.dxc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class V1ApiDelegateImpl implements V1ApiDelegate {
    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> deleteUser(String username) {
        return ResponseEntity.ok(userService.deleteUser(username));
    }

    @Override
    public ResponseEntity<User> getUserByUsername(String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @Override
    public ResponseEntity<String> loginUser(String username, String password) {
        return ResponseEntity.ok(userService.loginUser(username, password));
    }

    @Override
    public ResponseEntity<Void> logoutUser() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> upsertUser(User user) {
        return ResponseEntity.ok(userService.upsertUser(user));
    }
}
