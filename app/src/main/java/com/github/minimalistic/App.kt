package com.github.minimalistic

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import okhttp3.logging.HttpLoggingInterceptor
import com.github.minimalistic.data.di.ApiConstantsModule
import com.github.minimalistic.data.di.InterceptorModule
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var logTree: Timber.Tree

    override fun onCreate() {
        super.onCreate()
        initializeInjector()
        initializeLogger()
        initializeTime()
        initializeIfNeedStetho()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    private fun initializeInjector() {
        DaggerAppComponent
            .builder()
            .context(this)
            .appModule(AppModule(BuildConfig.IS_LOG_ENABLED))
            .apiConstantsModule(ApiConstantsModule())
            .interceptorModule(InterceptorModule(App::class.java.simpleName, HttpLoggingInterceptor.Level.BODY))
            .build()
            .inject(this)
    }

    private fun initializeLogger() {
        Timber.plant(logTree)
    }

    private fun initializeTime() {
        AndroidThreeTen.init(this)
    }

    private fun initializeIfNeedStetho() {
        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
    }
}