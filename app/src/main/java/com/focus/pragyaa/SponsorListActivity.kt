package com.focus.pragyaa

import SponsorAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.focus.pragyaa.Utils.Sponsor
import com.focus.pragyaa.databinding.ActivitySponsorBinding

class SponsorListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySponsorBinding
    private lateinit var sponsorAdapter: SponsorAdapter
    private lateinit var sponsorList:ArrayList<Sponsor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySponsorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        sponsorAdapter = SponsorAdapter(sponsorList)
        binding.sponsorRecyclerCardview.apply {
            layoutManager = LinearLayoutManager(this@SponsorListActivity)
            adapter = sponsorAdapter
        }

        // Load full list of sponsors
        val fullSponsorList = ArrayList<Sponsor>().apply {
            add(Sponsor(R.drawable.image1))
            add(Sponsor(R.drawable.image2))
            // Add all sponsors
        }
        sponsorAdapter.updateList(fullSponsorList)
    }
}