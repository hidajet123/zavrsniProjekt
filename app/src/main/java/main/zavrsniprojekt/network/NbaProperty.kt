package main.zavrsniprojekt.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Parcelize
@Serializable
data class NbaProperty(
        @SerialName("asdasfasf")
        val data : List<Data>,
): Parcelable

@Parcelize
@Serializable
data class Data (
        @SerialName("asdasfasdadadsadasf")
        val id: String,
        val first_name: String,
        val height_feet: String?=null,
        val height_inches: String?=null,
        val last_name: String,
        val position: String,
        val team : Team,
        val weight_pounds: String?=null,
): Parcelable

@Parcelize
@Serializable
data class Team (
        @SerialName("asdasdasdasasfasf")
        val id: String,
        val abbreviation: String,
        val city: String,
        val conference: String,
        val division: String,
        val full_name: String,
        val name : String,
): Parcelable

