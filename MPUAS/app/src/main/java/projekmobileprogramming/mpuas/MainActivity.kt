package projekmobileprogramming.mpuas

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvManhwa: RecyclerView
    private val list = ArrayList<Manhwa>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvManhwa = findViewById(R.id.rv_manhwa)
        rvManhwa.setHasFixedSize(true)
        list.addAll(getListManhwa())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }


    private fun getListManhwa(): ArrayList<Manhwa> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataGenre = resources.getStringArray(R.array.data_genre)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataDeskripsi = resources.getStringArray(R.array.data_deskripsi)
        val dataPenulis = resources.getStringArray(R.array.data_penulis)
        val dataRating = resources.getStringArray(R.array.data_rating)
        val listManhwa = ArrayList<Manhwa>()
        for (i in dataName.indices) {
            val ratingValue = dataRating[i].replace("Rating : ", "").replace(" / 10", "").toFloat() / 2
            val manhwa = Manhwa(dataName[i], dataGenre[i], dataDeskripsi[i], dataPenulis[i], ratingValue ,dataPhoto.getResourceId(i, -1))
            listManhwa.add(manhwa)
        }
        dataPhoto.recycle()
        return listManhwa
    }

    private fun showRecyclerList() {
        rvManhwa.layoutManager = LinearLayoutManager(this)
        val listManhwaAdapter = ListManhwaAdapter(list)
        rvManhwa.adapter = listManhwaAdapter

        listManhwaAdapter.setOnItemClickCallback(object : ListManhwaAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Manhwa, position: Int) { // pastikan menerima posisi
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra("MANHWA_DATA", data)
                    putExtra("POSITION", position) // mengirim posisi
                }
                startActivity(intentToDetail)
            }
        })
    }

}

