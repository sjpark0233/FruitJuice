package com.psj.homework.fruitjuice.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.psj.homework.fruitjuice.database.entities.ItemOption

@Dao
interface ItemOptionDao {

    @Insert
    fun insert(itemOption: List<ItemOption>)

    @Query("DELETE FROM item_option")
    fun deleteAll()

    @Query("SELECT a.* FROM item_option a JOIN item_main_option_mapping b ON a.id = b.item_option_id AND b.item_main_id = :itemMainId ORDER BY b.id ASC")
    fun findAllItemOptionByMainId(itemMainId: Long): List<ItemOption>
}