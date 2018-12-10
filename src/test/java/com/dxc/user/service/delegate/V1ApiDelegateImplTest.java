package com.dxc.user.service.delegate;

import com.dxc.user.api.model.User;
import com.dxc.user.delegate.V1ApiDelegateImpl;
import com.dxc.user.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class V1ApiDelegateImplTest {
    private static final String USERNAME = "nam123";
    private static final String PASSWORD = "123456";
    private static final String ID = "b026c28e-4728-41f0-8eb2-e15b51e1e305";
    private static final String RETURN = "Login successfully";
    private static final String JSON = "{}";
    private static final User USER = new User();
    @Mock
    UserService userService;

    @InjectMocks
    V1ApiDelegateImpl v1ApiDelegate;

    @Test
    public void testDeleteUser(){
        String ret = "";
        when(userService.deleteUser(USERNAME)).thenReturn(ret);
        ResponseEntity<String> res = v1ApiDelegate.deleteUser(USERNAME);
        Assert.assertEquals(res.getBody(), ret);
    }

    @Test
    public void testUpsertUser(){
        when(userService.upsertUser(new User())).thenReturn(ID);
        ResponseEntity<String> res = v1ApiDelegate.upsertUser(new User());
        Assert.assertEquals(res.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetUserByUsername(){
        when(userService.getUserByUsername(USERNAME)).thenReturn(USER);
        ResponseEntity<User> res = v1ApiDelegate.getUserByUsername(USERNAME);
        Assert.assertEquals(res.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testLoginUser(){
        when(userService.loginUser(USERNAME, PASSWORD)).thenReturn(RETURN);
        ResponseEntity<String> res = v1ApiDelegate.loginUser(USERNAME, PASSWORD);
        Assert.assertEquals(res.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testLougoutUser(){
        ResponseEntity<Void> res = v1ApiDelegate.logoutUser();
        Assert.assertEquals(res.getStatusCode(), HttpStatus.OK);
    }
}
