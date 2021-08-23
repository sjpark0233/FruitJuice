package com.psj.homework.fruitjuice.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.psj.homework.fruitjuice.database.entities.ItemMain

@Dao
interface ItemMainDao {

    @Query("SELECT * FROM item_main ORDER BY id ASC")
    fun findAllOrderByIdAsc(): List<ItemMain>

    @Query("SELECT * FROM item_main WHERE id = :id")
    fun findById(id: Long): ItemMain

    @Insert
    fun insert(itemMain: List<ItemMain>)

    @Query("DELETE FROM item_main")
    fun deleteAll()
}