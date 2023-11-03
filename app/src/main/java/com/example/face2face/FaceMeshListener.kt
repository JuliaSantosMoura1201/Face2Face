package com.example.face2face

import com.google.mlkit.vision.facemesh.FaceMesh

interface FaceMeshListener {
    fun onSuccess(faceMeshes: List<FaceMesh>)
    fun onFailure()
}