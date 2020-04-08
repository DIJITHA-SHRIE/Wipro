package com.example.wipro.Database

import androidx.room.*
import com.example.wipro.Model.DataResponse
import com.example.wipro.Model.RowResponse


@Dao
interface RoomDao {
    @Query("SELECT * FROM canada_offline_storage")
    fun getAll(): DataResponse

    @Query("SELECT COUNT(*) FROM canada_offline_storage")
    fun getCount(): Int

    @Insert
    fun insertAll(vararg todo: DataResponse)

    @Delete
    fun delete(todo: DataResponse)

    @Query("UPDATE canada_offline_storage SET title = :title, rows = :rows WHERE id = :id")
    fun updateTodo(title: String, rows: ArrayList<RowResponse>, id: Int)

}