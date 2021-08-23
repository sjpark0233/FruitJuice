package com.psj.homework.fruitjuice.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.psj.homework.fruitjuice.database.entities.BillDetailOption
import com.psj.homework.fruitjuice.database.entities.BillDetailOptionDto

@Dao
interface BillDetailOptionDao {

    @Query("DELETE FROM bill_detail_option")
    fun deleteAll()

    @Insert
    fun insert(billDetailOption: List<BillDetailOption>)

    @Query("SELECT a.billNum, a.seq, a.itemOptionId, b.name, a.quantity, b.price FROM bill_detail_option a JOIN item_option b ON a.itemOptionId = b.id WHERE a.billNum = :billNum")
    fun findByBillNum(billNum: Long): List<BillDetailOptionDto>
}