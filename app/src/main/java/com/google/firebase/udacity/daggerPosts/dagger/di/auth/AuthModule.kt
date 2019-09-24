package com.google.firebase.udacity.daggerPosts.dagger.di.auth

import com.google.firebase.udacity.daggerPosts.dagger.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/*
* this module for login process
* i tell dagger that i need AuthApi to be injected to use it in AuthActivity to login
*
* authApi.myFunctionToLogin(parameters)
* */
@Module
class AuthModule {
    /*
    * as we said i can use retrofit after create it in AppModule as u will see now
    * providesMainApi():AuthApi u tell dagger that u wanna authApi object with u in a place
    * to use it as an injected object
    * */
    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}