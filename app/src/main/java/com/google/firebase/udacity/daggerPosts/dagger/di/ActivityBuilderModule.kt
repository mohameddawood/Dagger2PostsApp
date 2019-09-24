package com.google.firebase.udacity.daggerPosts.dagger.di

import com.google.firebase.udacity.daggerPosts.dagger.di.auth.AuthModule
import com.google.firebase.udacity.daggerPosts.dagger.di.auth.AuthViewModelModule
import com.google.firebase.udacity.daggerPosts.dagger.di.main.MainFragmentBuilderModule
import com.google.firebase.udacity.daggerPosts.dagger.di.main.MainModule
import com.google.firebase.udacity.daggerPosts.dagger.di.main.MainViewModelsModule
import com.google.firebase.udacity.daggerPosts.dagger.ui.auth.AuthActivity
import com.google.firebase.udacity.daggerPosts.dagger.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/*
* this module deal with all Activities inside ir app
* */
@Module
abstract class ActivityBuilderModule {

    /*
    *  @ContributesAndroidInjector tells dagger hey!! i wanna this activity to be aware with dagger classes
    *  AuthViewModelModule::class, AuthModule::class just handling some logic in AuthActivity
    * */
    @ContributesAndroidInjector(modules = [AuthViewModelModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity


    /*
    *  @ContributesAndroidInjector tells dagger hey!! i wanna this activity to be aware with dagger classes
    *  [MainFragmentBuilderModule::class, MainViewModelsModule::class, MainModule::class]
    *  just handling some logic in MainActivity
    * */
    @ContributesAndroidInjector(modules = [MainFragmentBuilderModule::class, MainViewModelsModule::class, MainModule::class])
    abstract fun contributeMainActivity(): MainActivity
}