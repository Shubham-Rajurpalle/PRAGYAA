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
import com.focus.pragyaa.Utils.About
import com.focus.pragyaa.Utils.AboutAdapter

import com.focus.pragyaa.Utils.Sponsor
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    private val eventList = mutableListOf<EventFirebaseDataClass>()
    private lateinit var eventAdapter: EventAdapter
    private lateinit var sponsorAdapter: SponsorAdapter
    private lateinit var sponsorList: ArrayList<Sponsor>
    private lateinit var aboutList:ArrayList<About>
    private  lateinit var aboutAdapter: AboutAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("events")
        // Set up the event recycler view
        setupRecyclerView()
        setupSponsorsRecyclerView()
        fetchEvents()
        setupAboutRecyclerView()



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

        binding.aboutRecyclerView.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.aboutRecyclerView.adapter = aboutAdapter

        binding.viewAllAbout.setOnClickListener{
            val intent = Intent(this,AboutActivity::class.java)
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
        val arr=ArrayList<Sponsor>();
        arr.add(Sponsor(R.drawable.image1));
        arr.add(Sponsor(R.drawable.image2));
        arr.add(Sponsor(R.drawable.image1));

        sponsorList = arr
        sponsorAdapter = SponsorAdapter(sponsorList)
        binding.sponsorsRecyclerView.adapter = sponsorAdapter
    }

    private fun setupAboutRecyclerView(){

        val arr=ArrayList<About>();
        arr.add(About(R.drawable.nobita,"Nobita","Head"))
        arr.add(About(R.drawable.nobita,"Nobita","Head"))
        arr.add(About(R.drawable.nobita,"Nobita","Head"))
        arr.add(About(R.drawable.nobita,"Nobita","Head"))
        arr.add(About(R.drawable.nobita,"Nobita","Head"))
        arr.add(About(R.drawable.nobita,"Nobita","Head"))

        aboutList = arr
        aboutAdapter = AboutAdapter(aboutList)
        binding.aboutRecyclerView.adapter = aboutAdapter
    }

}
