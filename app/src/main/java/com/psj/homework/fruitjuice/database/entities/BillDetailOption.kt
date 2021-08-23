package com.psj.homework.fruitjuice.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bill_detail_option")
data class BillDetailOption(
    @ColumnInfo(name = "billNum") var billNum: Long,
    @ColumnInfo(name = "seq") var seq: Long,
    @ColumnInfo(name = "itemOptionId") var itemOptionId: Long,
    @ColumnInfo(name = "quantity") var quantity: Int,
    @PrimaryKey(autoGenerate = true) var id: Long = 0
)