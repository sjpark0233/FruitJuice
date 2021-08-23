package com.psj.homework.fruitjuice.menu.list

import android.app.Application
import android.content.Intent
import androidx.paging.DataSource
import com.psj.homework.fruitjuice.database.FruitJuiceDB
import com.psj.homework.fruitjuice.database.entities.ItemMain
import com.psj.homework.fruitjuice.database.entities.ItemOption
import com.psj.homework.fruitjuice.menuDetail.MenuDetailActivity
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error

class MenuListViewModel(app: Application)
    : BaseViewModel(app),
    MenuListPagedAdapter.MenuLiveDataBuilder,
    MenuListPagedAdapter.OnItemClickListener
{

    var categoryId: Int = -1
    val itemMains = buildPagedList()

    override fun createDataSource(): DataSource<Long, ItemMain> {
        if (categoryId == -1)
            error(
                "categoryId가 설정되지 않았습니다.",
                IllegalStateException("categoryId is -1")
            )

        return MenuListItemDataSource()
    }

    override fun onClickItemMain(itemMainId: Long?) {

        startActivity<MenuDetailActivity> {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra(MenuDetailActivity.ITEM_MAIN_ID, itemMainId)
        }
    }
}