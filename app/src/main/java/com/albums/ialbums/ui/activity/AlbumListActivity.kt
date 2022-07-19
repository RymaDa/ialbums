package com.albums.ialbums.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.albums.ialbums.R
import com.albums.ialbums.data.model.Album
import com.albums.ialbums.ui.adapter.AlbumItemAdapter
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
        setupView()
        fetchData()
        observeData()
        setupSearchBar()
    }

    /**
     * setup serach bar
     * keyword search: album id and title
     */
    private fun setupSearchBar() {
        search_editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                println("This filter"+s)
                val list  = (vm._albumList.value.data as? ArrayList<Album>) ?: ArrayList()
                val listFiltred = list.filter { it.albumId.toString().toUpperCase().contains(s.toString().toUpperCase())
                        || it.title.toString().toUpperCase().contains(s.toString().toUpperCase()) }
                initRecyclerView(ArrayList(listFiltred))
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun setupView() {
        activity_album_list_srl.setOnRefreshListener {
            fetchData()
        }

    }

    private fun observeData() {
        lifecycleScope.launch {
            vm._albumList.collect {
                it.let { resource ->
                    when (resource.status) {

                        Resource.Status.LOADING -> {}
                        Resource.Status.SUCCESS -> {
                            updateUI((it.data as? ArrayList<Album>) ?: ArrayList())
                            updateLocalDatabase((it.data as? ArrayList<Album>) ?: ArrayList())
                            activity_album_list_srl.isRefreshing = false
                        }
                        Resource.Status.ERROR -> {
                            vm.getRoomAlbumList()
                            activity_album_list_srl.isRefreshing = false
                        }
                        else -> {
                            activity_album_list_srl.isRefreshing = false
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
            initRecyclerView((vm._albumList.value.data as? ArrayList<Album>) ?: ArrayList())
        }else{
            empty_data_tv.visibility = View.VISIBLE
            activity_album_list_srl.visibility = View.GONE
        }
    }

    private fun initRecyclerView(data : ArrayList<Album>) {
        val linearLayoutManager = LinearLayoutManager(this)
        activity_album_list_rv.layoutManager = linearLayoutManager
        activity_album_list_rv.adapter = AlbumItemAdapter(this, data)
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