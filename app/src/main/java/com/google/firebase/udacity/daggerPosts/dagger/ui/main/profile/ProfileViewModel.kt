package com.google.firebase.udacity.daggerPosts.dagger.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.udacity.daggerPosts.dagger.SessionManager
import com.google.firebase.udacity.daggerPosts.dagger.models.User
import com.google.firebase.udacity.daggerPosts.dagger.ui.auth.AuthResource
import javax.inject.Inject

/*
* all of dagger classes that we built it we can use it now easily
*   Two ways to use the injected classes
*   1 - @Inject lateinint var sessionManager:SessionManager
*   2 - inject it in the constructor    EX @Inject constructor(var sessionManager: SessionManager)
* */
class ProfileViewModel @Inject constructor(val sessionManager: SessionManager) : ViewModel() {
    val TAG: String = "ProfileViewModel"

    init {
        Log.d(TAG, "ProfileViewModel: profile viewModel works fine")
    }

    /*
    * after login we already have a user stored in sessionManager
    * use it get current user
    * */
    fun getAuthenticatedUser(): LiveData<AuthResource<User>> = sessionManager.getAuthUser()

}