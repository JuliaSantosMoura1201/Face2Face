package com.example.face2face.result

data class ResultData(
    val imagePath: String,
    val date: String,
    val probabilityOfFraud: String,
    val typeOfFraud: String
)