package com.google.firebase.udacity.daggerPosts

import com.google.firebase.udacity.daggerPosts.dagger.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/*
* instead of using Application class
* we use DaggerApplication it will handle many stuffs automatically
* */

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        /*
        * if u opend AppComponent interface u will find
        * @BindsInstance
        * fun application(application: Application): Builder
        * as i told u we did that because we need the applicationClass
        * created at the same time we create the appComponent
        * */
        return DaggerAppComponent.builder().application(this).build()
    }
}
