package com.example.test.second

import android.support.v7.util.DiffUtil

class ListDataDiff(
    private val old: MutableList<String>,
    private val new: MutableList<String>
) : DiffUtil.Callback(){
    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition] == new[newPosition]
    }

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition] == new[newPosition]
    }

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }
}

class AsyncData(
    private val old: MutableList<String>,
    private val new: MutableList<String>
) : DiffUtil.ItemCallback<MutableList<String>>() {
    override fun areItemsTheSame(old: MutableList<String>, new: MutableList<String>): Boolean {
        return old == new
    }

    override fun areContentsTheSame(old: MutableList<String>, new: MutableList<String>): Boolean {
        return old == new
    }
}