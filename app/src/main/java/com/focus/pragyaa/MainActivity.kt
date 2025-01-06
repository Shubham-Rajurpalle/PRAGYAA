package com.focus.pragyaa


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.focus.pragyaa.Utils.EventFirebaseDataClass
import com.focus.pragyaa.databinding.ActivityMainBinding
import com.focus.pragyaa.Utils.EventAdapter
import SponsorAdapter

import com.focus.pragyaa.Utils.Sponsor
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    private val eventList = mutableListOf<EventFirebaseDataClass>()
    private lateinit var eventAdapter: EventAdapter
    private lateinit var sponsorAdapter: SponsorAdapter
    private lateinit var sponsorList: ArrayList<Sponsor>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("events")

        // Set up the event recycler view
        setupRecyclerView()
        fetchEvents()
        setupSponsorsRecyclerView()


        // Horizontal event RecyclerView setup
        binding.activityMainEventHorizontalRecyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.activityMainEventHorizontalRecyclerview.adapter = eventAdapter

        binding.viewAllEvent.setOnClickListener {
            val intent = Intent(this, EventActivity::class.java)
            startActivity(intent)
        }

        binding.sponsorsRecyclerView.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.sponsorsRecyclerView.adapter = sponsorAdapter

        binding.viewAllSponsor.setOnClickListener{
            val intent = Intent(this,SponsorListActivity::class.java)
            startActivity(intent)
        }

    }


    private fun setupRecyclerView() {
        eventAdapter = EventAdapter(eventList) { event -> onEventClicked(event) }
        binding.activityMainEventHorizontalRecyclerview.adapter = eventAdapter
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
        val intent = Intent(applicationContext, ViewEventDetailActivity::class.java).apply {
            putExtra("eventName", event.eventName)
            putExtra("coverImageUrl", event.coverImageUrl)
            putExtra("eventDateDay", event.eventDateDay)
            putExtra("eventDateMonth", event.eventDateMonth)
            putExtra("eventDescription", event.eventDescription)
            putExtra("eventRegistrationLink", event.eventRegistrationLink)
        }
        startActivity(intent)
    }

    private fun setupSponsorsRecyclerView() {
        // Create sponsor list
        val sponsorList = listOf(
            Sponsor(R.drawable.image1),
            Sponsor(R.drawable.image2)
        )

        val sponsorAdapter = SponsorAdapter(sponsorList)

        binding.sponsorsRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = sponsorAdapter
        }
    }

}
