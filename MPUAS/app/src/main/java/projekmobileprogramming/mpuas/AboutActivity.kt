package projekmobileprogramming.mpuas

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.card.MaterialCardView

class AboutActivity : AppCompatActivity() {

    private lateinit var cardView1: MaterialCardView
    private lateinit var cardView2: MaterialCardView
    private lateinit var showButton1: ImageButton
    private lateinit var showButton2: ImageButton
    private lateinit var hiddenLayout1: LinearLayout
    private lateinit var hiddenLayout2: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        cardView1 = findViewById(R.id.card1)
        cardView2 = findViewById(R.id.card2)
        showButton1 = findViewById(R.id.image_button)
        showButton2 = findViewById(R.id.image_button2)
        hiddenLayout1 = findViewById(R.id.layout_expand)
        hiddenLayout2 = findViewById(R.id.layout_expand2)

        showButton1.setImageResource(R.drawable.arrow_down)
        showButton2.setImageResource(R.drawable.arrow_down)
        hiddenLayout1.visibility = View.GONE
        hiddenLayout2.visibility = View.GONE

        setupToggle(cardView1, showButton1, hiddenLayout1)
        setupToggle(cardView2, showButton2, hiddenLayout2)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }

    private fun setupToggle(cardView: MaterialCardView, showButton: ImageButton, hiddenLayout: LinearLayout) {
        showButton.setOnClickListener {
            if (hiddenLayout.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView, AutoTransition())
                hiddenLayout.visibility = View.GONE
                showButton.setImageResource(R.drawable.arrow_down)
            } else {
                TransitionManager.beginDelayedTransition(cardView, AutoTransition())
                hiddenLayout.visibility = View.VISIBLE
                showButton.setImageResource(R.drawable.arrow_up)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
