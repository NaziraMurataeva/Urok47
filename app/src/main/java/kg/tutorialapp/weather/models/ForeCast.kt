package kg.tutorialapp.weather.models

data class ForeCast(
    var lat: Double? =null,
    var lon: Double? =null,
    var timezone: String? =null,
    var timezone_offset: Long? = 0L,
    var current: CurrentForeCast? = null,
    var daily: List<DailyForeCast>? = null,
    var hourly:List<HourlyForeCast>? = null,

)

data class  CurrentForeCast(
        var dt: Long? = null,
        var sunrise: Long? = null,
        var sunset: Long? = null,
        var temp: Double? = null,
        var feels_like: Double?= null,
        var humidity: Double? = null,
        var weather: List<Weather>? = null
)

data class Weather(
        var id: Long? =null,
        var description: String? = null,
        var icon:String? = null
)
data class HourlyForeCast(
    var dt: Long? = null,
    var temp: Double? = null,
    var weather: List<Weather>? = null,
    var pop: Double? = null
)

data class DailyForeCast(
    var dt: Long? = null,
    var temp: Temperature,
    var weather: List<Weather>? = null,
    var pop: Double? = null
)

data class Temperature(
    var min: Double? = null,
    var max: Double? = null
)
