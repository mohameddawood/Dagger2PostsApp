package com.google.firebase.udacity.daggerPosts.dagger.network.auth

import com.google.firebase.udacity.daggerPosts.dagger.models.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {
    @GET("users/{id}") fun userAuth(@Path("id") id: String?): Flowable<User?>?
}