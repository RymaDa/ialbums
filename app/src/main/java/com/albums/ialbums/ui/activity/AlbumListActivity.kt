package com.albums.ialbums.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.albums.ialbums.R
import com.albums.ialbums.data.model.Album
import com.albums.ialbums.ui.view_model.AlbumViewModel
import com.albums.ialbums.utils.Constants.Companion.ALBUM_LIST_URL
import com.albums.ialbums.utils.NetworkCheckUtils.Companion.isNetworkConnected
import com.albums.ialbums.utils.Resource
import kotlinx.android.synthetic.main.activity_album_list.*
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
                            updateUI((it.data as? ArrayList<Album>) ?: ArrayList())
                            updateLocalDatabase((it.data as? ArrayList<Album>) ?: ArrayList())
                        }
                        Resource.Status.ERROR -> {
                            vm.getRoomAlbumList()
                        }
                        else -> {
                        }
                    }
                }
            }
        }
    }


    private fun updateUI(data: ArrayList<Album>) {
        if (data.size>0){
            empty_data_tv.visibility = View.GONE
            activity_album_list_srl.visibility = View.VISIBLE
            initRecyclerView()
        }else{
            empty_data_tv.visibility = View.VISIBLE
            activity_album_list_srl.visibility = View.GONE
        }
    }

    private fun initRecyclerView() {

    }

    /**
     * update Room database
     * @param data  remote data
     */
    private fun updateLocalDatabase(data: ArrayList<Album>) {
        for (item in data){
            vm.insertRoomAlbum(item)
        }
    }

    /**
     *Fetch data
     *if the internet connection is activated => Fetch data from server
     *if album list from server is empty => Fetch data from room
     *if the internet connection is disabled => Fetch data from room
     */
    private fun fetchData() {
        if (isNetworkConnected(this)){
            println("INTERNET")
            vm.getRemoteAlbumList(ALBUM_LIST_URL)
        }else{
            vm.getRoomAlbumList()
            println("NO INTERNET")
        }


    }
}