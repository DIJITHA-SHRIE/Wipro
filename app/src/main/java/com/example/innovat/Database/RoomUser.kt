package com.example.innovat.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.innovat.Model.RowResponse

@Entity(tableName = "canada_offline_storage")
data class RoomUser(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "title") var title: String,


    @ColumnInfo(name = "rows") var rows:ArrayList<RowResponse>
)