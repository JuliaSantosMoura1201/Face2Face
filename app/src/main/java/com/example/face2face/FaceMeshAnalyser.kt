package com.example.face2face

import android.content.Context
import android.widget.Toast
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.android.gms.tflite.client.TfLiteInitializationOptions
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.facemesh.FaceMeshDetection
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.task.gms.vision.TfLiteVision
import java.nio.ByteBuffer

@ExperimentalGetImage
class FaceMeshAnalyzer : ImageAnalysis.Analyzer {

    var faceMeshListener: FaceMeshListener? = null
    val detector = FaceMeshDetection.getClient()
    var currentImage: InputImage? = null


    private lateinit var binaryModelInterpreter: Interpreter
    private lateinit var multipleClassesModelInterpreter: Interpreter

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

    fun initializeTFLiteModels(context: Context) {
        val options = TfLiteInitializationOptions.builder()
            .setEnableGpuDelegateSupport(true)
            .build()

        val tfliteBinaryModel = loadModelFileFromAssets(context, "model_binario.tflite")
        val tfliteMultipleClassModel = loadModelFileFromAssets(context, "multiple_class_model.tflite")

        TfLiteVision.initialize(context, options).addOnSuccessListener {
            binaryModelInterpreter = Interpreter(tfliteBinaryModel)
            multipleClassesModelInterpreter = Interpreter(tfliteMultipleClassModel)
        }.addOnFailureListener {
            TfLiteVision.initialize(context).addOnSuccessListener {
                binaryModelInterpreter = Interpreter(tfliteBinaryModel)
                multipleClassesModelInterpreter = Interpreter(tfliteMultipleClassModel)
            }.addOnFailureListener{
                Toast.makeText(context, "O detector TFLite falhou", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadModelFileFromAssets(context: Context, modelName: String): ByteBuffer {
        val assetManager = context.assets
        val inputStream = assetManager.open(modelName)
        val fileSize = inputStream.available()
        val buffer = ByteBuffer.allocateDirect(fileSize)

        inputStream.use { input ->
            buffer.apply {
                val bytes = ByteArray(buffer.capacity())
                input.read(bytes)
                buffer.put(bytes)
                buffer.rewind() // Volta para o início do buffer
            }
        }

        return buffer
    }
}