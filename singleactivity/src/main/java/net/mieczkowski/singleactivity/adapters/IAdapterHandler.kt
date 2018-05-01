package net.mieczkowski.singleactivity.adapters.baseAdapters

/**
 * Created by Josh Mieczkowski on 1/25/2018.
 */
interface IAdapterHandler<T> {

    fun removeItem(position: Int)

    fun removeItem(item: T)

    fun addItem(item: T, vararg position: Int)

    fun addItems(items: List<T>, vararg position: Int)

    fun updatedItem(item: T)

    fun updatedItem(position: Int)

    fun replaceItem(item: T)

    fun getItem(position: Int): T

    fun onItemClick(position: Int)

    fun itemSize(): Int
}