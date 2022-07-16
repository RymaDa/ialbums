package com.albums.ialbums.utils.network


import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val ioDispatcherModule = module {

    single { Dispatchers.IO }
}

