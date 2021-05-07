package com.mashup.ipdam.entity.kakao.keyword

import com.google.gson.annotations.SerializedName

data class SameName(
    @SerializedName("region") val region: List<String>,
    @SerializedName("keyword") val keyword: String,
    @SerializedName("selected_region") val selectedRegion: String
)
