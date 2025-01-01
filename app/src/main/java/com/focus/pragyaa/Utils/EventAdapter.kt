package com.focus.pragyaa.Utils

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.focus.pragyaa.ViewEventDetailActivity
import com.focus.pragyaa.databinding.RecyclerViewCardBinding

class EventAdapter(private val events: List<EventDataClass>) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    class EventViewHolder(private val binding: RecyclerViewCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(event: EventDataClass) {
            binding.cardTvEventName.text = event.name
            binding.eventCardRecyclerImageview.setImageResource(event.imageResId)
            binding.tvEventDateDay.text = event.day
            binding.tvEventDateMonth.text = event.month


            binding.cardTvEventName.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, ViewEventDetailActivity::class.java).apply {
                    putExtra("EVENT_NAME", event.name)
                    putExtra("EVENT_IMAGE", event.imageResId)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = RecyclerViewCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size
}
