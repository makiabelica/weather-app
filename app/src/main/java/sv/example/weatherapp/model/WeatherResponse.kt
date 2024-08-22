package sv.example.weatherapp.model

//Estructura de la api
data class WeatherResponse(
    val name: String,
    val cod: Number,
    val main: Main,
    val weather: List<Weather>
)

data class Main(
    val temp: Number,
    val humidity: Number,
)

data class Weather(
    val main: String,
    val description: String,
    val icon: String
)