package com.example.bookssearch.Models

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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

class ApiResponse {
    @SerializedName("items")
    @Expose
    var booksList: List<Book>? = null
}