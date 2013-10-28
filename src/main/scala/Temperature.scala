/**
 * Created with IntelliJ IDEA.
 * User: joshrendek
 * Date: 10/28/13
 * Time: 1:29 PM
 * License: see LICENSE file
 * Copyright (c) 2013 Josh Rendek 
 */
class Temperature(val kelvin : Double) {
  def toCelsius : Double = {
    val x = kelvin - 273.15;
    return "%.2f".format(x).toDouble
  }

  def toFahrenheit : Double = {
    val x = ( ( kelvin - 273.15 ) * 1.8 ) + 32
    return "%.2f".format(x).toDouble
  }
}

