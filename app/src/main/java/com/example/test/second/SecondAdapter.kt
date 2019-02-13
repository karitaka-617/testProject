package com.example.test.second

import android.arch.lifecycle.LiveData
import android.content.Context
import android.databinding.adapters.TextViewBindingAdapter.setImeActionLabel
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.example.test.R
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.ViewGroup
import android.widget.TextView
import android.databinding.adapters.TextViewBindingAdapter.setText
import android.graphics.Color
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.main_fragment.view.*
import kotlin.random.Random


class RecyclerAdapter(context: Context, data: MutableList<String>, listener: OnRecyclerListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mData: MutableList<String> = data
    private var mContext: Context = context
    private var mListener: OnRecyclerListener = listener

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        // 表示するレイアウトを設定
        return ViewHolder(mInflater.inflate(R.layout.list_item, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        // データ表示
        if (mData.size > i) {
            //viewHolder.textView.text = mData[i]
            viewHolder.itemView.list_item_text.text = mData[i]
        }

        // クリック処理
        viewHolder.itemView.list_liner.setOnClickListener { v -> mListener.onRecyclerClicked(v, viewHolder.layoutPosition) }

        val red = random()
        val green = random()
        val blue = random()
//        Log.d("mvvmTest", "$red,$green,$blue")

        viewHolder.itemView.list_liner.setBackgroundColor(Color.rgb(red,green,blue))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    private fun random(): Int{
        val rand = Random
        return rand.nextInt(255)
    }

    // ViewHolder(固有ならインナークラスでOK)
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textView: TextView = itemView.findViewById(R.id.list_item_text)

    }
}