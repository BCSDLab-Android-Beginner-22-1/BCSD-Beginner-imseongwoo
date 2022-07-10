package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBoardBinding

class BoardAdapter: RecyclerView.Adapter<BoardAdapter.ViewHolder>() {
    private lateinit var itemBoardBinding: ItemBoardBinding

    interface MyItemClickListener {
        fun onItemClick(position: Int)
        fun onLongClick(position: Int)
    }

    private lateinit var mItemClickListener: MyItemClickListener
    var boardList = mutableListOf<BoardData>()

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_board,parent,false)
//        return ViewHolder(view)
        itemBoardBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.item_board,parent,false)
        return ViewHolder(view,itemBoardBinding)
    }


    override fun getItemCount(): Int {
        return boardList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.title.text = boardList[position].title
//            boardTitle.text = boardList[position].title
            binding.writerContentTextview.text = boardList[position].writer
//            boardWriter.text = boardList[position].writer
            binding.boardContentTextview.text = boardList[position].content


        }
    }

    inner class ViewHolder(itemView: View,val binding:ItemBoardBinding) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                mItemClickListener.onItemClick(adapterPosition)
            }
            itemView.setOnLongClickListener {
                mItemClickListener.onLongClick(adapterPosition)
                return@setOnLongClickListener true
            }

        }
        val boardTitle : TextView = itemView.findViewById(R.id.title)
        val boardWriter : TextView = itemView.findViewById(R.id.writer_content_textview)
        val boardTime : TextView = itemView.findViewById(R.id.time_content_textview)
        val boardContent : TextView = itemView.findViewById(R.id.board_content_textview)

    }



}

