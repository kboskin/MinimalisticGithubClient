package com.github.minimalistic

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import com.github.minimalistic.data.di.ApiConstantsModule
import com.github.minimalistic.data.di.ApiModule
import com.github.minimalistic.data.di.DataSourceModule
import com.github.minimalistic.data.di.InterceptorModule
import com.github.minimalistic.data.di.MappersModule
import com.github.minimalistic.data.di.NetworkModule
import com.github.minimalistic.data.di.RepoModule
import com.github.minimalistic.data.di.StorageModule
import com.github.minimalistic.data.di.TokenModule
import com.github.minimalistic.presentation.di.ActivityBuilder
import com.github.minimalistic.presentation.di.FragmentBuilder
import com.github.minimalistic.presentation.di.NavigationModule
import com.github.minimalistic.presentation.di.PmModule
import timber.log.Timber
import javax.inject.Singleton

@Singleton
@Component(modules = [
    // common
    AndroidSupportInjectionModule::class,
    AppModule::class,
    // data
    ApiModule::class,
    ApiConstantsModule::class,
    NetworkModule::class,
    TokenModule::class,
    DataSourceModule::class,
    MappersModule::class,
    StorageModule::class,
    // domain
    RepoModule::class,
    // presentation
    PmModule::class,
    ActivityBuilder::class,
    FragmentBuilder::class,
    // navigation
    NavigationModule::class
    ])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun appModule(module: AppModule): Builder

        fun apiConstantsModule(module: ApiConstantsModule): Builder

        fun interceptorModule(module: InterceptorModule): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)

    fun context(): Context

    fun logTree(): Timber.Tree
}