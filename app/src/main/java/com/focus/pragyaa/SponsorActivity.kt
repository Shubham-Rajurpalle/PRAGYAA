package com.focus.pragyaa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.focus.pragyaa.Utils.Sponsor
import com.focus.pragyaa.Utils.SponsorAdapter
//import com.focus.pragyaa.adapters.SponsorAdapter
import com.focus.pragyaa.databinding.ActivityMainBinding

class SponsorActivity : AppCompatActivity() {

    private val sponsorlist :ArrayList<Sponsor> = ArrayList();
    private lateinit var sponsorAdapter: SponsorAdapter
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchsponsordata()
        setupRecyclerView1()


    }
    private fun fetchsponsordata(){
        sponsorlist.add(Sponsor(R.drawable.image1))
        sponsorlist.add(Sponsor(R.drawable.image1))
        sponsorlist.add(Sponsor(R.drawable.image1))
    }
    private fun setupRecyclerView1(){
        binding.sponsorsRecyclerView.layoutManager = LinearLayoutManager(this)
        sponsorAdapter = SponsorAdapter(sponsorlist) { sponsor -> onSponsorClicked(sponsor) }
        binding.sponsorsRecyclerView.adapter = sponsorAdapter
    }

    private fun onSponsorClicked(sponsor: Sponsor) {

        val intent = Intent(this, ViewSponsorDetailActivity::class.java).apply {
            putExtra("imageResource", sponsor.image)
        }
        startActivity(intent)
    }
}
