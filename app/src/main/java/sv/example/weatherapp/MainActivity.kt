package sv.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import sv.example.weatherapp.model.WeatherViewModelL
import sv.example.weatherapp.ui.theme.BlueBlond
import sv.example.weatherapp.ui.theme.PurpleGrey80
import sv.example.weatherapp.ui.theme.SoftBlue
import sv.example.weatherapp.ui.theme.SoftBlueSky
import sv.example.weatherapp.ui.theme.SoftGrey
import sv.example.weatherapp.ui.theme.SpaceColor
import sv.example.weatherapp.ui.theme.WeatherAppTheme
import sv.example.weatherapp.ui.theme.buttonColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherScreen()
        }
    }
}

@Composable
fun WeatherScreen() {

    val viewModel: WeatherViewModelL = viewModel()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val weatherData by viewModel.weatherData.collectAsState()
    var city by remember {
        mutableStateOf("")
    }
    val apiKey = "091901186e50e791a4f3bb89f54b8f3e"
    //temporizador
    var timeRemaining by remember { mutableStateOf(60) }
    //para actualizar el tiempo y los datos del clima
    LaunchedEffect(key1 = city) {
        while (true) {
            if (city.isNotEmpty()) {
                viewModel.fetchWeather(city, apiKey)
            }

            // Temporizador de 1 minuto con 3 segundos
            timeRemaining = 60
            while (timeRemaining > 0) {
                delay(1000L) // 1 segundo
                timeRemaining--
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        //Imagen del clima
        Image(
            painter = painterResource(id = R.drawable.weatherapp),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.TopCenter),
            contentScale = ContentScale.Fit
        )
        //Input debajo de la imagen
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 220.dp)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            var city by remember { mutableStateOf("") }

            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Ciudad") },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = SoftGrey,
                    unfocusedContainerColor = SoftBlue,
                    unfocusedIndicatorColor = SoftBlueSky,
                    focusedIndicatorColor = BlueBlond,
                    focusedLabelColor = BlueBlond
                )
            )
           //Botn para buscar
            Button(
                onClick = { viewModel.fetchWeather(city, apiKey) },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
            ) {
                Text(text = "Ver clima")
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Mostrar mensaje de error
            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            //Donde se mostraea la informacion
            weatherData?.let {
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        WeatherCard(label = "País", value = it.name, icon = Icons.Default.Place)
                        WeatherCard(label = "Temperatura", value = "${it.main.temp}°C", icon = Icons.Default.Warning)
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        WeatherCard(label = "Humedad", value = "${it.main.humidity}%", icon = Icons.Default.Star)
                        WeatherCard(label = "Descripción", value = it.weather.firstOrNull()?.description ?: "N/A", icon = Icons.Default.MoreVert)
                    }
                }
            }
            // Mostrar temporizador
            Text(
                text = "Actualización en ${timeRemaining / 60}:${timeRemaining % 60}",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun WeatherCard(label: String, value: String, icon: ImageVector) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(150.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Icon(
                    imageVector = icon, contentDescription = null,
                    tint = Color.Blue,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = label, fontSize = 14.sp, color = Color.DarkGray)
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = value,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = SpaceColor
                )
            }
        }
    }
}



//Vista previa de como se vera la pantalla, solo es diseño
@Preview(showBackground = true)
@Composable
fun WeatherPreview() {
    WeatherAppTheme {
        WeatherScreen()


    }
}