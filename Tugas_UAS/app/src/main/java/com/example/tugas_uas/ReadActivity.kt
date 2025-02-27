package com.example.tugas_uas

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Preview"

        val manhwaPhotoIndex = intent.getIntExtra("MANHWA_PHOTO_INDEX", 0)
        setupRecyclerView(manhwaPhotoIndex)
    }

    private fun setupRecyclerView(manhwaPhotoIndex: Int) {
        val recyclerView: RecyclerView = findViewById(R.id.rv_manhwa_pages)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val imageArray = when (manhwaPhotoIndex) {
            0 -> resources.obtainTypedArray(R.array.data_chapter_yeonwoo)
            1 -> resources.obtainTypedArray(R.array.data_chapter_operation_true_love)
            2 -> resources.obtainTypedArray(R.array.data_chapter_clothing_bin_of_love)
            3 -> resources.obtainTypedArray(R.array.data_chapter_meet_me_in_the_middle)
            4 -> resources.obtainTypedArray(R.array.data_chapter_couple_breaker)
            5 -> resources.obtainTypedArray(R.array.data_chapter_like_mother_like_daughter)
            6 -> resources.obtainTypedArray(R.array.data_chapter_my_lucky_strike)
            7 -> resources.obtainTypedArray(R.array.data_chapter_in_between)
            8 -> resources.obtainTypedArray(R.array.data_chapter_bad_word_baby)
            9 -> resources.obtainTypedArray(R.array.data_chapter_good_bad_fortune)
            else -> null
        }

        val images = mutableListOf<Int>()
        imageArray?.let {
            for (i in 0 until it.length()) {
                images.add(it.getResourceId(i, 0))
            }
            it.recycle()
        }

        val adapter = ManhwaPageAdapter(images)
        recyclerView.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
