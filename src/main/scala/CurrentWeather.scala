import uk.co.bigbeeconsultants.http.{Config, HttpClient}
import uk.co.bigbeeconsultants.http.response.Response
import java.net._
import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.Formats

/**
 * Created with IntelliJ IDEA.
 * User: joshrendek
 * Date: 10/28/13
 * Time: 1:50 PM
 * License: see LICENSE file
 * Copyright (c) 2013 Josh Rendek 
 */
class CurrentWeather(val location: String, val _port: Integer = null) {

  private var _latitude: Option[Double] = None
  private var _longitude: Option[Double] = None
  private var _wind: Wind = null
  private var _country: Option[String] = None
  private var _sunrise: Option[Integer] = None
  private var _sunset: Option[Integer] = None
  private var _temperature: Temperature = null
  private var _temperature_min: Temperature = null
  private var _temperature_max: Temperature = null
  private var _pressure: Option[Double] = None
  private var _sea_level: Option[Double] = None
  private var _ground_level: Option[Double] = None
  private var _humidity: Option[Integer] = None
  private var _cloudiness: Option[Integer] = None
  private var _id: Integer = null

  case class Wind(val speed: Double, val deg: Double)

  initWeatherData()


  def initWeatherData() = {
    val url = new URL(List("http://api.openweathermap.org/data/2.5/weather?q=", location).mkString)
    var json : JValue = null
    if (_port != null) {
      val proxyAddress = new InetSocketAddress("localhost", _port)
      val proxy = new Proxy(Proxy.Type.HTTP, proxyAddress)
      val config = Config(proxy = proxy)
      val httpClient = new HttpClient(config)
      val response: Response = httpClient.get(url)
      val rawJson = response.body.asString
      json = parse(rawJson)
    }else{
      val httpClient = new HttpClient
      val response: Response = httpClient.get(url)
      val rawJson = response.body.asString
      json = parse(rawJson)
    }

    implicit val formats = DefaultFormats
    _latitude = (json \\ "coord" \\ "lat").extractOpt[Double]
    _longitude = (json \\ "coord" \\ "lon").extractOpt[Double]
    _country = (json \\ "sys" \\ "country").extractOpt[String]
    _sunrise = (json \\ "sys" \\ "sunrise").extractOpt[Integer]
    _sunset = (json \\ "sys" \\ "sunset").extractOpt[Integer]
    _temperature = new Temperature((json \\ "main" \\ "temp").extract[Double])
    _temperature_min = new Temperature((json \\ "main" \\ "temp_min").extract[Double])
    _temperature_max = new Temperature((json \\ "main" \\ "temp_max").extract[Double])
    _pressure = (json \\ "main" \\ "pressure").extractOpt[Double]
    _sea_level = (json \\ "main" \\ "sea_level").extractOpt[Double]
    _ground_level = (json \\ "main" \\ "grnd_level").extractOpt[Double]
    _humidity = (json \\ "main" \\ "humidity").extractOpt[Integer]
    _wind = new Wind((json \\ "wind" \\ "speed").extract[Double], (json \\ "wind" \\ "deg").extract[Double])
    _cloudiness = (json \\ "clouds" \\ "all").extractOpt[Integer]
    _id = (json \ "id").extract[Integer]
  }

  def longitude = {
    _longitude.get
  }

  def latitude = {
    _latitude.get
  }

  def country = {
    _country.get
  }

  def sunrise = {
    new java.util.Date(_sunrise.get * 1000)
  }

  def sunset = {
    new java.util.Date(_sunset.get * 1000)
  }

  def temperature = {
    _temperature
  }

  def temperature_min = {
    _temperature_min
  }

  def temperature_max = {
    _temperature_max
  }

  def pressure = {
    _pressure.get
  }

  def sea_level = {
    _sea_level.get
  }

  def ground_level = {
    _ground_level.get
  }

  def humidity = {
    _humidity.get
  }

  def wind = {
    _wind
  }

  def cloudiness = {
    _cloudiness.get
  }

  def id = {
    _id
  }

}

