package com.google.firebase.udacity.daggerPosts.dagger.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.udacity.daggerPosts.R
import com.google.firebase.udacity.daggerPosts.dagger.models.User
import com.google.firebase.udacity.daggerPosts.dagger.ui.auth.AuthResource.AuthStatus
import com.google.firebase.udacity.daggerPosts.dagger.di.daggerViewModelHandling.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

/*
* display user profile
* */

class ProfileFragment : DaggerFragment() {
    private val TAG = "ProfileFragment"
    private lateinit var viewModel: ProfileViewModel

    @Inject lateinit var providerFactory: ViewModelProviderFactory


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Toast.makeText(context, "Welcome to Our Land in -> ProfileFragment", Toast.LENGTH_SHORT).show()
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, providerFactory).get(ProfileViewModel::class.java)
        subscribeObservers()
    }

    /*
    * we use LiveData to observe any change happen to it without any new request
    * */
    private fun subscribeObservers() {
        /*
        * removeObservers() to make sure that there is no old observers stored to prevent any conflict
        * */
        viewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticatedUser().observe(this, Observer { userAuthResource ->
            if (userAuthResource != null) {
                when (userAuthResource.status) {
                    AuthStatus.AUTHENTICATED -> setUserDetails(userAuthResource.data)
                    AuthStatus.ERROR -> setErrorDetails(userAuthResource.message)
                }
            }

        })
    }

    private fun setErrorDetails(message: String?) {
        email.text = message
        username.text = "Error !!"
        website.text = "Error !!"
    }

    private fun setUserDetails(data: User?) {
        email.text = data?.email
        username.text = data?.username
        website.text = data?.website
    }

}
