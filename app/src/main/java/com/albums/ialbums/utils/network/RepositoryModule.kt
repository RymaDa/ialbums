package com.albums.ialbums.utils.network


import com.albums.ialbums.data.repository.AlbumRepository
import com.albums.ialbums.data.repository.IAlbumRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<IAlbumRepository> { AlbumRepository(get(), get(), get()) }



}
