package id.dafa.artspace

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private lateinit var artworkImage: ImageView
    private lateinit var artworkTitle: TextView
    private lateinit var artworkArtist: TextView
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button

    private val artworks = listOf(
        Artwork("Astronout Space", "Wallpaper 1", "2021", R.drawable.artwork1),
        Artwork("NASA Space Take OFF Moon", "Wallpaper 2", "2022", R.drawable.artwork2),
        Artwork("Planets Mountain Space", "wallpaper 3", "2023", R.drawable.artwork3),  // Gambar baru
        Artwork("Astronout Surfing in Space", "Wallpaper 4", "2024", R.drawable.artwork4)   // Gambar baru
    )

    private var currentArtworkIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Membuat layout utama secara programmatically
        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setBackgroundColor(Color.WHITE)
        }

        // Membuat ImageView untuk menampilkan gambar karya seni
        artworkImage = ImageView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                3f
            ).apply {
                setMargins(16, 16, 16, 16)
            }
            scaleType = ImageView.ScaleType.CENTER_CROP
        }

        // Membuat TextView untuk menampilkan judul karya seni
        artworkTitle = TextView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(16, 16, 16, 4)
            }
            textSize = 18f
            setTextColor(Color.BLACK)
        }

        // Membuat TextView untuk menampilkan nama artis dan tahun
        artworkArtist = TextView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(16, 4, 16, 16)
            }
            textSize = 16f
            setTextColor(Color.GRAY)
        }

        // Membuat tombol Previous
        previousButton = Button(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(16, 16, 16, 16)
            }
            text = "Previous"
        }

        // Membuat tombol Next
        nextButton = Button(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(16, 16, 16, 16)
            }
            text = "Next"
        }

        // Menambahkan semua view ke layout utama
        mainLayout.addView(artworkImage)
        mainLayout.addView(artworkTitle)
        mainLayout.addView(artworkArtist)

        val buttonLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }
        buttonLayout.addView(previousButton)
        buttonLayout.addView(nextButton)

        mainLayout.addView(buttonLayout)

        // Menampilkan layout utama
        setContentView(mainLayout)

        // Set karya seni awal
        displayArtwork()

        // Listener tombol Previous
        previousButton.setOnClickListener {
            currentArtworkIndex = if (currentArtworkIndex > 0) currentArtworkIndex - 1 else artworks.size - 1
            displayArtwork()
        }

        // Listener tombol Next
        nextButton.setOnClickListener {
            currentArtworkIndex = (currentArtworkIndex + 1) % artworks.size
            displayArtwork()
        }
    }

    private fun displayArtwork() {
        val currentArtwork = artworks[currentArtworkIndex]
        artworkImage.setImageResource(currentArtwork.imageRes)
        artworkTitle.text = currentArtwork.title
        artworkArtist.text = "${currentArtwork.artist} (${currentArtwork.year})"
    }
}

data class Artwork(val title: String, val artist: String, val year: String, val imageRes: Int)
