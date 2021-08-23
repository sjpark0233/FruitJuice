package com.psj.homework.fruitjuice.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.psj.homework.fruitjuice.database.entities.BillDetailMain
import com.psj.homework.fruitjuice.database.entities.BillDetailMainDto

@Dao
interface BillDetailMainDao {

    @Query("DELETE FROM bill_detail_main")
    fun deleteAll()

    @Insert
    fun insert(billDetailMain: BillDetailMain)

    @Query("SELECT a.billNum, a.seq, a.itemMainId, b.name, b.price FROM bill_detail_main a JOIN item_main b ON a.itemMainId = b.id WHERE a.billNum = :billNum")
    fun findByBillNum(billNum: Long): List<BillDetailMainDto>
}