package com.psj.homework.fruitjuice.menuDetail

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.aminography.redirectglide.GlideApp
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.imageView

class ImageSliderAdapter : PagerAdapter() {

    var imageUrls: List<String> = listOf()

    override fun isViewFromObject(view: View, obj: Any) = view == obj

    override fun getCount() = 1

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = AnkoContext.create(container.context, container)
            .imageView().apply {
                GlideApp.with(this)
                    .load("${imageUrls[position]}")
                    .into(this)
            }

        container.addView(view)

        return view
    }

    fun updateItems(items: MutableList<String>) {
        imageUrls = items
        notifyDataSetChanged()
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        obj: Any
    ) {
        container.invalidate()
    }
}