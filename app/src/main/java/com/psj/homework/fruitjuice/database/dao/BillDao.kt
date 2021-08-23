package com.psj.homework.fruitjuice.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.psj.homework.fruitjuice.database.entities.Bill
import com.psj.homework.fruitjuice.database.entities.ItemMain

@Dao
interface BillDao {

    @Query("DELETE FROM bill")
    fun deleteAll()

    @Query("SELECT NULLIF(MAX(NULLIF(billNum, 0)), 0) FROM bill")
    fun findNewBillNum(): Long

    @Insert
    fun insert(bill: Bill)

    @Query("UPDATE bill SET isPaid = 'Y' WHERE billNum = :billNum")
    fun doPay(billNum: Long)
}