package org.wit.hillfort.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity
data class HillfortModel(@PrimaryKey(autoGenerate = true) var id: Long = 0,
                         var fbId : String = "",
                         var name: String = "",
                         var description: String = "",
                         var image: String = "",
                         @Embedded var location: Location = Location(),
                         var visited: Boolean = false,
                         var dateVisited: String = "",
                         var rating: Float = 1f,
                         var favorite: Boolean = false,
                         var notes : String = ""):Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable