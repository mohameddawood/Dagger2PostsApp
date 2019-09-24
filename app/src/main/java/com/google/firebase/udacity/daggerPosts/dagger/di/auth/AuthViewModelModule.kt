package com.google.firebase.udacity.daggerPosts.dagger.di.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.udacity.daggerPosts.dagger.di.daggerViewModelHandling.ViewModelKey
import com.google.firebase.udacity.daggerPosts.dagger.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/*
* i have one viewmodel in my Auth Package (
*   1- authentication
* )
*
* so as we talked about dagger/viewModel problem
* we call it in this way
* don't care if u did't understand it just put ur  @ViewModelKey(ViewModelClassName::class)
* it will be injected
* */
@Module
abstract class AuthViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel

}