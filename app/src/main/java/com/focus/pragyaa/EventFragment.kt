package com.focus.pragyaa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.focus.pragyaa.Utils.EventAdapter
import com.focus.pragyaa.Utils.EventFirebaseDataClass
import com.focus.pragyaa.databinding.FragmentEventBinding
import com.google.firebase.database.*

class EventFragment : Fragment() {

    private lateinit var binding: FragmentEventBinding
    private lateinit var database: DatabaseReference
    private val eventList = mutableListOf<EventFirebaseDataClass>()
    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventBinding.inflate(inflater, container, false)

        database = FirebaseDatabase.getInstance().getReference("events")

        setupRecyclerView()

        fetchEvents()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.eventRecyclerCardview.layoutManager = LinearLayoutManager(requireContext())
        eventAdapter = EventAdapter(eventList) { event ->
            onEventClicked(event)
        }
        binding.eventRecyclerCardview.adapter = eventAdapter
    }

    private fun fetchEvents() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                eventList.clear()
                for (eventSnapshot in snapshot.children) {
                    val event = eventSnapshot.getValue(EventFirebaseDataClass::class.java)
                    event?.let { eventList.add(it) }
                }
                eventAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", error.message)
            }
        })
    }

    private fun onEventClicked(event: EventFirebaseDataClass) {
        val intent = Intent(requireContext(), ViewEventDetailActivity::class.java).apply {
            putExtra("eventName", event.eventName)
            putExtra("coverImageUrl", event.coverImageUrl)
            putExtra("eventDateDay", event.eventDateDay)
            putExtra("eventDateMonth", event.eventDateMonth)
            putExtra("eventDescription", event.eventDescription)
            putExtra("eventRegistrationLink", event.eventRegistrationLink)
        }
        startActivity(intent)
    }
}
