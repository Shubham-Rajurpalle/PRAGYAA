package com.focus.pragyaa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.focus.pragyaa.Utils.EventFirebaseDataClass
import com.focus.pragyaa.databinding.ActivityMainBinding
import com.focus.pragyaa.Utils.EventAdapter
import com.focus.pragyaa.Utils.Sponsor
import com.focus.pragyaa.Utils.SponsorAdapter
//import com.focus.pragyaa.adapters.SponsorAdapter
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    private val eventList = mutableListOf<EventFirebaseDataClass>()
    private lateinit var eventAdapter: EventAdapter
    private val sponsorlist:ArrayList<Sponsor> = ArrayList()
    private lateinit var sponsorAdapter: SponsorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("events")

        // Set up the event recycler view
        setupRecyclerView()
        setupRecyclerView1()

        // Fetch data
        fetchsponsordata()
        fetchEvents()

        // Horizontal event RecyclerView setup
        binding.activityMainEventHorizontalRecyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.activityMainEventHorizontalRecyclerview.adapter = eventAdapter

        binding.viewAllEvent.setOnClickListener {
            val intent = Intent(this, EventActivity::class.java)
            startActivity(intent)
        }
    }

    // Handle the 'See All' button for Sponsors visibility toggle
    private fun toggleSponsorRecyclerViewVisibility() {
        if (binding.sponsorsRecyclerView.visibility == View.VISIBLE) {
            binding.sponsorsRecyclerView.visibility = View.GONE
        } else {
            binding.sponsorsRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun setupRecyclerView() {
        eventAdapter = EventAdapter(eventList) { event -> onEventClicked(event) }
        binding.activityMainEventHorizontalRecyclerview.adapter = eventAdapter
    }
    private fun setupRecyclerView1(){
        binding.sponsorsRecyclerView.layoutManager = LinearLayoutManager(this)
        sponsorAdapter = SponsorAdapter(sponsorlist){ sponsor -> onSponsorClicked(sponsor) }
        binding.sponsorsRecyclerView.adapter = sponsorAdapter
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
    private fun fetchsponsordata(){
        sponsorlist.add(Sponsor(R.drawable.image1))
        sponsorlist.add(Sponsor(R.drawable.image1))
        sponsorlist.add(Sponsor(R.drawable.image1))
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

    private fun onSponsorClicked(sponsor: Sponsor) {

        val intent = Intent(this, ViewSponsorDetailActivity::class.java).apply {
            putExtra("imageResource", sponsor.image)
        }
        startActivity(intent)
    }

}
