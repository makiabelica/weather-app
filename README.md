SDk: UpsideDownCake
API Doc: https://openweathermap.org/

---

### ¿cómo ejecutar proyecto?

1. **Actualizar SDK**
   Una vez abierto el proyecto en android studio, debe de seleccionar el icono de configuración ⚙️ y seleccionar "Administrador de SDK" o "SDK Mananger"
   ![sdk](https://ufgedu-my.sharepoint.com/:i:/g/personal/ia_angieespinoza_ufg_edu_sv/ERzfH3iJZQ9MmX-yp5DjSGUBZDaSo4P2sBOoND8Pf0SPXg?e=eOlLzW)
2. Se abrirá una ventana que mostrara distintos SDK
3. Seleccionar los siguientes SDK:

- Android 14.0 ("UpsideDownCake API LEVEL 34")
- Android 14.0 ("UpsideDownCake API LEVEL 34-ext8")

**Nota: Si ya se tiene el SDK instalado, omitir el paso**
Una vez seleccionado, darle clic al botón de "aplicar" y "ok"
![sdk todos]((https://ufgedu-my.sharepoint.com/:i:/g/personal/ia_angieespinoza_ufg_edu_sv/EarAwLQGZ4BLk9Eqb3XsbegBaP7klwvatzkCQcfqvj_fCA?e=Hc7ugk))
4. Esperar que se instale el SDK
**⚠️ Sino corre el proyecto o no es el sdk, darle clic a todos los `UpsideDownCake`**

5. Una vez instalado, correr el proyecto en el icono de play en verde ▶️
![alt text]((https://ufgedu-my.sharepoint.com/:i:/g/personal/ia_angieespinoza_ufg_edu_sv/EZDnghCzIGVPqw2kwjkxFbgBTHrXadd7cVKHQGpc1WI1xA?e=UWbcju))


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
