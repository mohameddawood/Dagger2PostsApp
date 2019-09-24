package com.google.firebase.udacity.daggerPosts.dagger

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.google.firebase.udacity.daggerPosts.dagger.models.User
import com.google.firebase.udacity.daggerPosts.dagger.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton


/*
* This class handle if user is logged or not entire the app
* So u will find it in dagger @link AppComponent to make it valid entire the app
* */
@Singleton
class SessionManager @Inject constructor() {
    private val TAG: String = "SessionManager"
    private val cashedUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()


    /*
    * authenticate the user
    * */
    fun authenticateWithId(source: LiveData<AuthResource<out User>>) {
        if (cashedUser != null) {
            cashedUser.value = AuthResource.loading(null)
            cashedUser.addSource(source) {
                cashedUser.value = it as AuthResource<User>?
                cashedUser.removeSource(source)
            }
        }
    }

    fun logOut() {
        Log.d(TAG, "logOut:  done successfully")
        cashedUser.value = AuthResource.logout()
    }

    /*
    * get the logged user
    * */
    fun getAuthUser(): LiveData<AuthResource<User>> {
        return cashedUser
    }
}