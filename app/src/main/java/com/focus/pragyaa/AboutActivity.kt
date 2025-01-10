package com.focus.pragyaa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.focus.pragyaa.Utils.About
import com.focus.pragyaa.Utils.AboutAdapter
import com.focus.pragyaa.databinding.ActivityAboutBinding

class AboutActivity:AppCompatActivity() {
    private lateinit var binding:ActivityAboutBinding
    private lateinit var aboutAdapter: AboutAdapter
    private lateinit var aboutList:ArrayList<About>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        aboutList = arrayListOf(
            About(R.drawable.nobita,"Head","Nobita"),
            About(R.drawable.nobita,"Coordinator","Nobi"),
            About(R.drawable.nobita,"Head","Nobita")
        )
        aboutAdapter = AboutAdapter(aboutList)
        binding.aboutRecyclerCardview.layoutManager = LinearLayoutManager(this@AboutActivity,
            LinearLayoutManager.VERTICAL,false)
        binding.aboutRecyclerCardview.adapter = aboutAdapter
    }
    // jccucuedc
}