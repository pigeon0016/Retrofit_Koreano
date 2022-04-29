package com.example.retrofit_koreano

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_koreano.databinding.ItemEmgmedBinding

class EmgMedAdapter : ListAdapter<Row, EmgMedAdapter.EmgMedViewHolder>(EmgmedCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmgMedViewHolder {
        val binding = ItemEmgmedBinding.inflate(LayoutInflater.from(parent.context),parent , false)
        return EmgMedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmgMedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }



    class EmgMedViewHolder(private val binding: ItemEmgmedBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(item: Row){
          with(binding){
              TVNombre.text = item.nombreRest
              TVFundacion.text = item.fundacion
              RbCalificacion.rating = item.calificacion!!.toFloat()
              TVCosto.text = item.costoAv
              //IVRestaurante.setImageResource(resid)
          }

         }
      }
    companion object{
        private val EmgmedCallBack = object : DiffUtil.ItemCallback<Row>(){
            override fun areItemsTheSame(oldItem: Row, newItem: Row): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
             }

            override fun areContentsTheSame(oldItem: Row, newItem: Row): Boolean {
               return oldItem == newItem
            }
        }
    }
}