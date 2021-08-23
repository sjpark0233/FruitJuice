package com.psj.homework.fruitjuice.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.psj.homework.fruitjuice.database.dao.*
import com.psj.homework.fruitjuice.database.entities.*

@Database(version = 9, entities = [ItemMain::class, ItemMainOptionMapping::class, ItemOption::class, Bill::class, BillDetailMain::class, BillDetailOption::class])
abstract class FruitJuiceDB: RoomDatabase() {
    abstract fun itemMainDao(): ItemMainDao
    abstract fun itemMainOptionMappingDao(): ItemMainOptionMappingDao
    abstract fun itemOptionDao(): ItemOptionDao
    abstract fun billDao(): BillDao
    abstract fun billDetailMainDao(): BillDetailMainDao
    abstract fun billDetailOptionDao(): BillDetailOptionDao

    companion object {
        private  var instance: FruitJuiceDB? = null

        fun getInstance(context: Context): FruitJuiceDB? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, FruitJuiceDB::class.java, "fruit_juice.db")
                    .allowMainThreadQueries()
                    .build()
            }

            return instance
        }

        fun getInstance(): FruitJuiceDB? {
            return instance
        }
    }
}