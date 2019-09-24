package com.google.firebase.udacity.daggerPosts.dagger.di.daggerViewModelHandling

import androidx.lifecycle.ViewModelProvider.Factory
import dagger.Binds
import dagger.Module

/*
* This is how to use ViewModelFactory With dagger
*
* we use @Bind (
*   1- this it an abstract class
*   2- we cant user Provides it abstract classes so don't worry use Bind here instead of @Provides
* )
*
* still fixing dagger/viewModel problem don't worry just copy/past it
* */

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory?): Factory?
}