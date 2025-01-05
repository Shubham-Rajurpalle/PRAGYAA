package com.focus.pragyaa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.focus.pragyaa.databinding.ActivityViewEventBinding

class ViewSponsorDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the sponsor details from the intent
        val sponsorImageResId = intent.getIntExtra("sponsorImage", -1) // Default to -1 if not found

        // Set the sponsor data to the views
        if (sponsorImageResId != -1) {
            binding.viewEventCardImageview.setImageResource(sponsorImageResId)
        }

        // If there are additional actions (e.g., links), add them here
        binding.btnRegisterEvent.setOnClickListener {
            // Optional: Implement any sponsor-specific action
        }
    }
}
