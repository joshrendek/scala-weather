import org.scalatest.FunSpec;

/**
 * Created with IntelliJ IDEA.
 * User: joshrendek
 * Date: 10/28/13
 * Time: 1:31 PM
 * License: see LICENSE file
 * Copyright (c) 2013 Josh Rendek
 */
class TemperatureSpec extends FunSpec {
  val temp = new Temperature(298.0)

  describe("initializing") {
    it("should have a kelvin value") {
      assert(temp.kelvin == 298.0)
    }
  }

  describe("conversions") {
    it("should convert to celsius") {
      assert(temp.toCelsius == 24.85)
    }

    it("should convert to fahrenheit") {
      assert(temp.toFahrenheit == 76.73)
    }
  }
}
