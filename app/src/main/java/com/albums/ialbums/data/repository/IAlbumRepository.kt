package com.albums.ialbums.data.repository

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.map
import com.albums.ialbums.data.api.ApiService
import com.albums.ialbums.data.model.Album
import com.albums.ialbums.data.room.database.AlbumDatabase
import com.albums.ialbums.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


interface IAlbumRepository {
    suspend fun getAlbumList(url: String) : Flow<Resource<List<Album>>>
    suspend fun getRoomAlbumList() : Flow<Resource<List<Album>>>
    suspend fun insertRoomAlbum(item: Album)
}

class AlbumRepository(
    private val api: ApiService,
    private val db: AlbumDatabase,
    private val context : Context,
    dispatcher : CoroutineDispatcher
) : IAlbumRepository {


    /**
     * Get list of albums from server
     * @param url de serveur
     * @return Album list flow
     */

    override suspend fun getAlbumList(url: String): Flow<Resource<List<Album>>> = flow{
        emit(Resource.loading())
        try{
            emit(Resource.success(api.getAlbumList(url)))
        } catch (err: Exception) {
            emit(Resource.error(err.message.toString(), null))
        }
    }


    /**
     * Get list of albums from room
     * @return Album list flow
     */
    @SuppressLint("CheckResult")
    override suspend fun getRoomAlbumList(): Flow<Resource<List<Album>>> = flow{
        emit(Resource.loading())
        try{
            println("get room data ")
            val a = db.getAlbumDao().getAllAlbumItems()
            a?.map {
                println("ite -> "+it)
            }
            println("a --> "+a)

            emit(Resource.success(a))
        } catch (err: Exception) {
            emit(Resource.error(err.message.toString(), null))
        }
    }

    /**
     *insert item to local database
     * @param item
     * @return flow
     */

    override suspend fun insertRoomAlbum(item:Album){

        try{
            println("INSERT================")
            db.getAlbumDao().insert(item)

        } catch (err: Exception) {
            println("SIZE ----->" + err.message.toString())
        }
    }


}