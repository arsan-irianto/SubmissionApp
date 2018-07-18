package com.arsan.submissionapp.data.network.model

import com.google.gson.annotations.SerializedName
import java.text.FieldPosition

data class Player(
        @SerializedName("idPlayer")
        var id:String?=null,

        @SerializedName("strPlayer")
        var player:String?=null,

        @SerializedName("strPosition")
        var position:String?=null,

        @SerializedName("strCutout")
        var playerPhoto:String?=null,

        @SerializedName("strFanart1")
        var fanArt:String?=null,

        @SerializedName("strHeight")
        var height:String?=null,

        @SerializedName("strWeight")
        var weight:String?=null,

        @SerializedName("strDescriptionEN")
        var playerDescr:String?=null
)