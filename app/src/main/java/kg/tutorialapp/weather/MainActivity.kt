package kg.tutorialapp.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kg.tutorialapp.weather.databinding.ActivityMainBinding
import kg.tutorialapp.weather.models.ForeCast
import kg.tutorialapp.weather.models.Post
import kg.tutorialapp.weather.network.PostsApi
import kg.tutorialapp.weather.network.WeatherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val httpClient by lazy {
        val interceptor =HttpLoggingInterceptor{}.apply { level = HttpLoggingInterceptor.Level.BODY }
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
    private val retrofit by lazy {
        Retrofit.Builder()
            //.baseUrl("https://api.openweathermap.org/data/2.5/")
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }
    private val weatherApi by lazy {
        retrofit.create(WeatherApi::class.java)
    }

    private val postsApi by lazy {
        retrofit.create(PostsApi::class.java)


    }

     lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //FatchWeather()
       // fetchWeatherUsingQuerry()
        //fetchPostById()
        //createPost()
        createPostUsingField()
       // updatePost()
        //deletePost()

        }

       /* private fun deletePost() {
        val call = postsApi.deletePost("id = 42")

        call.enqueue(object : Callback<Unit>{
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
               binding.textView.text = response.code().toString()
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {

            }

        }

                )

    }

    private fun updatePost() {
        val newPost = Post(userId = 20, title = "this is title", body = "this is body")
        val call = postsApi.putPost(42, post = newPost)

        call.enqueue(object: Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                val post = response.body()

                post?.let{
                    val resultText = "ID:" + it.id +"\n"+
                            "userID:" + it.userId  +"\n" +
                            "title:" + it.title  + " \n" +
                            "body:" + it.body  + "\n"

                    binding.textView.text =resultText
                }
            }


            override fun onFailure(call: Call<Post>, t: Throwable) {

            }

        })
    }*/
    private fun createPostUsingField() {

        val call = postsApi.createPostUsingFields(userId = 42, title = "Hi", body = "Osh")

        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                val post = response.body()

                post?.let {
                    val resultPost = "ID:" + it.id + "\n" +
                            "userID:" + it.userId + "\n" +
                            "title:" + it.title + " \n" +
                            "body:" + it.body + "\n"

                    binding.textView.text = resultPost
                }
            }


            override fun onFailure(call: Call<Post>, t: Throwable) {

            }

        })

    }
    private fun createPost() {
        val post = Post(userId = 42,title ="Hello",body ="BISHKEK")
        val call = postsApi.createPost(post)
        call.enqueue(object: Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                val post = response.body()

                post?.let{
                    val resultPost = "ID:" + it.id +"\n"+
                            "userID:" + it.userId  +"\n" +
                            "title:" + it.title  + " \n" +
                            "body:" + it.body  + "\n"

                    binding.textView.text =resultPost
                }
            }


            override fun onFailure(call: Call<Post>, t: Throwable) {

            }

        })
    }

    private fun fetchPostById() {
      val call = postsApi.fetchPostById(10)

        call.enqueue(object: Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                val post = response.body()

                post?.let{
                   val resultText = "ID:" + it.id +"\n"+
                    "userID:" + it.userId  +"\n" +
                    "title:" + it.title  + " \n" +
                    "body:" + it.body  + "\n"

                binding.textView.text = resultText
                }
            }


            override fun onFailure(call: Call<Post>, t: Throwable) {

            }

        })
    }

    private fun fetchWeatherUsingQuerry() {
        val call = weatherApi.fetchWeatherUsingQuerry()
        call.enqueue(object : Callback<ForeCast> {
            override fun onResponse(call: Call<ForeCast>, response: Response<ForeCast>) {
                if (response.isSuccessful) {
                    val foreCast = response.body()
                    foreCast?.let {

                        binding.textView.text = it.current?.weather!![0].description
                        binding.textView2.text = it.current?.temp?.toString()

                    }
                }
            }

            override fun onFailure(call: Call<ForeCast>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
            }


        })
    }

    private fun FatchWeather() {
        val call = weatherApi.getWeather()
        call.enqueue(object : Callback<ForeCast> {
            override fun onResponse(call: Call<ForeCast>, response: Response<ForeCast>) {
                if (response.isSuccessful) {
                    val foreCast = response.body()
                    foreCast?.let {

                        binding.textView.text = it.current?.weather!![0].description
                        binding.textView2.text = it.current?.temp?.toString()
                    }
                }
            }

            override fun onFailure(call: Call<ForeCast>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
            }


        })
    }


}