package net.mieczkowski.singleactivity.adapters.baseAdapters

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * Created by Josh Mieczkowski on 1/25/2018.
 */
abstract class BaseAdapter<T>(itemsToAdd: MutableList<T>) : RecyclerView.Adapter<BaseViewHolder<T>>(), IAdapterHandler<T> {

    var items = itemsToAdd
        set(value) {
            field = value
            handleUpdatingItems()
            notifyDataSetChanged()
        }


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.iAdapter = this

        if (position >= items.size) {
            holder.bind(null)
        } else {
            holder.bind(items[position])
        }
    }

    fun getView(parent: ViewGroup, @LayoutRes layoutID: Int): View =
            LayoutInflater.from(parent.context).inflate(layoutID, parent, false)

    override fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun removeItem(item: T) {
        removeItem(items.indexOf(item))
    }

    override fun addItem(item: T, vararg position: Int) {
        var positionAdded = items.size

        if (position.isNotEmpty()) {
            if (position[0] > items.size) {
                items.add(item)
            } else {
                positionAdded = position[0]
                items.add(positionAdded, item)
            }
        } else {
            items.add(item)
        }

        notifyItemInserted(positionAdded)
    }

    override fun addItems(items: List<T>, vararg position: Int) {
        if (position.isNotEmpty()) {
            this.items.addAll(position[0], items)
            notifyItemRangeInserted(position[0], items.size)

        } else {
            val currentCount = itemCount
            this.items.addAll(items)
            notifyItemRangeInserted(currentCount, items.size)
        }
    }

    override fun updatedItem(item: T) {
        val position = items.indexOf(item)
        notifyItemChanged(position)
    }

    override fun updatedItem(position: Int) {
        notifyItemChanged(position)
    }

    override fun replaceItem(item: T) {
        val index = items.indexOf(item)
        items.removeAt(index)
        items.add(index, item)

        notifyItemChanged(index)
    }

    override fun getItem(position: Int): T = items[position]

    override fun onItemClick(position: Int) {
    }


    fun moveObjects(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(items, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(items, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    protected open fun handleUpdatingItems() {

    }

    override fun itemSize(): Int = itemCount
}