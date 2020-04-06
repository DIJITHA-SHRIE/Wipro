package com.example.infosys.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "canada_offline_storage")
data class DataResponse(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "title") var title: String,

    @ColumnInfo(name = "rows") var rows: ArrayList<RowResponse>
) : Serializable
