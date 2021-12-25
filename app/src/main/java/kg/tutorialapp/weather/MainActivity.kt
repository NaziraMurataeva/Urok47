package kg.tutorialapp.weather
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kg.tutorialapp.weather.databinding.ActivityMainBinding
import kg.tutorialapp.weather.models.CurrentForeCast
import kg.tutorialapp.weather.models.ForeCast
import kg.tutorialapp.weather.models.Weather
import kg.tutorialapp.weather.network.WeatherClient
import kg.tutorialapp.weather.storage.ForeCastDataBase


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val db by lazy{
         ForeCastDataBase.getInstance(applicationContext)
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()

        }


    @SuppressLint("CheckResult")
    private fun setup() {

        binding.btnIns.setOnClickListener{
            db.forecastDao()
                .insert(getForecastFromInput())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { }

        }
        binding.btnUpd.setOnClickListener {
            db.forecastDao()
                .update(getForecastFromInput())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { }
        }
        binding.btnDel.setOnClickListener {
                db.forecastDao()
                    .delete(getForecastFromInput())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { }

            }
        binding.btnQga.setOnClickListener {
            db.forecastDao()
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                     var text = ""
                     it.forEach {
                         text += it.toString()
                     }

                     binding.textView.text = text
                    },
                    {
                    }
                )

        }

    }

    private fun getForecastFromInput() : ForeCast {
        val id = binding.etId.text?.toString().takeIf { !it.isNullOrEmpty() }?.toLong()
        val lon = binding.etLon.text?.toString().takeIf { !it.isNullOrEmpty() }?.toDouble()
        val lat = binding.etLat.text?.toString().takeIf { !it.isNullOrEmpty() }?.toDouble()
        val description = binding.etDes.text?.toString()
        val current = CurrentForeCast(weather = listOf(Weather(description = description)))

        return ForeCast (id =id, lat = lat, lon = lon, current = current)
    }



  @SuppressLint("CheckResult")
    private fun makeRxCall() {
        WeatherClient.weatherApi.fetchWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            },{
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            })
    }
}

