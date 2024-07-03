package com.example.todoapplication

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("your_endpoint")
     fun getName(): Call<ServerResponse>

    data class ServerResponse(val text: String)
}
