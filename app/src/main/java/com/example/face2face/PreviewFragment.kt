package com.example.face2face

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.ExperimentalGetImage
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.face2face.databinding.FragmentPreviewBinding
import com.google.gson.Gson
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.facemesh.FaceMesh
import com.opencsv.CSVWriter
import java.io.FileWriter


@ExperimentalGetImage
class PreviewFragment : Fragment() , FaceMeshListener {

    private val faceMeshAnalyzer = FaceMeshAnalyzer()
    val gson = Gson()

    private lateinit var viewBinding: FragmentPreviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentPreviewBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        faceMeshAnalyzer.faceMeshListener = this

        arguments?.getString("imageUri")?.let { uri ->

            Glide.with(this)
                .load(uri)
                .into(viewBinding.imageView)
            viewBinding.testText.text = uri

            InputImage.fromFilePath(requireContext(), uri.toUri()).let { image ->
                faceMeshAnalyzer.process(image)
            }
        }
    }

    private fun writeData(faceMeshes: List<FaceMesh>){
        val csv = Environment.getExternalStorageDirectory().absolutePath
        val writer = CSVWriter(FileWriter(csv))

        val face = faceMeshes.first()

        val newData =  face.allPoints.map {
            arrayOf(it.index.toString(), it.position.x.toString(), it.position.y.toString(), it.position.z.toString())
        }

        writer.writeAll(newData)

        writer.close()
    }

    override fun onSuccess(faceMeshes: List<FaceMesh>) {
        val json = gson.toJson(faceMeshes)
        Log.i("json face2face", json)
        Toast.makeText(context, faceMeshes.size.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onFailure() {
        Toast.makeText(context, "Falha", Toast.LENGTH_SHORT).show()
    }

}