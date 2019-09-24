package com.google.firebase.udacity.daggerPosts.dagger.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.google.firebase.udacity.daggerPosts.R
import com.google.firebase.udacity.daggerPosts.dagger.ui.auth.AuthResource.AuthStatus
import com.google.firebase.udacity.daggerPosts.dagger.ui.main.MainActivity
import com.google.firebase.udacity.daggerPosts.dagger.di.daggerViewModelHandling.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject


class AuthActivity : DaggerAppCompatActivity() {
    private val TAG = "AuthActivity"
    private lateinit var viewModel: AuthViewModel
    @Inject lateinit var logo: Drawable // user the injected @Drawable class
    @Inject lateinit var requestManager: RequestManager  // user the injected @RequestManager
    @Inject lateinit var providerFactory: ViewModelProviderFactory // use it with viewModel to solve the problem with dagger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
        setLogo()
        observeUser()
    }

    private fun observeUser() {
        viewModel.observeAuthSatate().observe(this, Observer { userAuthResource ->
            if (userAuthResource != null) {
                when (userAuthResource.status) {
                    AuthStatus.ERROR -> {
                        showProgress(false)
                        Toast.makeText(this, userAuthResource.message + "", Toast.LENGTH_SHORT).show()
                    }
                    AuthStatus.LOADING -> {
                        showProgress(true)
                    }
                    AuthStatus.AUTHENTICATED -> {
                        Log.d(TAG, "observeUser: login success")
                        showProgress(false)
                        onLoginSuccess()
                    }
                    AuthStatus.NOT_AUTHENTICATED -> {
                        Log.d(TAG, "observeUser: " + "login failed ")
                        showProgress(false)
                    }
                }
            }
        })
    }

    private fun onLoginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setLogo() {
        requestManager.load(logo).into(login_logo)
    }

    private fun showProgress(isVisible: Boolean) {
        if (isVisible) progress_bar.visibility = View.VISIBLE else progress_bar.visibility = View.GONE

    }

    fun clickToLogin(view: View) {
        if (TextUtils.isEmpty(user_id_input.text.toString()))
            user_id_input.error = "insert a valid id !!"
        else
            viewModel.authenticateWithId(user_id_input.text.toString())
    }
}