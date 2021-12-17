package kg.tutorialapp.weather.network

import kg.tutorialapp.weather.models.ForeCast
import retrofit2.Call
import retrofit2.http.GET

interface WeatherApi {
    @GET( "https://api.openweathermap.org/data/2.5/onecall?lat=42.87462&lon=74.56976&units=metric&appid=0572707574f371ae64536f0347516ed2")
    fun getWeather(): Call<ForeCast>
}