package com.google.firebase.udacity.daggerPosts.dagger.di.main

import android.app.Application
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.firebase.udacity.daggerPosts.dagger.network.main.MainApi
import com.google.firebase.udacity.daggerPosts.dagger.ui.main.posts.PostRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/*
* This module to handle some logic in All Activities after login process
* */
@Module
class MainModule {
    /*
    * as we said i can use retrofit after create it in AppModule as u will see now
    * providesMainApi():MainApi u tell dagger that u wanna mainApi object with u in a place
    * to use it as an injected object
    * */
    @Provides
    fun providesMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }

    /*
    *
    * */
    @Provides
    fun providePostRecyclerAdapter(): PostRecyclerAdapter {
        return PostRecyclerAdapter()
    }

    /*
    *   to make it easy when u use recycleview every time u need to pass the context to the LayoutManager
    *   but after this method u don't need to do this just
    *   @Inject lateinit var layoutManager: LayoutManager in ur activity or fragment
    * */
    @Provides
    fun provideLayoutManager(context: Application): LayoutManager {
        return LinearLayoutManager(context.applicationContext)
    }
}