package com.example.bookssearch.Adapters

import android.content.Context
import retrofit2.http.GET
import com.example.bookssearch.Models.ApiResponse
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.example.bookssearch.Models.BookInfo
import com.example.bookssearch.Models.VolumeImageLinks
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.bookssearch.R
import com.bumptech.glide.Glide
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.example.bookssearch.Api.BookSearchService
import androidx.lifecycle.LiveData
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import com.example.bookssearch.Repository.SearchBookRepo
import retrofit2.converter.gson.GsonConverterFactory
import androidx.lifecycle.AndroidViewModel
import androidx.appcompat.app.AppCompatActivity
import com.example.bookssearch.ViewModels.WordSearchViewModel
import com.example.bookssearch.Adapters.RecyclerViewAdapter
import android.widget.EditText
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookssearch.Models.Book
import java.util.ArrayList

class RecyclerViewAdapter(private val mContext: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var books: List<Book?>? = ArrayList()
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.list_1, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {

        // Set the name of each word
        (viewHolder as ViewHolder).book.text = books?.get(i)?.bookInfo?.title
        viewHolder.author.text = books?.get(i)?.bookInfo?.publisher
        if (books?.get(i)?.bookInfo?.imageLinks != null) {
            val imageUrl = books?.get(i)?.bookInfo?.imageLinks?.smallThumbnail
                ?.replace("http://", "https://")
            Glide.with(viewHolder.itemView)
                .load(imageUrl)
                .into(viewHolder.smallThumbNail)
        }
    }

    override fun getItemCount(): Int {
        return books!!.size
    }

    fun setBookList(Books: List<Book?>?) {
        books = Books
        notifyDataSetChanged()
    }

    private inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val book: TextView
        val author: TextView
        private val date: TextView? = null
        val smallThumbNail: ImageView

        init {
            book = itemView.findViewById(R.id.book_item_title)
            author = itemView.findViewById(R.id.book_item_authors)
            smallThumbNail = itemView.findViewById(R.id.book_item_smallThumbnail)
            //date = itemView.findViewById(R.id.da);
        }
    }
}