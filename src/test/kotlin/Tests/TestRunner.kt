package Tests

object TestRunner {
    @JvmStatic
    fun main(args: Array<String>) {
        val apiTests = ApiTests()
        apiTests.testGetUsers()
        apiTests.testGetSingleUser()
        apiTests.testGetResource()
        apiTests.testGetSingleResourceNotFound()
        apiTests.testGetSingleUserNotFound()
        apiTests.testRegisterUser()
        apiTests.testRegisterUserUnsuccessfully()
        apiTests.testUpdateUser()
        apiTests.testPatchUpdateUser()
        apiTests.testDeleteUser()
    }
}