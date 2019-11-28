package com.github.minimalistic

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.nullgr.core.hardware.NetworkChecker
import com.nullgr.core.preferences.defaultPrefs
import com.nullgr.core.resources.ResourceProvider
import com.nullgr.core.rx.RxBus
import com.nullgr.core.rx.SingletonRxBusProvider
import com.nullgr.core.rx.schedulers.ComputationSchedulersFacade
import com.nullgr.core.rx.schedulers.IoToMainSchedulersFacade
import com.nullgr.core.rx.schedulers.SchedulersFacade
import dagger.Module
import dagger.Provides
import com.github.minimalistic.common.di.qualifires.ComputationFacade
import com.github.minimalistic.common.logger.ReleaseTree
import com.github.minimalistic.data.common.cache.ApplicationDatabase
import com.github.minimalistic.presentation.core.pm.ExceptionParser
import com.github.minimalistic.presentation.core.pm.SimpleExceptionParser
import timber.log.Timber
import javax.inject.Singleton

@Suppress("TooManyFunctions")
@Module
class AppModule(private val enableLog: Boolean) {

    @Singleton
    @Provides
    fun provideSchedulersFacade(): SchedulersFacade = IoToMainSchedulersFacade()

    @Singleton
    @Provides
    @ComputationFacade
    fun provideComputationSchedulersFacade(): SchedulersFacade = ComputationSchedulersFacade()

    @Singleton
    @Provides
    fun provideResourceProvider(context: Context): ResourceProvider = ResourceProvider(context)

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences = defaultPrefs(context)

    @Singleton
    @Provides
    fun provideNetworkChecker(context: Context): NetworkChecker = NetworkChecker(context)

    @Singleton
    @Provides
    fun provideRxBus(): RxBus = SingletonRxBusProvider.BUS

    @Provides
    @Singleton
    fun provideErrorParser(resources: ResourceProvider): ExceptionParser = SimpleExceptionParser()

    @Provides
    @Singleton
    fun provideLogTree(): Timber.Tree = if (enableLog) Timber.DebugTree() else ReleaseTree()

    @Provides
    @Singleton
    fun provideDatabase(context: Context): ApplicationDatabase =
        Room.databaseBuilder(context, ApplicationDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    companion object {
        private const val DB_NAME = "git_results.db"
    }
}