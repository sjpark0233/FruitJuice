package com.psj.homework.fruitjuice.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_main")
data class ItemMain(
    @PrimaryKey var id: Long,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "price") var price: Int,
    @ColumnInfo(name = "imageUrl") var imageUrl: String
)