package com.google.firebase.udacity.daggerPosts.dagger.di.main

import com.google.firebase.udacity.daggerPosts.dagger.ui.main.posts.PostsFragment
import com.google.firebase.udacity.daggerPosts.dagger.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


/*
* this module deal with all Fragments inside ir app
* */
@Module
abstract class MainFragmentBuilderModule {

    /*
    *  @ContributesAndroidInjector tells dagger hey!! i wanna this fragment to be aware with dagger classes
    * */
    @ContributesAndroidInjector
    internal abstract fun contributeProfileFragment(): ProfileFragment?

    @ContributesAndroidInjector
    internal abstract fun contributePostsFragment(): PostsFragment?
}