package com.focus.pragyaa

import SponsorAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.focus.pragyaa.Utils.Sponsor
import com.focus.pragyaa.databinding.ActivitySponsorBinding

class SponsorListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySponsorBinding
    private lateinit var sponsorAdapter: SponsorAdapter
    private lateinit var sponsorList: ArrayList<Sponsor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySponsorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sponsorList = arrayListOf(
            Sponsor(R.drawable.image1),
            Sponsor(R.drawable.image2)
        )
        sponsorAdapter = SponsorAdapter(sponsorList)
        binding.sponsorRecyclerCardview.layoutManager = LinearLayoutManager(this@SponsorListActivity,
            LinearLayoutManager.VERTICAL, false)
        binding.sponsorRecyclerCardview.adapter = sponsorAdapter
    }
}