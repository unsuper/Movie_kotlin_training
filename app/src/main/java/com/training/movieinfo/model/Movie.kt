package com.training.movieinfo.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "result")
    val result: Boolean?,
    @Json(name = "message")
    val message: String?,
    @Json(name = "items")
    val items: List<Item?>?
) {
    @JsonClass(generateAdapter = true)
    data class MovieItem(
        @Json(name = "result")
        val result: Boolean?,
        @Json(name = "message")
        val message: String?,
        @Json(name = "items")
        val items: Item?
    )

    @JsonClass(generateAdapter = true)
    data class Item(
        @Json(name = "_id")
        val id: String?,
        @Json(name = "name")
        val name: String?,
        @Json(name = "directer")
        val directer: String?,
        @Json(name = "status")
        val status: Int?,
        @Json(name = "create_at")
        val createAt: String?,
        @Json(name = "update_at")
        val updateAt: String?,
        @Json(name = "delete_at")
        val deleteAt: Any?,
        @Json(name = "country")
        val country: String?,
        @Json(name = "language")
        val language: String?,
        @Json(name = "years")
        val years: String?,
        @Json(name = "duration")
        val duration: Int?,
        @Json(name = "introduction")
        val introduction: String?,
        @Json(name = "cover_img")
        val coverImg: String?,
        @Json(name = "trailer")
        val trailer: String?,
        @Json(name = "score")
        val score: Double?,
        @Json(name = "__v")
        val v: Int?,
        @Json(name = "episode")
        val episode: Any?,
        @Json(name = "screenwriter")
        val screenwriter: Any?
    )
}