package com.gulizartunc.sekertansiyon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gulizartunc.sekertansiyon.R

class GidaAdapter(private val gidaList: List<Gida>) :
    RecyclerView.Adapter<GidaAdapter.GidaViewHolder>() {

    class GidaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewIsim: TextView = itemView.findViewById(R.id.textViewIsim)
        val textViewKanSekeri: TextView = itemView.findViewById(R.id.textViewKanSekeri)
        val textViewTansiyon: TextView = itemView.findViewById(R.id.textViewTansiyon)
        val textViewTuketimOnerisi: TextView = itemView.findViewById(R.id.textViewTuketimOnerisi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GidaViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_gida, parent, false)
        return GidaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GidaViewHolder, position: Int) {
        val gida = gidaList[position] // Get the Gida object at the current position
        holder.textViewIsim.text = gida.isim  // Set the text of name TextView
        holder.textViewKanSekeri.text = gida.kanSekeriEtki  // Set the text of Kan Sekeri TextView
        holder.textViewTansiyon.text = gida.tansiyonEtki  // Set the text of Tansiyon TextView
        holder.textViewTuketimOnerisi.text = gida.tuketimOnerisi  // Set the text of Tuketim Onerisi TextView
    }

    override fun getItemCount(): Int {
        return gidaList.size  // Return the size of the gidaList
    }
}
