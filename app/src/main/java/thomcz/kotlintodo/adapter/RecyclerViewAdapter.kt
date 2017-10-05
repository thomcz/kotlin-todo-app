package thomcz.kotlintodo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import thomcz.kotlintodo.R
import thomcz.kotlintodo.data.Item

/**
 * Copyright (c) 2017
 * Created by Thomas Czogalik on 28.09.2017
 */

class RecyclerViewAdapter(private var items: List<Item>) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = items[position]
        holder.itemTitleView.text = item.title
        holder.itemDescView.text = item.description
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_list_item, parent, false))
    }

    fun addItems(items : List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val itemTitleView: TextView = view.findViewById(R.id.todo_item_title)
        val itemDescView: TextView = view.findViewById(R.id.todo_item_desc)
    }

}