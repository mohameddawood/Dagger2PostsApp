package com.google.firebase.udacity.daggerPosts.dagger.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.udacity.daggerPosts.R
import com.google.firebase.udacity.daggerPosts.Util
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/*
*
* Note that anything inside this class is valid to use it in entire the app
* */
@Module class AppModule {
    /*
    * after doing this u can use RequestOptions directly in ur activity without doing what exists in
    * fun provideRequestOptions{
    * }
    * */
    @Singleton @Provides fun provideRequestOptions(): RequestOptions {
        return RequestOptions.placeholderOf(R.drawable.ice_tick).error(R.drawable.ic_cross)
    }

    @Singleton @Provides fun providesGlideInstance(application: Application, requestOptions: RequestOptions): RequestManager {
        return Glide.with(application).setDefaultRequestOptions(requestOptions)
    }

    @Singleton @Provides fun provideAppDrawable(application: Application): Drawable {
        return ContextCompat.getDrawable(application, R.drawable.ice_tick)!!
    }


    /*
    * u can use retrofit anywhere u want after this method without any need to write method body
    * fun provideRetrofitInstance()
    *
    * just Retrofit.create(YOUR_INTERFACE_CLASS)
    * */
    @Singleton @Provides fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(Util.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}