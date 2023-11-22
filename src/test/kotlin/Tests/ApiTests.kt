package Tests

import Model.ApiRequests


class ApiTests {
    fun testGetUsers() {
        val userApi = ApiRequests()
        val response = userApi.getUsers(3)
        println(response)
    }

    fun testGetSingleUser() {
        val userApi = ApiRequests()
        val response = userApi.getUserById(2)
        val expectedEmail = "janet.weaver@reqres.in"
        if (response.contains(expectedEmail)) {
            println("Test passed: Found user with email $expectedEmail")
        } else {
            println("Test failed: User with email $expectedEmail not found")
        }
    }

    fun testGetResource() {
        val userApi = ApiRequests()
        val response = userApi.getResources()
        println(response)
    }

    fun testGetSingleResourceNotFound() {
        val apiRequests = ApiRequests()
        val resourceNotFoundResponse = apiRequests.getSingleResourceNotFound(23)
        if (resourceNotFoundResponse.isEmpty()) {
            println("Resource not found - Test Passed")
        } else {
            println("Resource is found - Test failed")
        }


    }

    fun testGetSingleUserNotFound() {
        val apiRequests = ApiRequests()
        val resourceNotFoundResponse = apiRequests.getSingleUserNotFound(23)
        if (resourceNotFoundResponse.isEmpty()) {
            println("User not found - Test Passed")
        } else {
            println("User is found - Test failed")
        }
    }
    fun testRegisterUser(){
        val apiRequests = ApiRequests()
        val response = apiRequests.registerUser("eve.holt@reqres.in", "pistol")
        if (response.contains("\"id\":")) {
            println("User registered successfully!")
        } else {
            println("User registration failed!")
        }

    }
    fun testRegisterUserUnsuccessfully(){
        val apiRequests = ApiRequests()
        val response = apiRequests.registerUserNegative("sydney@fife", null)
        if (response.contains("\"error\":")) {
            println("User registration unsuccessful: $response")
        } else {
            println("Unexpected response: $response")
        }
    }
    fun testUpdateUser(){
        val apiRequests = ApiRequests()
        val response = apiRequests.updateUser(2,"morpheus", "zion resident")
        if (response.contains("\"updatedAt\":")) {
            println("User information updated: $response")
        } else {
            println("Unexpected response: $response")
        }
    }
    fun testPatchUpdateUser(){
        val apiRequests = ApiRequests()
        val response = apiRequests.patchUpdateUser(2,"morpheus", "zion resident")
        if (response.contains("\"createdAt\":")) {
            println("User information partially updated: $response")
        } else {
            println("Unexpected response: $response")
        }
    }
    fun testDeleteUser(){
        val apiRequests = ApiRequests()
        val responseCode = apiRequests.deleteUser(20)
        if (responseCode == 204) {
            println("User deletion test passed")
        } else {
            println("User deletion test failed. Response code: $responseCode")
        }
    }
}