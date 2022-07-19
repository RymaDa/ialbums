package com.albums.ialbums.ui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.albums.ialbums.R
import com.albums.ialbums.data.model.Album
import com.albums.ialbums.ui.activity.AlbumListActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders


class AlbumItemAdapter(var context: Context,
                        var items: ArrayList<Album>? = ArrayList()):
    RecyclerView.Adapter<AlbumItemAdapter.RowViewHolder>(){


    class RowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        val image: ImageView = itemView.findViewById(R.id.album_item_adapter_img)
        val category: TextView = itemView.findViewById(R.id.album_item_adapter_categ)
        val title: TextView = itemView.findViewById(R.id.album_item_adapter_title)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_album, parent, false)

        return RowViewHolder(item)
    }


    override fun getItemCount(): Int {
        return if (!items.isNullOrEmpty())
            items?.size!!
        else
            return 0
    }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        val currentItem: Album = items?.get(position) ?: Album()
        prepareData(holder, currentItem)
        setupEvent(holder, currentItem)
    }


    private fun prepareData(holder: RowViewHolder,  item: Album) {

        holder.title.text = item.title
        holder.category.text = "Album n° "+ item.albumId.toString()
        val url = GlideUrl(
            item.url, LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build()
        )
        Glide.with(context)
            .load(url)
            .placeholder(ColorDrawable(Color.GRAY))
            .centerCrop()
            .into(holder.image)
    }
    private fun setupEvent(holder: AlbumItemAdapter.RowViewHolder, client: Album) {
        holder.itemView.setOnClickListener {
            onClick(client)
        }
    }

    private fun onClick(item: Album) {
        if (context is AlbumListActivity) {
         ///   (context as AlbumListActivity).showClientDetails(item)

        }
    }

}