**Explicación de la arquitectura utilizada**
Se utiliza Clean architecture para la estructura general de la aplicación, con 4 componentes principales, core, data, domain y presentation
En core se encuentran lo que se comparte a travez de la aplicacion y de los cuales pueden depender las diferentes capas. 
En data se encuentran accesos directos a los datos, modelos para mapeo de respuestas de api y mapeo de entidades a guardar en la base de datos.
En domain se encuentran los modelos que utilizan ya los elementos de ui, los casos de uso y las definiciones de los repositorios, aca vive la logica de negocio.
Finalmente en presentation, tenemos todos los composables de ui, componentes y pantallas de la aplicacion, esta utilzia el patrond e arquitectura MVVM para el manejo de estados y separar la logica de ui

**Estructura del proyecto**
core
----base
----database
----di
----events
----extensions
----navigation
data
----database
----dataSources
----di
----models
----repositoriesImpl
domain
----models
----repositories
----useCases
presentation
----ui
--------components
--------screens
--------theme
----viewModels

**Librerías utilizadas y por qué**
----//navigation
----implementation(libs.androidx.navigation.compose)
----Soporte para navegacion con jetpack compose

----//retrofit
----implementation(libs.retrofit)
----implementation(libs.retrofit.gson)
----Para poder realizar los lalmados a api

----//logging
----implementation(libs.logging.okhttp)
----Para poder ver las peticiones http que se relizan mediante retrofit

----//hilt
----implementation(libs.hilt.android)
----implementation(libs.hilt.navigation)
----annotationProcessor(libs.hilt.compiler)
----kapt(libs.hilt.compiler)
----Para el manejo de injeccion de dependencias

----//room
----implementation(libs.androidx.room.runtime)
----implementation(libs.androidx.room.ktx)
----ksp(libs.androidx.room.compiler)
----Para implementar la abse de datos local

**Decisiones técnicas tomadas**

**Cómo escalaría la aplicación**
Se puede implementar las diferentes capas en modulos independientes, los cuales permiten que las capas esten separadas realmente y no solo por carpetas
Validar que los datos que se cachean en base de datos se extragan principalmente de ahi y se actualicen cuando sea necesario (Reduccion de llamados a apis)
Mejorar el UI, agregar mas funcionalidades para gestionar comentarios como editar o eliminarlos.
**Qué mejoraría si tuviera más tiempo**
Implementar KMM para que la aplicacion pueda correr en otros dispositivos.
Mejorar el traer los posts desde la base de datos siempre y no desde la api al cargar la app
Agregar tests mas completos
**Cómo ejecutar el proyecto**

##Requisitos
----- Android studio
----- JDK 17
----- Andoid SDK (compileSdk 36, minSdk 28, targetSdk 35)

##clonar
clonar el repositorio desde https://github.com/AlfonsHoz/postsApp

##Ejecutar
1. Abrir el proyecto en android studio
2. Sync gradle
3. Run en emulador o dispositivo con version de android compatible

**Screenshots de la aplicación**
Listado de posts
![img_1.png](img_1.png)
Detalle de posts con sus comentarios
![img.png](img.png)
Al agregar un comentario
![img_2.png](img_2.png)
Post con comentarios diferentes, guardados para este post
![img_3.png](img_3.png)