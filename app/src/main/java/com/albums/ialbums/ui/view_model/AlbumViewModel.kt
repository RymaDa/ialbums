package com.albums.ialbums.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albums.ialbums.data.model.Album
import com.albums.ialbums.data.repository.IAlbumRepository
import com.albums.ialbums.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class AlbumViewModel (var repository: IAlbumRepository) : ViewModel() {

    var _albumList = MutableStateFlow<Resource<ArrayList<Album>>>(Resource.any())
    val albumList: StateFlow<Resource<ArrayList<Album>>>
        get() = _albumList


    fun getRemoteAlbumList(url: String) {
        viewModelScope.launch {
            repository.getAlbumList(url).collect {
                println(it)
                _albumList.value = it
            }
        }
    }

}