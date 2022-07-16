package com.albums.ialbums.data.api

import com.albums.ialbums.data.model.Album
import retrofit2.http.GET
import retrofit2.http.Url
import java.util.ArrayList

interface ApiService {

    @GET()
    suspend fun getAlbumList(@Url url: String): ArrayList<Album>
}