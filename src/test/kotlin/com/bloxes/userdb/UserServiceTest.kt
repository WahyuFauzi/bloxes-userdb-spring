package com.bloxes.userdb

import com.bloxes.userdb.entity.User
import com.bloxes.userdb.helper.DateHelper
import com.bloxes.userdb.helper.Helper
import com.bloxes.userdb.helper.RepositoryHelper
import com.bloxes.userdb.helper.UUIDHelper
import com.bloxes.userdb.model.CreateUserRequest
import com.bloxes.userdb.model.UpdateUserRequest
import com.bloxes.userdb.model.UserResponse
import com.bloxes.userdb.repository.UserRepository
import com.bloxes.userdb.service.UserServiceImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*


class UserServiceTest {

    @Mock
    private val userRepository = mock(UserRepository::class.java)

    @Mock
    private val UUIDHelper = mock(UUIDHelper::class.java)

    @Mock
    private val repoHelper = mock(RepositoryHelper::class.java)
    @Mock
    private val dateHelper = mock(DateHelper::class.java)
    private val helper = Helper()

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

    private val dummyUserEntity = User(
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
        recent = mutableListOf(),
        created_at = "24/12/1999",
        updated_at = "24/12/1999"
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

    private val service = UserServiceImpl(userRepository, UUIDHelper, repoHelper, dateHelper, helper)

    @Test
    fun initTest() {
        assertInstanceOf(UserServiceImpl::class.java, service)
    }

    @Test
    fun createUser() {
        `when`(UUIDHelper.getRandomUUID()).thenReturn(id)
        `when`(dateHelper.getCurrentDateInString()).thenReturn("24/12/1999")
        service.createUser(dummyCreateUserRequest)
        verify(UUIDHelper).getRandomUUID()
        verify(userRepository).save(dummyUserEntity)
        verify(dateHelper, times(2)).getCurrentDateInString()
    }

    @Test
    fun getUser() {
        `when`(repoHelper.findProductByIdOrThrowNotFound(id)).thenReturn(dummyUserEntity)
        val userFounded = service.getUser(id)
        assertEquals(userFounded, dummyUserResponse)

        verify(repoHelper).findProductByIdOrThrowNotFound(id)
    }

    @Test
    fun updateUser() {
        `when`(repoHelper.findProductByIdOrThrowNotFound(id)).thenReturn(dummyUserEntity)
        `when`(dateHelper.getCurrentDateInString()).thenReturn("24/12/1999")
        val userUpdated = service.updateUser(id, dummyUpdateRequest)
        assertEquals(userUpdated, dummyUserResponse)
        verify(repoHelper).findProductByIdOrThrowNotFound(id)
        verify(userRepository).save(dummyUserEntity)
        verify(dateHelper).getCurrentDateInString()
    }
}