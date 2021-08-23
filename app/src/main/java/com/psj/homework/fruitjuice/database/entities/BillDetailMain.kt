package com.psj.homework.fruitjuice.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bill_detail_main")
data class BillDetailMain(
    @ColumnInfo(name = "billNum") var billNum: Long,
    @ColumnInfo(name = "seq") var seq: Long,
    @ColumnInfo(name = "itemMainId") var itemMainId: Long,
    @PrimaryKey(autoGenerate = true) var id: Long = 0
)