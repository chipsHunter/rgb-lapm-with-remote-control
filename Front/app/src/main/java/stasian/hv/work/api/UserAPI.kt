package stasian.hv.work.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

// Data class для отправки данных
data class UserCredentials(val login: String, val password: String)

// Data class для получения данных о пользователе (если нужно)
data class UserGetDTO(val id: Long, val username: String, val password: String)


interface UserApi {
    @POST("user/create")
    @Headers("Accept: application/json")
    suspend fun registerUser(@Body userCredentials: UserCredentials): Response<UserGetDTO>
}