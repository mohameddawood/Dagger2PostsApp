package com.google.firebase.udacity.daggerPosts.dagger.ui.main.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.udacity.daggerPosts.dagger.SessionManager
import com.google.firebase.udacity.daggerPosts.dagger.models.Post
import com.google.firebase.udacity.daggerPosts.dagger.network.main.MainApi
import com.google.firebase.udacity.daggerPosts.dagger.ui.main.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/*
* all of dagger classes that we built it we can use it now easily
*   Two ways to use the injected classes
*   1 - @Inject lateinint var api:AuthApi
*   2 - inject it in the constructor    EX @Inject constructor(var authApi: AuthApi)
* */
class PostsViewModel @Inject constructor(var sessionManager: SessionManager, var mainApi: MainApi) : ViewModel() {
    private val disposable = CompositeDisposable()
    private var postsTest: MutableLiveData<List<Post>> = MutableLiveData()
    private val TAG: String = "PostsViewModelTag"


    init {
        Log.d(TAG, "PostsViewModel: done")
    }

    /*
    * api request to get user posts
    * */
    fun observeMyPosts(): LiveData<List<Post>> {
        disposable.add(mainApi.getPosts(sessionManager.getAuthUser().value?.data!!.id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    postsTest.value = it
                })
        return postsTest
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
        disposable.dispose()
    }
}