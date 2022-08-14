package com.bloxes.userdb

import com.bloxes.userdb.controller.UserController
import com.bloxes.userdb.service.UserService
import com.bloxes.userdb.service.UserServiceImpl
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserControllerTest {


    @Mock
    val userService = mock(UserServiceImpl.class)

    @InjectMocks
    val controller: UserController = UserController(userService)

    @Test
    fun createUser() {

    }

    @Test
    fun getUser() {

    }

    @Test
    fun updateUser() {

    }

    @Test
    fun deleteUser() {

    }
}
