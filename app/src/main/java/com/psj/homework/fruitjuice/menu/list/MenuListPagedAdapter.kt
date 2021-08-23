package com.psj.homework.fruitjuice.menu.list

import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aminography.redirectglide.GlideApp
import com.psj.homework.fruitjuice.database.entities.ItemMain
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.NumberFormat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.psj.homework.fruitjuice.common.paging.LiveDataPagedListBuilder

class MenuListPagedAdapter(
    private val listener: OnItemClickListener
) : PagedListAdapter<ItemMain, MenuListPagedAdapter.MenuItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = MenuItemViewHolder(parent, listener)

    override fun onBindViewHolder(
        holder: MenuItemViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<ItemMain>() {
                override fun areItemsTheSame(
                    oldItem: ItemMain,
                    newItem: ItemMain
                ) = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: ItemMain,
                    newItem: ItemMain
                ) = oldItem.toString() == newItem.toString()
            }
    }

    class MenuItemViewHolder(
        parent: ViewGroup,
        private val listener: OnItemClickListener,
        private val ui: MenuListItemUI = MenuListItemUI()
    ) : RecyclerView.ViewHolder(
        ui.createView(AnkoContext.create(parent.context, parent))
    ) {
        var itemId: Long? = null

        init {
            itemView.onClick { listener.onClickItemMain(itemId) }
        }

        fun bind(item: ItemMain?) = item?.let {
            this.itemId = item.id

            val commaSeparatedPrice = NumberFormat.getNumberInstance().format(item.price)

            ui.itemMainName.text = item.name
            ui.price.text = "₩$commaSeparatedPrice"

            GlideApp.with(ui.imageView)
                .load("${item.imageUrl}")
                .thumbnail(0.1f)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .fitCenter()
                .into(ui.imageView)
        }

    }

    interface OnItemClickListener {
        fun onClickItemMain(itemMainId: Long?)
    }

    interface MenuLiveDataBuilder
        : LiveDataPagedListBuilder<Long, ItemMain>
}