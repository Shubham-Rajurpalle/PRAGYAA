package com.focus.pragyaa.Utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.focus.pragyaa.databinding.AboutcardBinding

class AboutAdapter(private val aboutList:List<About>):
RecyclerView.Adapter<AboutAdapter.AboutViewHolder>(){

    inner class AboutViewHolder(private val binding:AboutcardBinding):
            RecyclerView.ViewHolder(binding.root){

                fun bind(about:About){
                    binding.cardImage.setImageResource(about.image)
                    binding.textview1.text= about.text1
                    binding.textview2.text = about.text2
                    binding.cardImage.setOnClickListener{

                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutViewHolder {
        val binding = AboutcardBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return AboutViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return aboutList.size
    }

    override fun onBindViewHolder(holder: AboutViewHolder, position: Int) {
        holder.bind(aboutList[position])
    }

}