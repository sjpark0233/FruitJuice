package com.psj.homework.fruitjuice.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.psj.homework.fruitjuice.database.entities.ItemMainOptionMapping
import com.psj.homework.fruitjuice.database.entities.ItemOption

@Dao
interface ItemMainOptionMappingDao {

    @Insert
    fun insert(itemMainOptionMapping: List<ItemMainOptionMapping>)

    @Query("DELETE FROM item_main_option_mapping")
    fun deleteAll()
}