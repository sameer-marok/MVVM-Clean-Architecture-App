package com.example.tmdbclient.presentation.artist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ActivityArtistBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class ArtistActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArtistBinding

    @Inject
    lateinit var factory:ArtistViewModelFactory

    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var adapter: ArtistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artist)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Dependency injection
        (application as Injector).createArtistSubComponent()
            .inject(this)

        // initialize ViewModel
        artistViewModel = ViewModelProvider(this, factory)[ArtistViewModel::class]

        initRecyclerView()
        setupClickListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.update, menu)
        return true
    }

    private fun setupClickListeners(){
        // clear all button
        binding.clearButton.setOnClickListener {
            binding.etArtist.text?.clear()
        }

        binding.searchButton.setOnClickListener {
            if (binding.etArtist.text.toString().isEmpty()){
                Toast.makeText(this, "No search data, fetching all popular Artists", Toast.LENGTH_SHORT).show()
                displayPopularArtists()
            }
            else{
                val name = binding.etArtist.text.toString()
                displayArtistBasedOnName(name)
            }
        }
    }

    private fun displayArtistBasedOnName(name: String){
        binding.artistProgressBar.visibility = View.VISIBLE

        val responseLiveData = artistViewModel.getArtistsBasedOnName(name)
        responseLiveData.observe(this){
            if (it != null){
                if (it.isEmpty())
                    Toast.makeText(this, "No data available for \"$name\"", Toast.LENGTH_SHORT).show()
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.artistRecyclerView.smoothScrollToPosition(0)
                binding.artistProgressBar.visibility = View.GONE
            }
            else{
                binding.artistProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initRecyclerView(){
        binding.artistRecyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ArtistAdapter()

        binding.artistRecyclerView.adapter = adapter
        displayPopularArtists()
    }

    private fun displayPopularArtists(){
        binding.artistProgressBar.visibility = View.VISIBLE

        val responseLiveData = artistViewModel.getArtists()
        responseLiveData.observe(this){
            if (it != null){
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.artistRecyclerView.smoothScrollToPosition(0)
                binding.artistProgressBar.visibility = View.GONE
            }
            else{
                binding.artistProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updaterArtists(){
        binding.artistProgressBar.visibility = View.VISIBLE

        val responseLiveData = artistViewModel.updateArtists()
        responseLiveData.observe(this){
            if (it != null){
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.artistRecyclerView.smoothScrollToPosition(0)
                binding.artistProgressBar.visibility = View.GONE
            }
            else{
                binding.artistProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                updaterArtists()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}