package com.albums.ialbums.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.albums.ialbums.data.model.Album
import com.albums.ialbums.data.room.dao.AlbumDao

/*
    *This database class inherits from the Room Database class.
    * In GroceryDatabase class we will make an abstract method to get an
    * instance of DAO and further use this method from the DAO instance to interact with the database.
    *  See the below code to implement.
 */

@Database(entities = [Album::class], version = 1)
abstract class AlbumDatabase : RoomDatabase() {


     abstract fun getAlbumDao(): AlbumDao

    companion object {
        @Volatile
        private var instance: AlbumDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AlbumDatabase::class.java, "AlbumDatabase.db").build()
    }

}