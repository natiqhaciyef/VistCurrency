package com.natiqhaciyef.kotlincryptocurrencyapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.natiqhaciyef.kotlincryptocurrencyapp.databinding.RecyclerRowItemBinding
import com.natiqhaciyef.kotlincryptocurrencyapp.model.CryptoModel
import kotlinx.android.synthetic.main.recycler_row_item.view.*

class CryptoAdapter(private val list: ArrayList<CryptoModel>) : RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {

    private val colorList = arrayListOf("#008000","#FFFFFF","#F98A59","#92A8D1","#F4D714","#AED2D1")

    class CryptoHolder(private val binding: RecyclerRowItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(cryptoModel: CryptoModel, colors: ArrayList<String>, position: Int){
            itemView.setBackgroundColor(Color.parseColor(colors[position % 6]))
            itemView.currency.text = cryptoModel.currency
            itemView.price.text = cryptoModel.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val binding = RecyclerRowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CryptoHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.bind(list[position], colorList, position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}