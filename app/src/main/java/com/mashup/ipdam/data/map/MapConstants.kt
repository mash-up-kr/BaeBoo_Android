package com.mashup.ipdam.data.map

import android.Manifest
import com.naver.maps.map.LocationTrackingMode

object MapConstants {
    const val LOCATION_MAP_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION
    const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    const val DEFAULT_LATITUDE = 37.557277
    const val DEFAULT_LONGITUDE = 126.9009433
    val LOCATION_TRACKING_MODE = LocationTrackingMode.None
    const val MAP_MAX_ZOOM = 18.0
    const val MIN_MAX_ZOOM = 5.0
}