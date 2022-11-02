package com.example.bookssearch.Repository

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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchBookRepo {
    private  val BOOK_SEARCH_SERVICE_BASE_URL = "https://www.googleapis.com/"
    var apiResponseMutableLiveData: MutableLiveData<ApiResponse?> = MutableLiveData()
    var isfinished: MutableLiveData<Boolean> = MutableLiveData()
    var bookSearchService: BookSearchService


    fun search(name: String?, author: String?) {
        isfinished.value = false
        bookSearchService.SearchApi(name, author).enqueue(object : Callback<ApiResponse?> {

            override fun onResponse(call: Call<ApiResponse?>, response: Response<ApiResponse?>) {
                if (response.body() != null) {

                    apiResponseMutableLiveData.postValue(response.body())
                    isfinished.postValue(true)
                }
            }
            override fun onFailure(call: Call<ApiResponse?>, t: Throwable) {
                apiResponseMutableLiveData.postValue(null)
                isfinished.postValue(true)
            }
        })
    }



    init {

//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        val client = OkHttpClient.Builder().build()
        bookSearchService = Retrofit.Builder()
            .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookSearchService::class.java)
    }
}