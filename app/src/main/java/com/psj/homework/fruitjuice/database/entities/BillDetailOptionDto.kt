package com.psj.homework.fruitjuice.database.entities

class BillDetailOptionDto(
    val billNum: Long,
    val seq: Long,
    val itemOptionId: Long,
    val name: String,
    val quantity: Int,
    val price: Int
)