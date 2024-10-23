package com.example.weatherforcasting.data.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class WeatherResponse(
    @SerializedName("city")
    val city: CityWeather?,
    @SerializedName("cnt")
    val cnt: Int?,
    @SerializedName("cod")
    val cod: String?,
    @SerializedName("list")
    val list: List<WeatherItem>?,
    @SerializedName("message")
    val message: Int?
)
@Entity
data class CityWeather(
    @ColumnInfo
    @SerializedName("coord")
    val coord: Coord?,
    @ColumnInfo
    @SerializedName("country")
    val country: String?,
    @SerializedName("id")
    @PrimaryKey
    val id: Int?,
    @ColumnInfo
    @SerializedName("name")
    val name: String?,
    @ColumnInfo
    @SerializedName("population")
    val population: Double?,
    @ColumnInfo
    @SerializedName("sunrise")
    val sunrise: Double?,
    @ColumnInfo
    @SerializedName("sunset")
    val sunset: Double?,
    @ColumnInfo
    @SerializedName("timezone")
    val timezone: Double?
)
@Entity
data class WeatherItem(
    @ColumnInfo
    @SerializedName("city_migrated_id")
    var cityMigratedId: Int?,
    @ColumnInfo
    @SerializedName("clouds")
    val clouds: Clouds?,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Auto-generated primary key
    @ColumnInfo
    @SerializedName("dt")
    val dt: Double?,
    @ColumnInfo
    @SerializedName("dt_txt")
    val dtTxt: String?,
    @ColumnInfo
    @SerializedName("main")
    val main: Main?,
    @ColumnInfo
    @SerializedName("pop")
    val pop: Double?,
    @ColumnInfo
    @SerializedName("sys")
    val sys: Sys?,
    @ColumnInfo
    @SerializedName("visibility")
    val visibility: Double?,
    @ColumnInfo
    @SerializedName("weather")
    val weather: List<Weather>?,
    @ColumnInfo
    @SerializedName("wind")
    val wind: Wind?
)
@Entity
data class Coord(
    @ColumnInfo
    @SerializedName("lat")
    val lat: Double?,
    @ColumnInfo
    @SerializedName("lon")
    val lon: Double?
)
@Entity
data class Clouds(
    @ColumnInfo
    @SerializedName("all")
    val all: Double?
)
@Entity
data class Main(
    @ColumnInfo
    @SerializedName("feels_like")
    val feelsLike: Double?,
    @ColumnInfo
    @SerializedName("grnd_level")
    val grndLevel: Double?,
    @ColumnInfo
    @SerializedName("humidity")
    val humidity: Double?,
    @ColumnInfo
    @SerializedName("pressure")
    val pressure: Double?,
    @ColumnInfo
    @SerializedName("sea_level")
    val seaLevel: Double?,
    @ColumnInfo
    @SerializedName("temp")
    val temp: Double?,
    @ColumnInfo
    @SerializedName("temp_kf")
    val tempKf: Double?,
    @ColumnInfo
    @SerializedName("temp_max")
    val tempMax: Double?,
    @ColumnInfo
    @SerializedName("temp_min")
    val tempMin: Double?
)
@Entity
data class Sys(
    @ColumnInfo
    @SerializedName("pod")
    val pod: String?
)
@Entity
data class Weather(
    @ColumnInfo
    @SerializedName("description")
    val description: String?,
    @ColumnInfo
    @SerializedName("icon")
    val icon: String?,
    @ColumnInfo
    @SerializedName("id")
    val id: Int?,
    @ColumnInfo
    @SerializedName("main")
    val main: String?
)
@Entity
data class Wind(
    @SerializedName("deg")
    @ColumnInfo
    val deg: Double?,
    @ColumnInfo
    @SerializedName("gust")
    val gust: Double?,
    @ColumnInfo
    @SerializedName("speed")
    val speed: Double?
)