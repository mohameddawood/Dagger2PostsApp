package com.google.firebase.udacity.daggerPosts.dagger.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.lifecycle.Observer
import com.google.firebase.udacity.daggerPosts.dagger.SessionManager
import com.google.firebase.udacity.daggerPosts.dagger.ui.auth.AuthActivity
import com.google.firebase.udacity.daggerPosts.dagger.ui.auth.AuthResource.AuthStatus.*
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


/*
* why extending DaggerAppCompActivity
* because this finish many stuffs u was do it in the old dagger
* and make this class aware with dagger process and lifecycle
* */
abstract class BaseActivity : DaggerAppCompatActivity() {
    private val TAG = "BaseActivity"

    /*
    * finally with dagger i can use sessionManager class without any extra effort
    * just @Inject className
    * */
    @Inject lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    /*
    * check the status of the current request
    * LOADING Or AUTHENTICATED or NoT
    * */
    private fun subscribeObservers() {
        sessionManager.getAuthUser().observe(this, Observer { userAuthResource ->
            if (userAuthResource != null) {
                when (userAuthResource.status) {
                    ERROR -> return@Observer
                    LOADING -> {
                        Log.d(TAG, "subscribeObservers: " + "d")
                    }
                    AUTHENTICATED -> {
                        Log.d(TAG, "subscribeObservers: " + "auth done");
                    }
                    NOT_AUTHENTICATED -> {
                        navLoginScreen()
                    }
                }
            }
        })
    }

    private fun navLoginScreen() {
        var intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}