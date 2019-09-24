package com.google.firebase.udacity.daggerPosts.dagger.di

import android.app.Application
import com.google.firebase.udacity.daggerPosts.BaseApplication
import com.google.firebase.udacity.daggerPosts.dagger.SessionManager
import com.google.firebase.udacity.daggerPosts.dagger.di.daggerViewModelHandling.ViewModelFactoryModule
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/*
* this is the main component in ur app
*
* u will include all Main Modules Only here
* for EX: i use AppModule to create retrofit to be valid inside all activities SO WHAT include it here
*
* SECOND
*     AndroidSupportInjectionModule::class,  fixed with any project u must add in
* */
@Singleton @Component(modules = [
    AndroidSupportInjectionModule::class, // mandatory in all projects
    ActivityBuilderModule::class,         // to handle all activities with dagger
    AppModule::class,                     // any public Class u wanna use it entire ur app
    ViewModelFactoryModule::class])       // as we said to fix dagger/ViewModel problem

interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        /*
        * NOTE THAT
        * why @BindsInstance because i wanna the AppClass to be with me when i create the app component
        *
        * so for ex if u need in ur app to use HelloClass with the creation of AppComponent
        * okay just @BindsInstance fun helloMyClass(hello :HelloClass):Builder
        * */
        @BindsInstance
        fun application(application: Application): Builder

        // to build the app component
        fun build(): AppComponent
    }

    /*
    * i wanna this class to be with me inside all app so just make it here
    * */
    fun sessionManager(): SessionManager

}
