package sv.example.weatherapp.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class WeatherViewModelL: ViewModel() {
    private  val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    //current state update with instance
    val weatherData: StateFlow<WeatherResponse?> = _weatherData
    //Para manejar errores
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage
    //create a function for instance
    private val weatherApi = WeatherAPI.create()

    fun fetchWeather(city: String, apikey: String){
        viewModelScope.launch {
            try {
                val response = weatherApi.getWeather(city, apikey)

                if (response != null) {
                    _weatherData.value = response
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "Ciudad no encontrada."
                }
            }catch (e: Exception){
                e.printStackTrace()
                _errorMessage.value = "Error al obtener los datos."
            }
        }
    }
}