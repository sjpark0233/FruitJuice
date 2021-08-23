package com.psj.homework.fruitjuice.database.entities

data class BillDetailMainDto(
    val billNum: Long,
    val seq: Long,
    val itemMainId: Long,
    val name: String,
    val price: Int
)