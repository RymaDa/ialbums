package com.albums.ialbums.utils.network

import com.albums.ialbums.ui.view_model.AlbumViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

   viewModel { AlbumViewModel(get()) }
}