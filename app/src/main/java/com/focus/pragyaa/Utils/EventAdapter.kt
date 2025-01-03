package com.focus.pragyaa.Utils

import com.focus.pragyaa.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.focus.pragyaa.databinding.RecyclerViewCardBinding

class EventAdapter(
    private val events: List<EventFirebaseDataClass>,
    private val onEventClick: (EventFirebaseDataClass) -> Unit
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RecyclerViewCardBinding.bind(itemView)

        fun bind(event: EventFirebaseDataClass) {
            binding.cardTvEventName.text = event.eventName
            binding.tvEventDateDay.text = event.eventDateDay
            binding.tvEventDateMonth.text = event.eventDateMonth

            Glide.with(binding.eventCardRecyclerImageview.context)
                .load(event.coverImageUrl)
                .into(binding.eventCardRecyclerImageview)

            itemView.setOnClickListener {
                onEventClick(event)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_card, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size
}
