package com.albums.ialbums.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albums.ialbums.data.model.Album
import com.albums.ialbums.data.repository.IAlbumRepository
import com.albums.ialbums.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class AlbumViewModel (var repository: IAlbumRepository) : ViewModel() {

    var _albumList = MutableStateFlow<Resource<List<Album>>>(Resource.any())
    val albumList: StateFlow<Resource<List<Album>>>
        get() = _albumList

    /**
     *
     */
    fun getRemoteAlbumList(url: String) {
        viewModelScope.launch {
            repository.getAlbumList(url).collect {
                println(it)
                _albumList.value = it
            }
        }
    }

    /**
     *
     */

    fun insertRoomAlbum(item : Album) {
        println("insertRoomAlbum================")
        viewModelScope.launch{
            println(item)
            repository.insertRoomAlbum(item)
        }
    }

    /**
     *
     */


    fun getRoomAlbumList() {
        println("getRoomAlbumList======================")
        CoroutineScope(Dispatchers.IO).launch {
            repository.getRoomAlbumList().collect {
                println("it ---> "+it)
                _albumList.value = it
            }
        }
    }




}