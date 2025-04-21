package com.raffiargianda.latfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.view.View.OnClickListener
import android.widget.TextView
import com.raffiargianda.latfragment.R

class NewsFragment : Fragment(), OnClickListener {
    private lateinit var btnNewsActivity: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        val description = arguments?.getString("description") ?: "Belum Ada Berita"
        val textNews = view.findViewById<TextView>(R.id.text_news_fragment)
        textNews.text = description
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnNewsActivity = view.findViewById(R.id.btn_news_activity)
        btnNewsActivity.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_news_activity) {
            val newsActivity = NewsActivity()
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(
                    R.id.frame_container, newsActivity,
                    NewsActivity::class.java.simpleName
                )
                addToBackStack(null)
                commit()
            }
        }
    }
}
