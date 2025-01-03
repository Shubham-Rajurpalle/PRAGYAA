package com.focus.pragyaa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pragyaapp.ViewEventDetailActivity

import com.focus.pragyaa.Utils.EventAdapter
import com.focus.pragyaa.Utils.EventFirebaseDataClass
import com.focus.pragyaa.databinding.ActivityEventBinding
import com.google.firebase.database.*

class EventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventBinding
    private lateinit var database: DatabaseReference
    private val eventList = mutableListOf<EventFirebaseDataClass>()
    private lateinit var eventAdapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("events")

        setupRecyclerView()
        fetchEvents()
    }

    private fun setupRecyclerView() {
        binding.eventRecyclerCardview.layoutManager = LinearLayoutManager(this)
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
        val intent = Intent(this, ViewEventDetailActivity::class.java).apply {
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
