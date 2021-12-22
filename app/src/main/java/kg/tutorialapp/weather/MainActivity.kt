package kg.tutorialapp.weather

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kg.tutorialapp.weather.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
     var workResult :Int =1
     lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()

        }


    private fun setup() {
        binding.btnStart.setOnClickListener {
 //           doSomeWork()
            makeRxCall()
        }

        binding.btnShowToast.setOnClickListener{
            Toast.makeText(this, "Hello",Toast.LENGTH_LONG).show()

        }
    }

    @SuppressLint("CheckResult")
    private fun makeRxCall() {
        WeatherClient.weatherApi.fetchWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    binding.textView.text = it.current?.weather?.get(0)?.description
                    binding.textView2.text = it.current?.temp?.toString()
                }, {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            )
        }

    private fun doSomeWork() {

        val observable = Observable.create<String>{ emitter ->
            Log.d(TAG,"${Thread.currentThread().name} starting emiting")
            Thread.sleep(3000)
            emitter.onNext( "Hello")
            Thread.sleep(1000)
            emitter.onNext("Bishkek")
            emitter.onComplete()

        }
         val observer = object: Observer<String>{
             override fun onSubscribe(d: Disposable) {

             }

             override fun onNext(t: String) {
                 Log.d(TAG,"${Thread.currentThread().name} onNext() $t")
             }

             override fun onError(e: Throwable) {

             }

             override fun onComplete() {

             }


            }
        observable
            .subscribeOn(Schedulers.computation())
            .map {
                Log.d(TAG,"${Thread.currentThread().name} starting maping")
                it.toUpperCase()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)

        }

    companion object{
    const val TAG = "Rx"

    }


}

