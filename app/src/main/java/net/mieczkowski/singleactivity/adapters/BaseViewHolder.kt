package net.mieczkowski.singleactivity.adapters.baseAdapters

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Josh Mieczkowski on 1/25/2018.
 */
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var iAdapter: IAdapterHandler<T>

    init {
        if (adapterHandleOnClick()) {
            this.itemView.setOnClickListener { iAdapter.onItemClick(adapterPosition) }
        } else {
            this.itemView.setOnClickListener(null)
        }
    }

    protected open fun adapterHandleOnClick(): Boolean = true

    abstract fun bind(item: T?)
}