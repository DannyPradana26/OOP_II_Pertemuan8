package com.satria.oop_ii


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.satria.oop_ii.Database.Kopi
import kotlinx.android.synthetic.main.adapter_kopi.view.*

class KopiAdapter (private val AllKopi: ArrayList<Kopi>, private val listener: OnAdapterListener) : RecyclerView.Adapter<KopiAdapter.KopiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KopiViewHolder {
        return KopiViewHolder(
            LayoutInflater.from(parent.context).inflate( R.layout.adapter_kopi, parent, false)
        )
    }

    override fun getItemCount() = AllKopi.size

    override fun onBindViewHolder(holder: KopiViewHolder, position: Int) {
        val kopi = AllKopi[position]
        holder.view.text_merk.text = kopi.merk
        holder.view.text_merk.setOnClickListener {
            listener.onClick(kopi)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(kopi)
        }
    }

    class KopiViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Kopi>) {
        AllKopi.clear()
        AllKopi.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(kopi: Kopi)
        fun onDelete(kopi: Kopi)
    }
}