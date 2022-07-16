package com.albums.ialbums.utils.network

import androidx.multidex.MultiDexApplication
import com.albums.ialbums.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

import timber.log.Timber

class IAlbumApplication : MultiDexApplication() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@IAlbumApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                    ioDispatcherModule
                )
            )
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

}