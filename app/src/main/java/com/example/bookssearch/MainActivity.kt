package com.example.bookssearch

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
import android.widget.Button
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {


    private var mProgressBar: ProgressBar? =  null
    private val mAdapter: RecyclerViewAdapter = RecyclerViewAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         val btn: Button = findViewById(R.id.search)
        val editText1: EditText = findViewById(R.id.book)
        val editText2: EditText= findViewById(R.id.author)
        mProgressBar =  findViewById(R.id.progress_bar)

        RecyclerviewInit()

        val wordSearchViewModel: WordSearchViewModel = ViewModelProvider(this).get(WordSearchViewModel::class.java)

        wordSearchViewModel.init()

        wordSearchViewModel.apiResponseLiveData?.observe(this) { apiResponse -> mAdapter.setBookList(apiResponse?.booksList) }

        wordSearchViewModel.isfinished?.observe(this) { aBoolean ->
            if (!aBoolean!!) {
                showProgressBar()
            } else {
                hideProgressBar()
            }
        }
        btn.setOnClickListener {
            wordSearchViewModel.search(
                editText1.text.toString(),  editText2.text.toString()
            )
        }
    }

    fun RecyclerviewInit() {
        val mRecyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val linearLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = linearLayoutManager
        mRecyclerView.adapter = mAdapter
    }

    private fun showProgressBar() {
        mProgressBar?.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        mProgressBar?.visibility = View.GONE
    }
}