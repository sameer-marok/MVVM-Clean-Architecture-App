package com.example.tmdbclient.presentation.movie

import android.os.Bundle
import android.util.Log
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
import com.example.tmdbclient.databinding.ActivityMovieBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMovieBinding

    @Inject // Injecting viewModel factory
    lateinit var factory:MovieViewModelFactory

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var adapter:MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Dependency injection
        (application as Injector).createMovieSubComponent()
            .inject(this)

        // Initialize viewModel
        movieViewModel = ViewModelProvider(this, factory)
            .get(MovieViewModel::class.java)

        /*val movieBasedOnName = movieViewModel.getMoviesBasedOnName("Hulk")
        movieBasedOnName.observe(this){
            Log.i("MyTag",it.toString())
        }*/

        initRecyclerView()

        // Setup click listener
        setupClickListeners()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater = menuInflater
        inflater.inflate(R.menu.update, menu)
        return true
    }

    private fun setupClickListeners(){
        // clear all button
        binding.clearButton.setOnClickListener {
            binding.etMovie.text?.clear()
        }

        // search button
        binding.searchButton.setOnClickListener{
            if (binding.etMovie.text.toString().isEmpty()){
                Toast.makeText(this, "No search data, fetching all popular movies", Toast.LENGTH_SHORT).show()
                displayPopularMovies()
            }
            else{
                val name = binding.etMovie.text.toString()
                displayMovieBasedOnName(name)
            }
        }
    }

    private fun displayMovieBasedOnName(name: String){
        binding.movieProgressBar.visibility = View.VISIBLE
        val responseLiveData = movieViewModel.getMoviesBasedOnName(name)
        responseLiveData.observe(this) {
            if (it != null) {
                if (it.isEmpty())
                    Toast.makeText(this, "No data available for \"$name\"", Toast.LENGTH_SHORT).show()
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.movieRecyclerView.smoothScrollToPosition(0)
                binding.movieProgressBar.visibility = View.GONE
            }
            else{
                binding.movieProgressBar.visibility = View.GONE
                Toast.makeText(this, "No data available!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initRecyclerView(){
        binding.movieRecyclerView.layoutManager = LinearLayoutManager(this)
        //assign adapter, MovieAdapter's value
        adapter = MovieAdapter()
        // set that adapter to Movie RECYCLER VIEW
        binding.movieRecyclerView.adapter = adapter
        displayPopularMovies()
    }

    private fun displayPopularMovies(){
        binding.movieProgressBar.visibility = View.VISIBLE
        val responseLiveData = movieViewModel.getMovies()
        responseLiveData.observe(this) {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.movieRecyclerView.smoothScrollToPosition(0)
                binding.movieProgressBar.visibility = View.GONE
            }
            else{
                binding.movieProgressBar.visibility = View.GONE
                Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateMovies(){
        binding.movieProgressBar.visibility = View.VISIBLE
        val responseLiveData = movieViewModel.updateMovieList()
        responseLiveData.observe(this) {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.movieRecyclerView.smoothScrollToPosition(0)
                binding.movieProgressBar.visibility = View.GONE
            }
            else{
                binding.movieProgressBar.visibility = View.GONE
                Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                updateMovies()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}
