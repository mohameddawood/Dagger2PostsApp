package com.google.firebase.udacity.daggerPosts.dagger.di.main

import androidx.lifecycle.ViewModel
import com.google.firebase.udacity.daggerPosts.dagger.di.daggerViewModelHandling.ViewModelKey
import com.google.firebase.udacity.daggerPosts.dagger.ui.main.posts.PostsViewModel
import com.google.firebase.udacity.daggerPosts.dagger.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/*
* i have two viewmodels in my Main Package (
*   1- profile
*   2- posts
* )
*
* so as we talked about dagger/viewModel problem
* we call it in this way
* don't care if u did't understand it just put ur  @ViewModelKey(ViewModelClassName::class)
* it will be injected
* */
@Module
abstract class MainViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(model: ProfileViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(model: PostsViewModel?): ViewModel?
}