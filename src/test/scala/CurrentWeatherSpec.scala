import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.matchers.ShouldMatchers._
import org.scalatest.FlatSpec



/**
 * Created with IntelliJ IDEA.
 * User: joshrendek
 * Date: 10/28/13
 * Time: 1:51 PM
 * License: see LICENSE file
 * Copyright (c) 2013 Josh Rendek 
 */
class CurrentWeatherSpec extends FunSpec with ShouldMatchers {
  val weather = new CurrentWeather("Clearwater,FL")
  describe("requesting data") {
    it("should have latitude and longitude") {
      assert(weather.latitude == 27.9656)
      assert(weather.longitude == -82.7998)
    }

    it("should be in the USA") {
      assert(weather.country == "United States of America")
    }

    it("should have a sea level") {
      assert(weather.sea_level == 1034.76)
    }

    it("should have a ground level") {
      assert(weather.ground_level == 1034.14)
    }

    it("should have a sunrise and sunset") {
      assert(weather.sunrise != None)
      assert(weather.sunset != None)
    }

    it("should have current, min, max temp") {
      assert(weather.temperature.toFahrenheit > 0)
      assert(weather.temperature_max.toFahrenheit > 0)
      assert(weather.temperature_min.toFahrenheit > 0)
    }

    it("should have pressure") {
      assert(weather.pressure > 0)
    }

    it("should have humidity") {
      // its florida!
      assert(weather.humidity > 50)
    }

    it("should have wind") {
      assert(weather.wind.speed >= 0)
    }

    it("should have cloudiness") {
      assert(weather.cloudiness != None)
    }

    it("should have an id") {
      assert(weather.id > 0)
    }

  }
}

