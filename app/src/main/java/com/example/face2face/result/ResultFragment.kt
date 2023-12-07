package com.example.face2face.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.face2face.R
import com.example.face2face.databinding.FragmentResultBinding

class ResultFragment: Fragment() {

    private lateinit var viewBinding: FragmentResultBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentResultBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = List(5){
            ResultData(
                imagePath = "user1.png",
                date = "30/11/23 - 18h00",
                probabilityOfFraud = "99%",
                typeOfFraud = "Vídeo"
            )
        } // Replace with your data

        val layoutManager = LinearLayoutManager(context)
        viewBinding.recyclerView.layoutManager = layoutManager

        val adapter = ResultAdapter(requireContext(), data)
        viewBinding.recyclerView.adapter = adapter
    }
}



