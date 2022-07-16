package com.albums.ialbums.data.repository

import android.content.Context
import com.albums.ialbums.data.api.ApiService
import com.albums.ialbums.data.model.Album
import com.albums.ialbums.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.ArrayList


interface IAlbumRepository {
    suspend fun getAlbumList(url: String) : Flow<Resource<ArrayList<Album>>>
}

class AlbumRepository(
    private val api : ApiService,
    private val context : Context,
    dispatcher : CoroutineDispatcher

) : IAlbumRepository {


    /**
     * Récupérer la liste des albums depuis le serveur
     * @param URL de serveur
     * @return Flow de la liste des albums
     */
    override suspend fun getAlbumList(url: String): Flow<Resource<ArrayList<Album>>> = flow{
        emit(Resource.loading())
        try{
            emit(Resource.success(api.getAlbumList(url)))
        } catch (err: Exception) {
            emit(Resource.error(err.message.toString(), null))
        }
    }




}