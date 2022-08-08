package com.bloxes.userdb.controller

import com.bloxes.userdb.model.CreateUserRequest
import com.bloxes.userdb.model.UpdateUserRequest
import com.bloxes.userdb.model.UserResponse
import com.bloxes.userdb.model.WebResponse
import com.bloxes.userdb.service.UserService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/v1/user"])
class UserController(val userService: UserService) {

    @PostMapping(
        value = [""],
        consumes = ["application/json"]
    )
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): WebResponse<String> {
        userService.createUser(createUserRequest)
        return WebResponse(
            status = "OK",
            code = 200,
            data = "User with user_name: ${createUserRequest.user_name} has been created"
        )
    }

    @GetMapping(
        value = ["/{userId}"],
        produces = ["application/json"],
    )
    fun getUser(@PathVariable("userId") id: String): WebResponse<UserResponse> {
        val user = userService.getUser(id)
        return WebResponse(
            status = "OK",
            code = 200,
            data = user
        )
    }

    @PutMapping(
        value = ["/{userId}"],
        consumes = ["application/json"]
    )
    fun updateUser(@PathVariable("userId") id: String, @RequestBody updateUserRequest: UpdateUserRequest): WebResponse<String> {
        userService.updateUser(updateUserRequest, id)
        return WebResponse(
            status = "OK",
            code = 200,
            data = "User with user_name: ${updateUserRequest.user_name} has been updated"
        )
    }

    @DeleteMapping(
        value = ["/{userId}"],
    )
    fun deleteUser(@PathVariable("userId") id: String): WebResponse<String> {
        userService.deleteUser(id)
        return WebResponse(
            status = "OK",
            code = 200,
            data = "User with id: $id has been deletedd"
        )
    }
}