package com.google.firebase.udacity.daggerPosts.dagger.di.daggerViewModelHandling;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

/*
 * to work with ViewModelFactory u need this scope class
 * just don't care about the written copy/past it
 * this is a solution for dagger/ViewModel problem
 * */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface ViewModelKey {
    Class<? extends ViewModel> value();
}
