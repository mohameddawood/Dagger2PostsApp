package com.google.firebase.udacity.daggerPosts.dagger.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.google.firebase.udacity.daggerPosts.dagger.SessionManager
import com.google.firebase.udacity.daggerPosts.dagger.models.User
import com.google.firebase.udacity.daggerPosts.dagger.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/*
* all of dagger classes that we built it we can use it now easily
*   Two ways to use the injected classes
*   1 - @Inject lateinint var api:AuthApi
*   2 - inject it in the constructor    EX @Inject constructor(var authApi: AuthApi)
* */
class AuthViewModel @Inject constructor(
        var authApi: AuthApi, var sessionManager: SessionManager
) : ViewModel() {
    private val TAG = "AuthViewModel"

    init {
        Log.d(TAG, "AuthViewModel: is working fine .... ")
        if (authApi == null)
            Log.d(TAG, "AuthViewModel: api is null .... ")
        else
            Log.d(TAG, "AuthViewModel: api is working fine .... ")
    }


    fun authenticateWithId(id: String) {
        Log.d(TAG, "authenticateWithId: Attempting to login")
        sessionManager.authenticateWithId(queryUserId(id))
    }

    /*
    * login with ids form 1 --> 10
    * */
    private fun queryUserId(id: String): LiveData<AuthResource<out User>> {
        return LiveDataReactiveStreams.fromPublisher(authApi.userAuth(id)
                ?.onErrorReturn {
                    var err = User(-1, "", "", "")
                    err
                }?.map { t: User ->
                    if (t.id == -1)
                        return@map AuthResource.error("Could not authenticate", null)

                    AuthResource.authenticated(t)
                }?.subscribeOn(Schedulers.io())!!
        )

    }

    fun observeAuthSatate(): LiveData<AuthResource<User>> {
        return sessionManager.getAuthUser()
    }

}