package kg.tutorialapp.weather.network

import kg.tutorialapp.weather.models.ForeCast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET( "https://api.openweathermap.org/data/2.5/onecall?lat=42.87462&lon=74.56976&units=metric&appid=0572707574f371ae64536f0347516ed2")
    fun getWeather(): Call<ForeCast>

    @GET("onecall")
    fun fetchWeatherUsingQuerry(
        @Query("lat") lat: Double =42.87462,
        @Query("lon") lon: Double =74.56976,
        @Query("exclude") exclde:String ="minutly",
        @Query("appid") appid: String ="0572707574f371ae64536f0347516ed2",
        @Query("lang") lang: String ="ru",
        @Query("units") units: String ="metric"

    )
   : Call<ForeCast>
}