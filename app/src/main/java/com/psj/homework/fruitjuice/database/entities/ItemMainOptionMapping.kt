package com.psj.homework.fruitjuice.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_main_option_mapping")
data class ItemMainOptionMapping(
    @PrimaryKey var id: Long,
    @ColumnInfo(name = "item_main_id") var itemMainId: Long,
    @ColumnInfo(name = "item_option_id") var itemOptionId: Long
)