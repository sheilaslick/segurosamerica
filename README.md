# Seguros America

[![Slick|Developers](https://es.slickdevelopers.co/wp-content/uploads/2014/09/logomodern90.png)](https://es.slickdevelopers.co/)

Seguros America es una app Android, para mobiles de hasta 5.5 pulgadas, offline, desarrollada en Android con el lenguaje de Programación Java.

  - Tipo de aplicación de Seguros
  - Información útil para el Asegurado

# Nuevas Características!

  - Reclamo en línea, con esta opción podrá ir a Seguros America en Línea desde su navegador en el mobil y gestionar su reclamo.

### Tecnologías

Seguros America usa una serie de tecnologías open source:

* [Android Studio 3.1.3] - IDE (Entorno de Desarrollo Integrado) oficial por Google!
* [Java JDK 1.8] - lenguaje de programación para desarrollar la aplicación
* [Play Store] - plataforma de distribución digital de aplicaciones móviles para los dispositivos con sistema operativo Android (antes llamado Android Market).
* [Git] - software de control de versiones
* [Gradle] - es un sistema de automatización de construcción de código abierto que construye sobre los conceptos de Apache Ant y Apache Maven e introduce un lenguaje especifico del dominio (DSL) basado en Groovy en vez de la forma XML utilizada por Apache Maven para declarar la configuración de proyecto

### Instalación

Seguros America requiere [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) v1.8 para correr.

Instalar el JDK para poder iniciar con la codificación de la app.
Hay un instalador para cada sistema operativo tanto macOS, linux y windows:
* [macOS](http://download.oracle.com/otn-pub/java/jdk/8u181-b13/96a7b8442fe848ef90c96a2fad6ed6d1/jdk-8u181-macosx-x64.dmg)
* [linux](http://download.oracle.com/otn-pub/java/jdk/8u181-b13/96a7b8442fe848ef90c96a2fad6ed6d1/jdk-8u181-linux-x64.rpm)
* [windows](http://download.oracle.com/otn-pub/java/jdk/8u181-b13/96a7b8442fe848ef90c96a2fad6ed6d1/jdk-8u181-windows-x64.exe)


#### Información de la aplicación
```sh
$ --version 1.4.8
$ --minSdkVersion 14
$ --targetSdkVersion 27
$ --versionCode 21
```
El `targetSdkVersion 27` corresponde a Android Oreo versión `8.1`.

### Generar aplicación firmada
En el proyecto se incluye el archivo `segurosamerica.keystore` con `keyPassword` y `storePassword` igual a `segurosamerica`, una vez generado con ese keystore la aplicación estará lista para ser públicada en el Play Store.
