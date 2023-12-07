package com.example.face2face

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.facemesh.FaceMeshDetection

@ExperimentalGetImage
class FaceMeshAnalyzer : ImageAnalysis.Analyzer {

    var faceMeshListener: FaceMeshListener? = null

    val detector = FaceMeshDetection.getClient()

    var currentImage: InputImage? = null

    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            currentImage =
                InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
        }
    }

    fun process(image: InputImage) {
        detector.process(image)
            .addOnSuccessListener {
                faceMeshListener?.onSuccess(it)
            }
            .addOnFailureListener {
                faceMeshListener?.onFailure()
            }
    }
}