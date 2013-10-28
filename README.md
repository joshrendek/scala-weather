[![Build Status](https://travis-ci.org/joshrendek/scala-weather.png?branch=master)](https://travis-ci.org/joshrendek/scala-weather)

# scala-weather

A simple scala library to interact with http://openweathermap.org

# Reporting issues

Just open an issue on Github

# Usage

```
  val weather = new CurrentWeather("Clearwater,FL")
  weather.longitude // => -82.7998
  weather.humidity // => 94
  weather.temperature.toFahrenheit // => 86 
  // There is also a .toCelsius method and the default data unit is Kelvin
  weather.wind.speed // => 13mps
  weather.wind.degrees // => 280
  // + more, see CurrentWeather.scala
```

# Running tests

```
sbt test
```

# Contributing / Editing

You can generate the IDEA configs for IntelliJ by running `sbt idea-gen` and then opening the project in IntelliJ

# License

MIT License - See LICENSE for full text

scala-weather is Copyright (c) 2013 Josh Rendek. It is free software, and may be redistributed under the terms specified in the LICENSE file.
