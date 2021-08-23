package com.psj.homework.fruitjuice.menu.list

import androidx.paging.PageKeyedDataSource
import com.psj.homework.fruitjuice.database.FruitJuiceDB
import com.psj.homework.fruitjuice.database.entities.ItemMain
import kotlinx.coroutines.runBlocking

class MenuListItemDataSource(
) : PageKeyedDataSource<Long, ItemMain>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, ItemMain>
    ) {
        val response = getMenuItems()

        response?.let {
            if (it.isNotEmpty())
                callback.onResult(it, it.first().id, it.last().id)
        }
    }

    override fun loadBefore(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, ItemMain>
    ) {
    }

    override fun loadAfter(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, ItemMain>
    ) {
    }

    private fun getMenuItems() = runBlocking {
        FruitJuiceDB.getInstance()?.itemMainDao()?.findAllOrderByIdAsc()
    }

    companion object {
        private const val NEXT = "next"
        private const val PREV = "prev"
    }
}