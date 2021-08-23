package com.psj.homework.fruitjuice.intro

import android.app.Activity
import android.os.Bundle
import com.psj.homework.fruitjuice.database.FruitJuiceDB
import com.psj.homework.fruitjuice.database.entities.ItemMain
import com.psj.homework.fruitjuice.database.entities.ItemMainOptionMapping
import com.psj.homework.fruitjuice.database.entities.ItemOption
import com.psj.homework.fruitjuice.menu.MenuMainActivity
import kotlinx.coroutines.*
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class IntroActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = IntroActivityUI()
        ui.setContentView(this)

        // DB 시작
        initDB()

        GlobalScope.launch(Dispatchers.Main) {
            delay(1500)

            startActivity<MenuMainActivity>()
        }
    }

    private fun initDB() {

        val fruitJuiceDB = FruitJuiceDB.getInstance(this)

        // 생과일주스 메인과일 세팅
        val itemMainList = listOf<ItemMain>(
            ItemMain(1, "오렌지", 4000, "https://picsum.photos/id/0/5616/3744"),
            ItemMain(2, "바나나", 3000, "https://picsum.photos/id/1/5616/3744"),
            ItemMain(3, "사과", 3500, "https://picsum.photos/id/100/2500/1656"),
            ItemMain(4, "멜론", 4000, "https://picsum.photos/id/1000/5626/3635"),
            ItemMain(5, "망고", 4500, "https://picsum.photos/id/1001/5616/3744"),
            ItemMain(6, "딸기", 5000, "https://picsum.photos/id/1003/1181/1772"),
            ItemMain(7, "당근", 4500, "https://picsum.photos/id/1004/5616/3744"),
            ItemMain(8, "수박", 4000, "https://picsum.photos/id/1005/5760/3840")
        )

        fruitJuiceDB?.itemMainDao()?.deleteAll()
        fruitJuiceDB?.itemMainDao()?.insert(itemMainList)

        // 추가과일 세팅
        val itemOptionList = listOf<ItemOption>(
            ItemOption(1, "딸기", 1500, "https://picsum.photos/id/1003/1181/1772"),
            ItemOption(2, "망고", 1000, "https://picsum.photos/id/1001/5616/3744"),
            ItemOption(3, "키위", 1500, "https://picsum.photos/id/1006/3000/2000"),
            ItemOption(4, "당근", 1000, "https://picsum.photos/id/1004/5616/3744"),
            ItemOption(5, "멜론", 2000, "https://picsum.photos/id/1000/5626/3635"),
            ItemOption(6, "바나나", 1000, "https://picsum.photos/id/1/5616/3744"),
            ItemOption(7, "사과", 1500, "https://picsum.photos/id/100/2500/1656"),
            ItemOption(8, "수박", 1500, "https://picsum.photos/id/1005/5760/3840")
        )

        fruitJuiceDB?.itemOptionDao()?.deleteAll()
        fruitJuiceDB?.itemOptionDao()?.insert(itemOptionList)

        // 메인과일-추가과일 관계 세팅
        val itemMainOptionMappingList = listOf<ItemMainOptionMapping>(
            ItemMainOptionMapping(1, itemMainList.get(1).id, itemOptionList.get(0).id),    // 바나나-딸기
            ItemMainOptionMapping(2, itemMainList.get(1).id, itemOptionList.get(1).id),    // 바나나-망고
            ItemMainOptionMapping(3, itemMainList.get(1).id, itemOptionList.get(2).id),    // 바나나-키위
            ItemMainOptionMapping(4, itemMainList.get(2).id, itemOptionList.get(3).id),    // 사과-당근
            ItemMainOptionMapping(5, itemMainList.get(2).id, itemOptionList.get(1).id),    // 사과-망고
            ItemMainOptionMapping(6, itemMainList.get(2).id, itemOptionList.get(4).id),    // 사과-멜론
            ItemMainOptionMapping(7, itemMainList.get(2).id, itemOptionList.get(7).id),    // 사과-수박
            ItemMainOptionMapping(8, itemMainList.get(3).id, itemOptionList.get(2).id),    // 멜론-키위
            ItemMainOptionMapping(9, itemMainList.get(3).id, itemOptionList.get(3).id),    // 멜론-당근
            ItemMainOptionMapping(10, itemMainList.get(4).id, itemOptionList.get(0).id),    // 망고-딸기
            ItemMainOptionMapping(11, itemMainList.get(4).id, itemOptionList.get(2).id),    // 망고-키위
            ItemMainOptionMapping(12, itemMainList.get(4).id, itemOptionList.get(3).id),    // 망고-당근
            ItemMainOptionMapping(13, itemMainList.get(4).id, itemOptionList.get(4).id),    // 망고-멜론
            ItemMainOptionMapping(14, itemMainList.get(4).id, itemOptionList.get(5).id),    // 망고-바나나
            ItemMainOptionMapping(15, itemMainList.get(4).id, itemOptionList.get(6).id),    // 망고-사과
            ItemMainOptionMapping(16, itemMainList.get(4).id, itemOptionList.get(7).id),    // 망고-수박
            ItemMainOptionMapping(17, itemMainList.get(5).id, itemOptionList.get(5).id),    // 딸기-바나나
            ItemMainOptionMapping(18, itemMainList.get(5).id, itemOptionList.get(1).id),    // 딸기-망고
            ItemMainOptionMapping(19, itemMainList.get(5).id, itemOptionList.get(6).id),    // 딸기-사과
            ItemMainOptionMapping(20, itemMainList.get(5).id, itemOptionList.get(4).id),    // 딸기-멜론
            ItemMainOptionMapping(21, itemMainList.get(6).id, itemOptionList.get(6).id),    // 당근-사과
            ItemMainOptionMapping(22, itemMainList.get(6).id, itemOptionList.get(0).id),    // 당근-딸기
            ItemMainOptionMapping(23, itemMainList.get(6).id, itemOptionList.get(1).id),    // 당근-망고
            ItemMainOptionMapping(24, itemMainList.get(7).id, itemOptionList.get(2).id),    // 수박-키위
            ItemMainOptionMapping(25, itemMainList.get(7).id, itemOptionList.get(5).id),    // 수박-바나나
        )

        fruitJuiceDB?.itemMainOptionMappingDao()?.deleteAll()
        fruitJuiceDB?.itemMainOptionMappingDao()?.insert(itemMainOptionMappingList)

        // 주문내역 초기화
        fruitJuiceDB?.billDao()?.deleteAll()
        fruitJuiceDB?.billDetailMainDao()?.deleteAll()
        fruitJuiceDB?.billDetailOptionDao()?.deleteAll()

    }
}