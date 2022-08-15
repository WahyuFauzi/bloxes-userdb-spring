package com.bloxes.userdb.controller

import com.bloxes.userdb.model.CreateUserRequest
import com.bloxes.userdb.model.UpdateUserRequest
import com.bloxes.userdb.model.UserResponse
import com.bloxes.userdb.model.WebResponse
import com.bloxes.userdb.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/v1/user"])
class UserController(val userService: UserService) {

    @PostMapping(
        value = [""],
        consumes = ["application/json"]
    )
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): WebResponse<UserResponse> {
        val user = userService.createUser(createUserRequest)
        return WebResponse(
            status = "OK",
            code = 200,
            data = user
        )
    }

    @GetMapping(
        value = [""],
        produces = ["application/json"],
    )
    fun getUser(@RequestParam("userId") id: String): WebResponse<UserResponse> {
        val user = userService.getUser(id)
        return WebResponse(
            status = "OK",
            code = 200,
            data = user
        )
    }

    @PutMapping(
        value = [""],
        consumes = ["application/json"]
    )
    fun updateUser(@RequestParam("userId") id: String, @RequestBody updateUserRequest: UpdateUserRequest): WebResponse<UserResponse> {
        val user = userService.updateUser(id, updateUserRequest)
        return WebResponse(
            status = "OK",
            code = 200,
            data = user
        )
    }

    @DeleteMapping(
        value = [""],
    )
    fun deleteUser(@RequestParam("userId") id: String): WebResponse<String> {
        userService.deleteUser(id)
        return WebResponse(
            status = "OK",
            code = 200,
            data = "User with id: $id has been deleted"
        )
    }
}