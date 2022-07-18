package com.albums.ialbums.ui.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albums.ialbums.R
import com.albums.ialbums.data.model.Album
import com.albums.ialbums.ui.activity.AlbumListActivity


class AlbumItemAdapter(var context: Context,
                        var items: ArrayList<Album>? = ArrayList()):
    RecyclerView.Adapter<AlbumItemAdapter.RowViewHolder>(){


    class RowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        //val name: TextView = itemView.findViewById(R.id.item_client_name)


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


    private fun prepareData(holder: RowViewHolder,  client: Album) {

       // holder.name.text = if (client.clientName != null) Html.fromHtml("<b><font color='black'>Nom Client : </font></b>"+(client.clientName ?: "-")) else  "-"
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