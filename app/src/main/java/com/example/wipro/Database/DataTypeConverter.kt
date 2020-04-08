package com.example.wipro.Database

import androidx.room.TypeConverter
import com.example.wipro.Model.RowResponse
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import java.util.Collections.EMPTY_LIST
import kotlin.collections.ArrayList


class DataTypeConverter {
    val gson = Gson()
    @TypeConverter fun stringToList(data: String?): ArrayList<RowResponse> {
        if (data == null) {
            return EMPTY_LIST as ArrayList<RowResponse>
        }

        val listType = object : TypeToken<ArrayList<RowResponse>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter fun ListToString(someObjects: ArrayList<RowResponse>): String {
        return gson.toJson(someObjects)
    }
}