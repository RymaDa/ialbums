package com.albums.ialbums.data.api

import com.albums.ialbums.data.model.Album
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET()
    suspend fun getAlbumList(@Url url: String): List<Album>
}