SDk: UpsideDownCake
API Doc: https://openweathermap.org/

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
