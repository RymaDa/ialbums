package com.albums.ialbums.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.albums.ialbums.R
import com.albums.ialbums.ui.view_model.AlbumViewModel
import com.albums.ialbums.utils.Constants.Companion.ALBUM_LIST_URL
import com.albums.ialbums.utils.Resource
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.getViewModel




class AlbumListActivity : AppCompatActivity() {

    private val vm: AlbumViewModel
        get() = getViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_list)
        fetchData()
        observeData()
    }
    private fun observeData() {
        lifecycleScope.launch {
            vm._albumList.collect {
                it.let { resource ->
                    when (resource.status) {

                        Resource.Status.LOADING -> {
                        }
                        Resource.Status.SUCCESS -> {
                            println("SUCCESS---------------------------")
                            println(it.data)
                        }
                        Resource.Status.ERROR -> {

                        }
                        else -> {

                        }
                    }
                }
            }
        }
    }
    private fun fetchData() {
        vm.getRemoteAlbumList(ALBUM_LIST_URL)
    }
}