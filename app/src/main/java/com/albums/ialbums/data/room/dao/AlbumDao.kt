package com.albums.ialbums.data.room.dao


import androidx.lifecycle.LiveData
import androidx.room.*
import com.albums.ialbums.data.model.Album
import com.albums.ialbums.utils.Resource


/*
The Dao is an interface in which we create all the functions that we want to implement on the database.
This interface also annotated with @Dao.
Now we will create a function using suspend function which is a coroutines function.
Here we create three functions, First is the insert function to insert items in the database and annotated with @Insert, Second is for deleting items from the database annotated with @Delete and Third is for getting all items annotated
 */

// This class is used to create
// function for database.

@Dao
interface AlbumDao {

    // Insert function is used to
    // insert data in database.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Album)

    // Delete function is used to
    // delete data in database.
    @Delete
    suspend fun delete(item: Album)

    // getAllAlbumItems function is used to get
    // all the data of database.
    @Query("SELECT * FROM album")
    fun getAllAlbumItems(): List<Album>

}