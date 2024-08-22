package sv.example.weatherapp.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String ,
        @Query("units") units: String = "metric"
    ) : WeatherResponse

    companion object{
        private const val BASE_URL = "ttps://api.openweathermap.org/data/2.5/"

        fun create() : WeatherAPI{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(WeatherAPI::class.java)
        }
    }
}
