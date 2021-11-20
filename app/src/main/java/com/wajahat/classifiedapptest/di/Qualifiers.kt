package com.wajahat.classifiedapptest.di

import javax.inject.Qualifier

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class PhotoAPI

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CoroutineScopeIO