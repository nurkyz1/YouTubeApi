package com.geektech.youtubeapi.ui.playlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.youtubeapi.R
import com.geektech.youtubeapi.databinding.ItemPlaylistBinding
import com.geektech.youtubeapi.extentions.load
import com.geektech.youtubeapi.data.remote.model.Items

class PlaylistAdapter (private  val playlist: ArrayList<Items>):
        RecyclerView.Adapter<PlaylistAdapter.ViewHolder>(){
            private  lateinit var clickListener:OnItemClickListener
            private lateinit var  binding: ItemPlaylistBinding

            fun setOnItemClickListener(listener: OnItemClickListener){
                clickListener=listener
            }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
      return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(playlist[position])
    }

    override fun getItemCount(): Int {
      return  playlist.size
    }
inner class  ViewHolder : RecyclerView.ViewHolder(this.binding.root){
    @SuppressLint("SetTextI18n")
    fun onBind(playlist: Items){
        binding.subTitle.text= playlist.contentDetails.itemCount.toString()+ itemView.context.getString(
            R.string.video_series)
        binding.imageView.load(playlist.snippet.thumbnails.default.url)
        binding.textTitle.text= playlist.snippet.title

        itemView.setOnClickListener {
            clickListener.onItemClick(playlist.id)
        }
    }
        }
     interface OnItemClickListener{
    fun onItemClick(id: String)
}}