package com.raffiargianda.latfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class HomeFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnCategory: Button = view.findViewById(R.id.btn_category)
        val btnNews: Button = view.findViewById(R.id.btn_news) // Tugas
        btnNews.setOnClickListener(this) // Tugas
        btnCategory.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_category) {
            val categoryFragment = CategoryFragment()
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(R.id.frame_container, categoryFragment,
                    CategoryFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
        // Tugas
        if (v?.id == R.id.btn_news) {
            val newsFragment = NewsFragment()
            val bundle = Bundle()
            val fragmentManager = parentFragmentManager
            bundle.putString("description", "Belum Ada Berita")
            newsFragment.arguments = bundle
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frame_container, newsFragment, NewsFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }
}