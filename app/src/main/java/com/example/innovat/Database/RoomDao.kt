package com.example.innovat.Database

import androidx.room.*
import com.example.innovat.Model.DataResponse
import com.example.innovat.Model.RowResponse


@Dao
interface RoomDao {

    @Query("SELECT * FROM canada_offline_storage")
    fun getAll(): RoomUser


    @Insert
    fun insertAll(vararg todo: RoomUser)

    @Delete
    fun delete(todo: RoomUser)

    @Update
    fun updateTodo(vararg todos: RoomUser)

}