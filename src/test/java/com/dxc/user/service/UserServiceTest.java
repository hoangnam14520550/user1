package com.dxc.user.service;

import com.dxc.user.delegate.V1ApiDelegateImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    V1ApiDelegateImpl v1ApiDelegate;

    @InjectMocks
    UserService userService;



}
