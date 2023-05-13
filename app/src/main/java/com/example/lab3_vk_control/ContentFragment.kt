package com.example.lab3_vk_control


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


public var isAutorize = false
class ContentFragment : Fragment(R.layout.fragment_content) {

    public var listOfFilms = mutableListOf<Movie>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FillDatabaseByFilms()
    }

    private fun FillDatabaseByFilms()
    {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val imdbApi = retrofit.create(ImdbApi::class.java)
        val call = imdbApi.MovieAllResponse(API_KEY)

        call.enqueue(object : Callback<MovieAllResponse> {
            @SuppressLint("Range")
            override fun onResponse(call: Call<MovieAllResponse>, response: Response<MovieAllResponse>) {
                if (response.isSuccessful) {
                    val top250MoviesResponse = response.body()
                    val movies = top250MoviesResponse?.items
                    if (movies != null) {
                        databaseHelper = DataBaseHelper(context!!)
                        for (movie in movies) {

                            // Проверяем, есть ли фильм уже в базе данных
                            if (!databaseHelper.checkIfMovieExists(movie.id)) {

                                databaseHelper.insertMovie(movie)

                            }
                        }

                        }

                        databaseHelper.close()
                    }
                }

            override fun onFailure(call: Call<MovieAllResponse>, t: Throwable) {
                val toast = Toast.makeText(context, "Ошибка api запроса", Toast.LENGTH_LONG)
                toast.show()
            }
        })

    }
    private val API_KEY = "k_yy8967dx"
    private val BASE_URL = "https://imdb-api.com/"
    interface ImdbApi {
        @GET("API/InTheaters/{apiKey}")
        fun MovieAllResponse(@Path("apiKey") apiKey: String): Call<MovieAllResponse>
        @GET("API/Title/{apiKey}/{movieId}")
        fun getMovieDescription(
            @Path("apiKey") apiKey: String,
            @Path("movieId") movieId: String,
            @Query("plot") plot: String = "full"
        ): Call<MovieDescriptionResponse>

    }

    data class MovieAllResponse(
        val items: List<Movie>
    )
    data class MovieDescriptionResponse(
        val id: String,
        val plot: String
    )
    private lateinit var databaseHelper: DataBaseHelper

    @SuppressLint("Range")
    private suspend fun fillist(adapter: RecycleAdapter)  {
        if(this.context!=null) databaseHelper = DataBaseHelper(this.context!!)
        val db = databaseHelper.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM movies", null)
        while (cursor.moveToNext()) {
            val id = cursor.getString(cursor.getColumnIndex("_id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val year = cursor.getString(cursor.getColumnIndex("year"))
            val plot = cursor.getString(cursor.getColumnIndex("description"))
            val image = cursor.getString(cursor.getColumnIndex("poster"))
            val data = Movie(id,name, year, image, plot)
            if (adapter != null) {

              listOfFilms.add(data)
                //adapter.addItem(data)
                adapter.notyy()
            }
           // delay(4000);


        }
        cursor.close()
        databaseHelper.close()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val recycle = view.findViewById<RecyclerView>(R.id.recycler)
       recycle.layoutManager = LinearLayoutManager(context)

        val container= requireActivity().findViewById<View>(R.id.fragment)

        val adapter = activity?.let {
            RecycleAdapter(listOfFilms ,
                it, container)
        }
        recycle.adapter = adapter

        GlobalScope.launch(Dispatchers.IO) {
            if (adapter != null) {
                fillist(adapter)
            };
        }



    }


}





