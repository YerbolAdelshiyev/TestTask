package Model

import java.io.*
import java.lang.RuntimeException
import java.lang.StringBuilder
import java.net.HttpURLConnection
import  java.net.URL

class ApiRequests {
    fun getUsers(page: Int): String {
        val url = URL("https://reqres.in/api/users?page=$page")

        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val inputStream = connection.inputStream
            val reader = BufferedReader(InputStreamReader(inputStream))
            val response = StringBuilder()

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }
            reader.close()
            return response.toString()
        } else {
            throw RuntimeException("Failed to fetch data, Response Code: $responseCode")
        }
    }

    fun getUserById(userId: Int): String {
        val url = URL("https://reqres.in/api/users/$userId")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val inputStream = connection.inputStream
            val reader = BufferedReader(InputStreamReader(inputStream))
            val response = StringBuilder()
            println("Response Code: $responseCode")

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }
            reader.close()
            return response.toString()
        } else {
            throw RuntimeException("Failed to fetch data, Response Code: $responseCode")
        }
    }

    fun getResources(): String {
        val url = URL("https://reqres.in/api/unknown")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val inputStream = connection.inputStream
            val reader = BufferedReader(InputStreamReader(inputStream))
            val response = StringBuilder()
            println("Response Code: $responseCode")

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }
            reader.close()
            return response.toString()
        } else {
            throw RuntimeException("Failed to fetch data, Response Code: $responseCode")
        }
    }

    //    fun getSingleResource(resourceId: Int): SingleResourceResponse {
//        val url = URL("https://reqres.in/api/unknown/$resourceId")
//        val connection = url.openConnection() as HttpURLConnection
//        connection.requestMethod = "GET"
//
//        val responseCode = connection.responseCode
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            val inputStream = connection.inputStream
//            val responseText = inputStream.bufferedReader().use { it.readText() }
//            return Json.decodeFromString(SingleResourceResponse.serializer(), responseText)
//        } else {
//            throw RuntimeException("Failed to fetch data, Response Code: $responseCode")
//        }
//    }
    fun getSingleResourceNotFound(resourceId: Int): String {
        val url = URL("https://reqres.in/api/unknown/$resourceId")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        val responseCode = connection.responseCode
        println("Response Code: $responseCode")

        if (responseCode == HttpURLConnection.HTTP_OK) {
        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
        }
        return ""
    }

    fun getSingleUserNotFound(userId: Int): String {
        val url = URL("https://reqres.in/api/users/$userId")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        val responseCode = connection.responseCode
        println("Response Code: $responseCode")

        if (responseCode == HttpURLConnection.HTTP_OK) {
        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
        }
        return ""
    }

    fun registerUser(email: String, password: String): String {
        val url = URL("https://reqres.in/api/register")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true

        val requestBody = """
            {
                "email": "$email",
                "password": "$password"
            }
        """.trimIndent()

        val outputStream = connection.outputStream
        val writer = OutputStreamWriter(outputStream)
        writer.write(requestBody)
        writer.flush()

        val responseCode = connection.responseCode
        val inputStream = connection.inputStream
        val response = readStream(inputStream)
        println("Response Code: $responseCode")
        println("Response Message: $response")
        if (responseCode == HttpURLConnection.HTTP_OK) {
        } else {
            println("Registration failed")
        }

        return response
    }

    fun readStream(inputStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String?

        try {
            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(line).append("\n")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                inputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            return stringBuilder.toString()
        }
    }

        fun registerUserNegative(email: String, password: String?): String {
            val url = URL("https://reqres.in/api/register")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.doOutput = true

            val requestBody = """
            {
                "email": "$email",
                "password": "$password"
            }
        """.trimIndent()

            val outputStream = connection.outputStream
            val writer = OutputStreamWriter(outputStream)
            writer.write(requestBody)
            writer.flush()

            val responseCode = connection.responseCode
            println("Status code: $responseCode")
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = connection.inputStream
                val reader = BufferedReader(InputStreamReader(inputStream))
                val response = StringBuilder()

                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()
                return response.toString()
            } else {
                val errorStream = connection.errorStream
                val reader = BufferedReader(InputStreamReader(errorStream))
                val errorResponse = StringBuilder()

                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    errorResponse.append(line)
                }
                reader.close()
                return errorResponse.toString()
            }
        }

        fun updateUser(id: Int, name: String, job: String): String {
            val url = URL("https://reqres.in/api/users/$id")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "PUT"
            connection.doOutput = true

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
                val inputStream = connection.inputStream
                val reader = BufferedReader(InputStreamReader(inputStream))
                val response = StringBuilder()

                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()
                return response.toString()
            } else {
                throw RuntimeException("Failed to update user, Response Code: $responseCode")
            }
        }

        fun patchUpdateUser(id: Int, name: String, job: String): String {
            val url = URL("https://reqres.in/api/users/$id")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"

            connection.setRequestProperty("X-HTTP-Method-Override", "PATCH")
            connection.setRequestProperty("Content-Type", "application/json")

            val payload = "{\"name\":\"$name\",\"job\":\"$job\"}"
            connection.doOutput = true
            val outputWriter = OutputStreamWriter(connection.outputStream)
            outputWriter.write(payload)
            outputWriter.flush()

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                val inputStream = connection.inputStream
                val reader = BufferedReader(InputStreamReader(inputStream))
                val response = StringBuilder()

                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()
                return response.toString()
            } else {
                throw RuntimeException("Failed to update user, Response Code: $responseCode")
            }
        }

        fun deleteUser(id: Int): Int {
            val url = URL("https://reqres.in/api/users/$id")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "DELETE"

            val responseCode = connection.responseCode

            if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
                println("User with ID $id deleted successfully.")
            } else {
                throw RuntimeException("Failed to delete user, Response Code: $responseCode")
            }

            return responseCode
        }
    }
