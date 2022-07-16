package com.albums.ialbums.data.model

import java.io.Serializable


data class Album (var id: Int? ,
                  var albumId: Int? ,
                  var title: String? ,
                  var url: String? ,
                  var thumbnailUrl: String?): Serializable {

    constructor(): this(
        null,
        null,
        null,
        null,
        null
    )
}