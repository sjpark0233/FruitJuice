package com.psj.homework.fruitjuice.bill

import android.app.Application
import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.psj.homework.fruitjuice.database.FruitJuiceDB
import com.psj.homework.fruitjuice.menu.MenuMainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

class BillViewModel(app: Application) : BaseViewModel(app) {

    fun getBillDetailMain(billNum: Long) = FruitJuiceDB.getInstance()?.billDetailMainDao()?.findByBillNum(billNum)

    fun getBillDetailOption(billNum: Long) = FruitJuiceDB.getInstance()?.billDetailOptionDao()?.findByBillNum(billNum)

    fun doPay(billNum: Long) {
        FruitJuiceDB.getInstance()?.billDao()?.doPay(billNum)

        confirm("주문이 완료되었습니다.") {
            finishActivity()
            startActivity<MenuMainActivity>()
        }
    }
}