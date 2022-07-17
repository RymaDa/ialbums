package com.albums.ialbums.utils.network


import com.albums.ialbums.data.repository.AlbumRepository
import com.albums.ialbums.data.repository.IAlbumRepository
import com.albums.ialbums.data.room.dao.AlbumDao
import com.albums.ialbums.data.room.database.AlbumDatabase
import org.koin.dsl.module

val repositoryModule = module {

    fun albumDao(database: AlbumDatabase): AlbumDao {
        return database.getAlbumDao()
    }
    single<IAlbumRepository> { AlbumRepository(get(), get(), get(), get()) }
    single { albumDao(get()) }


}
