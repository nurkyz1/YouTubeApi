package com.geektech.youtubeapi.ui.playlistDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.youtubeapi.data.remote.model.Items
import com.geektech.youtubeapi.databinding.ItemDetailBinding
import com.geektech.youtubeapi.extentions.load

class PlaylistDetailAdapter(private val list: MutableList<Items> ):
RecyclerView.Adapter<PlaylistDetailAdapter.ViewHolder>(){
    private lateinit var binding: ItemDetailBinding
    private lateinit var onClickListener: OnClickListener

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
   binding = ItemDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return  list.size
    }
    inner class ViewHolder(binding: ItemDetailBinding) :RecyclerView.ViewHolder(binding.root){
        fun  onBind(items: Items){
            binding.ivPlaylist.load(items.snippet.thumbnails.default.url)
            binding.tvDetailTitle.text = items.snippet.title
            itemView.setOnClickListener {
                onClickListener.onClickListener(items)
            }
        }

    }
    interface OnClickListener{
        fun onClickListener(items: Items)
    }

}