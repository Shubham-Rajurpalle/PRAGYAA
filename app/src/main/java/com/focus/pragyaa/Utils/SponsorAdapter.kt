package com.focus.pragyaa.Utils

import com.focus.pragyaa.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.focus.pragyaa.databinding.SponsorItemLayoutBinding

class SponsorAdapter(
    private val sponsors: ArrayList<Sponsor>,
    private val onSponsorClick: (Sponsor) -> Unit
) : RecyclerView.Adapter<SponsorAdapter.SponsorViewHolder>() {

    inner class SponsorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = SponsorItemLayoutBinding.bind(itemView)

        fun bind(sponsor: Sponsor) {
            Glide.with(binding.sponsorCardRecyclerImageview.context)
                .load(sponsor.image)
                .into(binding.sponsorCardRecyclerImageview)

            itemView.setOnClickListener {
                onSponsorClick(sponsor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SponsorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sponsor_item_layout, parent, false)
        return SponsorViewHolder(view)
    }

    override fun onBindViewHolder(holder: SponsorViewHolder, position: Int) {
        holder.bind(sponsors[position])
    }

    override fun getItemCount(): Int = sponsors.size
}
