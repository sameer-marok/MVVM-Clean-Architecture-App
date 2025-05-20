package com.example.tmdbclient.presentation.tvshow

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
import com.example.tmdbclient.databinding.ActivityTvShowBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class TvShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTvShowBinding

    @Inject
    lateinit var factory:TvShowViewModelFactory

    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var adapter: TvShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Dependency injection
        (application as Injector).createTvShowSubComponent()
            .inject(this)

        // Initialize viewModel
        tvShowViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

        initRecyclerView()
        setupClickListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.update, menu)
        return true
    }

    private fun initRecyclerView(){
        binding.tvShowRecyclerView.layoutManager = LinearLayoutManager(this)

        adapter = TvShowAdapter()

        binding.tvShowRecyclerView.adapter = adapter
        displayPopularTvSHows()
    }

    private fun setupClickListeners(){
        binding.clearButton.setOnClickListener {
            binding.etTvShows.text?.clear()
        }

        binding.searchButton.setOnClickListener {
            if (binding.etTvShows.text.toString().isEmpty()){
                Toast.makeText(this, "No search data, fetching all popular Tv Shows", Toast.LENGTH_SHORT).show()
                displayPopularTvSHows()
            }
            else{
                val name  = binding.etTvShows.text.toString()
                displayTvShowsBasedOnName(name)
            }
        }
    }

    private fun displayTvShowsBasedOnName(name: String){
        binding.tvShowProgressBar.visibility = View.VISIBLE
        val responseLiveData = tvShowViewModel.getTvShowsBasedOnName(name)
        responseLiveData.observe(this) {
            if (it != null){
                if (it.isEmpty())
                    Toast.makeText(this, "No data available for \"$name\"", Toast.LENGTH_SHORT).show()
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.tvShowRecyclerView.smoothScrollToPosition(0)
                binding.tvShowProgressBar.visibility = View.GONE
            }
            else{
                binding.tvShowProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayPopularTvSHows(){
        binding.tvShowProgressBar.visibility = View.VISIBLE
        val responseLiveData = tvShowViewModel.getTvShows()
        responseLiveData.observe(this) {
            if (it != null){
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.tvShowRecyclerView.smoothScrollToPosition(0)
                binding.tvShowProgressBar.visibility = View.GONE
            }
            else{
                binding.tvShowProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateTvSHows(){
        binding.tvShowProgressBar.visibility = View.VISIBLE
        val responseLiveData = tvShowViewModel.updateTvShows()
        responseLiveData.observe(this) {
            if (it != null){
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.tvShowRecyclerView.smoothScrollToPosition(0)
                binding.tvShowProgressBar.visibility = View.GONE
            }
            else{
                binding.tvShowProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                updateTvSHows()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}