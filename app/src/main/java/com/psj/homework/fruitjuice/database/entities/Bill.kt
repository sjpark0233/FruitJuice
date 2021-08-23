package com.psj.homework.fruitjuice.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bill")
data class Bill(
    @ColumnInfo(name = "isPaid") var isPaid: String,
    @PrimaryKey var billNum: Long = 0
)