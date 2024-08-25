- SDk: UpsideDownCake

- API Doc: https://openweathermap.org/

---
Enlace de video:

[Video de la App](https://ufgedu-my.sharepoint.com/:v:/g/personal/ia_angieespinoza_ufg_edu_sv/EaaYeBN_ss1PmZY840-icNQBvJRImrGN3G5BBbPonTxXRA?nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJPbmVEcml2ZUZvckJ1c2luZXNzIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXciLCJyZWZlcnJhbFZpZXciOiJNeUZpbGVzTGlua0NvcHkifX0&e=5GULlG)



---
### ¿Cómo ejecutar proyecto?

1. **Actualizar SDK**
   Una vez abierto el proyecto en android studio, debe de seleccionar el icono de configuración ⚙️ y seleccionar "Administrador de SDK" o "SDK Mananger"
   
   ![image](https://github.com/user-attachments/assets/4e0abab4-14f5-4b7e-8fdb-2b4a1e2c44ca)

   
3. Se abrirá una ventana que mostrara distintos SDK
4. Seleccionar los siguientes SDK:

- Android 14.0 ("UpsideDownCake API LEVEL 34")
- Android 14.0 ("UpsideDownCake API LEVEL 34-ext8")

**Nota: Si ya se tiene el SDK instalado, omitir el paso**
Una vez seleccionado, darle clic al botón de "aplicar" y "ok"

![image-1](https://github.com/user-attachments/assets/ad11e499-d921-4aab-b3a8-c9117137747e)


4. Esperar que se instale el SDK
**⚠️ Sino corre el proyecto o no es el sdk, darle clic a todos los `UpsideDownCake`**

5. Una vez instalado, correr el proyecto en el icono de play en verde ▶️


![image-2](https://github.com/user-attachments/assets/1a19ae08-0260-41d5-9cf1-7c2f2908d5e7)

### ¿Cómo se conecto a la API de openweathermap?
1. Se inicia sesión o se crea una cuenta en la plataforma
2. Una vez adentro, se escogio la API gratuita, llamada: Current Weather Data

![image](https://github.com/user-attachments/assets/c97bba6e-cd39-4d94-a82b-ee105edb786e)

3. Para consumir un endpoint, se necesita tener el apiKey que se pasara por query params, por lo tanto se debe de ir al perfil a copiarlo para utilizarlo
   
![image](https://github.com/user-attachments/assets/415ed421-4e57-4536-8cbe-9ff45679c297)

4. En `Api Doc` se mostrara los distintos endpoint que se tiene, y depdendindo del plan, se mostrara informacion de la API, en este caso el plan es gratis y el endpoint que se utiliza es por ciudad

   ![image](https://github.com/user-attachments/assets/f03ee02d-2d94-40bb-a958-6138166ac383)

Y la documentacion de ese endpoint nos dice:
Parámetros


| Parámetro | Requerido | Descripción |
|-----------|-----------|-------------|
| `q`       | Sí        | Nombre de la ciudad, código del estado y código del país separados por coma. Consulta el ISO 3166 para los códigos de estado o país. Puedes especificar el parámetro no solo en inglés. En este caso, la respuesta de la API se devolverá en el mismo idioma que el nombre de la ubicación solicitado, siempre que la ubicación esté en nuestra lista predefinida de más de 200,000 ubicaciones. |
| `appid`   | Sí        | Tu clave API única (siempre la puedes encontrar en la página de tu cuenta bajo la pestaña "API key"). |
| `mode`    | No        | Formato de la respuesta. Los valores posibles son xml y html. Si no usas el parámetro `mode`, el formato será JSON por defecto. |
| `units`   | No        | Unidades de medida. Están disponibles unidades estándar, métricas e imperiales. Si no usas el parámetro `units`, se aplicarán las unidades estándar por defecto. |
| `lang`    | No        | Puedes usar este parámetro para obtener la salida en tu idioma. |

Por lo tanto, se utilizo este endpoitn para la aplicación:

`
https://api.openweathermap.org/data/2.5/weather?q=london&appid=${myApiKey}&lang=es
`

Y al colocarle un pais se nos mostrará la siguiente informacion:
```
{
    "coord": {
        "lon": -0.1257,
        "lat": 51.5085
    },
    "weather": [
        {
            "id": 804,
            "main": "Clouds",
            "description": "nubes",
            "icon": "04d"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 290.87,
        "feels_like": 290.42,
        "temp_min": 289.85,
        "temp_max": 292.05,
        "pressure": 1018,
        "humidity": 66,
        "sea_level": 1018,
        "grnd_level": 1014
    },
    "visibility": 10000,
    "wind": {
        "speed": 7.2,
        "deg": 220
    },
    "clouds": {
        "all": 91
    },
    "dt": 1724610141,
    "sys": {
        "type": 2,
        "id": 2075535,
        "country": "GB",
        "sunrise": 1724562158,
        "sunset": 1724612569
    },
    "timezone": 3600,
    "id": 2643743,
    "name": "London",
    "cod": 200
}

```

Para consumir una API en android usando kotlin y retofit se crea una carpeta model con los siguientes archivos:



![image](https://github.com/user-attachments/assets/8f5c1826-4ddd-4800-bb7d-2acdf7c45043)


`WeatherAPI.kt`

- Define un método `getWeather` anotado con `@GET("weather")` que realiza una solicitud GET a la URL weather.
- Utiliza parámetros de consulta `(@Query)` para enviar el nombre de la ciudad `(q)`, la clave API `(appid)`, y un parámetro opcional para el idioma `(lang)`, que por defecto es `"es"`.
- Devuelve un objeto WeatherResponse.

`companion object y create Function`
- Define una URL base para la API y una función `create` que configura Retrofit para usar el conversor Gson y la URL base especificada. Esta función retorna una instancia de WeatherAPI.
WeatherResponse.kt

![image](https://github.com/user-attachments/assets/8d8531fe-330d-4707-9d34-4a4b6a4a143f)



`WeatherResponse Data Class`

- Representa la respuesta de la API e incluye campos como name (nombre de la ciudad), cod (código de estado), main (objeto con la temperatura y humedad), y weather (lista de condiciones meteorológicas).
- 

![image](https://github.com/user-attachments/assets/5d9ff9bc-ba89-4da9-884a-3a3d13e9208b)



`WeatherViewModelL.kt`
Define el ViewModel para manejar la lógica de obtención de datos meteorológicos y el estado de la aplicación.

Propiedades:
- `_weatherData` y `weatherData`: `StateFlow` que almacena la respuesta del clima. Se inicializa como `null` y se actualiza con los datos obtenidos.
- `_errorMessage` y `errorMessage`: StateFlow que almacena mensajes de error. Se inicializa como null y se actualiza en caso de error.

`fetchWeather Function:`
- Utiliza viewModelScope.launch para realizar una solicitud de clima en un coroutine.
- Llama a `weatherApi.getWeather` con los parámetros proporcionados.
Actualiza `_weatherData` con la respuesta si es válida o `_errorMessage` con un mensaje de error si no se encuentra la ciudad o ocurre una excepción.

![image](https://github.com/user-attachments/assets/07ab3a96-abe2-4be4-a378-0f3c747c574f)



### Dependencias utilizadas

**Retrofit para hacer fetch**

- implementation("com.squareup.retrofit2:retrofit:2.9.0")
  **Para pasarlo a formato JSON**
- implementation("com.squareup.retrofit2:converter-gson:2.9.0")
  **Lifecycle viewmodel**
- implementation("androidx.lifecycle:lifecycle-viewmodel-android:2.8.4")
  **Uso de Jetpack Compose**
- implementation("androidx.navigation:navigation-compose:2.7.7")

**Permisos para consumir APIs en `AndroidManifest.xml`**

```
<uses-permission android:name="android.permission.INTERNET" />
```
