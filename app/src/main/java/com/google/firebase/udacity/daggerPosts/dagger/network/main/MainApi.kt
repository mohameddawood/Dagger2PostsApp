package com.google.firebase.udacity.daggerPosts.dagger.network.main

import com.google.firebase.udacity.daggerPosts.dagger.models.Post
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {
    @GET("posts")
    fun getPostFromUser(@Query("userId") id: Int): Flowable<List<Post?>?>?

    @GET("posts")
    fun getPosts(@Query("userId") id: Int): Observable<List<Post>>
}