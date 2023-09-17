package ru.artemaccount.movies.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.artemaccount.movies.MoviesApp
import ru.artemaccount.movies.data.MovieAdapter
import ru.artemaccount.movies.databinding.ActivityMainBinding
import ru.artemaccount.movies.model.Movie
import ru.artemaccount.movies.model.SearchResponse


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("MyTagBro", "onCreate!")

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.searchButton.setOnClickListener {
            binding.noValueText.visibility = View.GONE
            val film = binding.searchEt.text.toString()
            if (film.isBlank()) {
                binding.searchEt.error = "Please, enter value"
            } else {
                getMovies(film)
                currentFocus?.hideKeyboard()
            }
        }
    }

    private fun View.hideKeyboard() {
        val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(this.windowToken, 0)
    }


    private fun getMovies(film: String) {
        MoviesApp.movieService.listMovies(film).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {

                Log.d("myTagBro", "BODY IS ${response.body().toString()}")

                val movieList = response.body()?.search
                if (movieList == null) {
                    binding.noValueText.visibility = View.VISIBLE
                    adapter = MovieAdapter(emptyList(), this@MainActivity)
                } else {
                    adapter = MovieAdapter(movieList, this@MainActivity)
                }
                adapter.notifyDataSetChanged()
                binding.recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("myTagBro", t.stackTraceToString())
            }
        })
    }
}