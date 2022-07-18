package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemBoardBinding

class BoardAdapter() : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {
    interface MyItemClickListener {
        fun onItemClick(position: Int)
        fun onLongClick(position: Int)
    }

    private lateinit var itemBoardBinding: ItemBoardBinding
    val boardAddActivity = BoardAddActivity()
    private lateinit var mItemClickListener: MyItemClickListener
    var boardList = mutableListOf<BoardData>()

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemBoardBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_board,
            parent,
            false)
        return ViewHolder(itemBoardBinding)
    }


    override fun getItemCount(): Int {
        return boardList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageView = holder.binding.itemImageview
        with(holder) {
            binding.title.text = boardList[position].title
            binding.writerContentTextview.text = boardList[position].writer
            binding.timeContentTextview.text = boardList[position].time
            binding.boardContentTextview.text = boardList[position].content
        }
        Glide.with(holder.binding.root).load(boardList[position].imgUri).into(imageView)


    }

    fun setData(newData: MutableList<BoardData>) {
        boardList = newData
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemBoardBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                mItemClickListener.onItemClick(adapterPosition)
            }
            itemView.setOnLongClickListener {
                mItemClickListener.onLongClick(adapterPosition)
                return@setOnLongClickListener true
            }


        }


    }


}

