package ru.artemaccount.movies.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.artemaccount.movies.R
import ru.artemaccount.movies.model.Movie


class MovieAdapter(private val dataSet: List<Movie>, private val context: Context) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val year: TextView
        val poster: ImageView

        init {
            title = view.findViewById(R.id.title_tv)
            year = view.findViewById(R.id.year_tv)
            poster = view.findViewById(R.id.poster_iv)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.movie_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val movie = dataSet[position]
        viewHolder.title.text = movie.title
        viewHolder.year.text = movie.year

        val options: RequestOptions = RequestOptions()
            .centerInside()
            .placeholder(R.drawable.no_movie_48dp)
            .error(R.drawable.no_movie_48dp)
        Glide.with(context)
            .load(movie.posterUrl)
            .apply(options)
            .into(viewHolder.poster)
    }

    override fun getItemCount() = dataSet.size

}