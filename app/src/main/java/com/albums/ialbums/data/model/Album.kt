package com.albums.ialbums.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "album")
data class Album (@PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id")var id: Int?,
                  @ColumnInfo(name = "albumId")var albumId: Int?,
                  @ColumnInfo(name = "title")var title: String?,
                  @ColumnInfo(name = "url")var url: String?,
                  @ColumnInfo(name = "thumbnailUrl")var thumbnailUrl: String?): Serializable {

    constructor(): this(
        null,
        null,
        null,
        null,
        null
    )
}