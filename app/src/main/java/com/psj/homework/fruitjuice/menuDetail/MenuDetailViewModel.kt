package com.psj.homework.fruitjuice.menuDetail

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.psj.homework.fruitjuice.bill.BillActivity
import com.psj.homework.fruitjuice.database.FruitJuiceDB
import com.psj.homework.fruitjuice.database.entities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.codephobia.ankomvvm.databinding.addAll
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import java.text.NumberFormat

class MenuDetailViewModel(app: Application)
    : BaseViewModel(app)
{

    var itemMainId: Long? = null
    val itemMainName = MutableLiveData("-")
    val itemMainPrice = MutableLiveData("-")
    val itemMainImageUrl: MutableLiveData<MutableList<String>> = MutableLiveData(mutableListOf())


    fun loadDetail(id: Long) = viewModelScope.launch(Dispatchers.Main) {
        try {
            itemMainId = id

            val itemMain = getItemMain(id)
            updateViewData(itemMain)
        } catch (e: Exception) {
            toast(e.message ?: "알 수 없는 오류가 발생했습니다.")
        }
    }

    private fun getItemMain(id: Long) = FruitJuiceDB.getInstance()?.itemMainDao()?.findById(id)
    private fun getItemOptions(itemMainId: Long) = runBlocking {
        FruitJuiceDB.getInstance()?.itemOptionDao()?.findAllItemOptionByMainId(itemMainId)
    }

    private fun updateViewData(itemMain: ItemMain?) {
        val commaSeparatedPrice = NumberFormat.getInstance().format(itemMain?.price)

        itemMainName.value = itemMain?.name
        itemMainPrice.value = "₩${commaSeparatedPrice}"
        if (itemMain != null) {
            itemMainImageUrl.addAll(listOf<String>(itemMain.imageUrl))
        }
    }

    fun getOptionListData(id: Long) : List<ItemOption>? {
        return getItemOptions(id)
    }

    fun calculateBill(itemMainId: Long, itemOptionIds: List<Long>, itemOptionQuantities: List<TextView>) {

        // 주문내역 생성
        var newBillNum = FruitJuiceDB.getInstance()?.billDao()?.findNewBillNum()
        newBillNum = newBillNum?.plus(1L)
        Log.d("MenuDetailViewModel", "newBillNum : " + newBillNum.toString())

        newBillNum?.let { Bill("N", it) }?.let { FruitJuiceDB.getInstance()?.billDao()?.insert(it) }

        var seq = 1L
        newBillNum?.let { BillDetailMain(it, seq, itemMainId) }?.let {
            FruitJuiceDB.getInstance()?.billDetailMainDao()?.insert(
                it
            )
        }

        var index = 0
        var billDetailOptions: ArrayList<BillDetailOption> = arrayListOf()
        for (optionId in itemOptionIds) {
            var quantity = Integer.parseInt(itemOptionQuantities.get(index).text.toString())

            if (quantity > 0) {
                var billDetailOption =
                    newBillNum?.let { BillDetailOption(it, seq, optionId, quantity) }

                if (billDetailOption != null) {
                    billDetailOptions.add(billDetailOption)
                }
            }
            index++
        }
        FruitJuiceDB.getInstance()?.billDetailOptionDao()?.insert(billDetailOptions)

        startActivity<BillActivity> {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra(BillActivity.BILL_NUM, newBillNum)
        }

    }
}