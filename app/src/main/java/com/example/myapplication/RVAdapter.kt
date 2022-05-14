package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(val items: MutableList<String>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    private lateinit var mListener: onItemClickListener
    private lateinit var mlListener: onItemLongClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)

    }
    interface onItemLongClickListener {
        fun onItemLongClick(position: Int)
    }


    fun setOnItemClickListener(listener: onItemClickListener) {
        this.mListener = listener
    }

    fun setOnItemLongClickListener(llistener: onItemLongClickListener) {
        this.mlListener = llistener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)

        return ViewHolder(view,mListener,mlListener)
    }


    override fun onBindViewHolder(holder: RVAdapter.ViewHolder, position: Int) {

        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View,listener: onItemClickListener,llistener: onItemLongClickListener) : RecyclerView.ViewHolder(itemView) {
        val rv_text = itemView.findViewById<TextView>(R.id.rvItem)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
            itemView.setOnLongClickListener {
                llistener.onItemLongClick(adapterPosition)
                return@setOnLongClickListener true
            }

        }

        fun bindItems(item: String) {
            rv_text.text = item

        }

    }

    fun dataDelete(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

}




