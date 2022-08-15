package com.bloxes.userdb

import com.bloxes.userdb.controller.UserController
import com.bloxes.userdb.entity.User
import com.bloxes.userdb.model.CreateUserRequest
import com.bloxes.userdb.model.UpdateUserRequest
import com.bloxes.userdb.model.UserResponse
import com.bloxes.userdb.model.WebResponse
import com.bloxes.userdb.service.UserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*

class UserControllerTest {

    @Mock
    private val service = mock(UserService::class.java)

    private val controller = UserController(service)

    private val id = "id"

    private val dummyCreateUserRequest = CreateUserRequest(
        email = "dummy@email.com",
        password = "password",
        user_name = "username"
    )

    private val dummyUpdateRequest = UpdateUserRequest(
        password = "password",
        user_name = "username",
        subscribed_space = 0,
        used_space = 0,
        recycle_bin = mutableListOf(),
        pinned = mutableListOf(),
        recent = mutableListOf(),
    )

    private val dummyUserResponse = UserResponse(
        id = "id",
        email = "dummy@email.com",
        password = "password",
        user_name = "username",
        subscribed_space = 0,
        used_space = 0,
        subscribed_at = null,
        end_of_subscription = null,
        init_folder = "init_folder",
        recycle_bin = mutableListOf(),
        pinned = mutableListOf(),
        created_at = "24/12/1999",
        updated_at = "24/12/1999"
    )

    private val webResponseUser = WebResponse(
        status = "OK",
        code = 200,
        data = dummyUserResponse
    )

    private val webResponseString = WebResponse(
        status = "OK",
        code = 200,
        data = "User with id: $id has been deleted"
    )

    @Test
    fun initTest() {
        assertInstanceOf(UserController::class.java, controller)
    }
    @Test
    fun createUser() {
        `when`(service.createUser(dummyCreateUserRequest)).thenReturn(dummyUserResponse)
        val webResponse = controller.createUser(dummyCreateUserRequest)
        assertEquals(webResponse, webResponseUser)
        verify(service).createUser(dummyCreateUserRequest)
    }
    @Test
    fun getUser() {
        `when`(service.getUser(id)).thenReturn(dummyUserResponse)
        val webResponse = controller.getUser(id)
        assertEquals(webResponse, webResponseUser)
        verify(service).getUser(id)
    }
    @Test
    fun updateUser() {
        `when`(service.updateUser(id, dummyUpdateRequest)).thenReturn(dummyUserResponse)
        val webResponse = controller.updateUser(id, dummyUpdateRequest)
        assertEquals(webResponse, webResponseUser)
        verify(service).updateUser(id, dummyUpdateRequest)
    }
    @Test
    fun deleteUser() {
        val webResponse = controller.deleteUser(id)
        assertEquals(webResponse, webResponseString)
    }
}