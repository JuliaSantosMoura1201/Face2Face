package com.example.face2face.result

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.face2face.R

class ResultAdapter(
    private val context: Context,
    private val data: List<ResultData>
    ) : RecyclerView.Adapter<ResultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.result_list_item, parent, false)
        return ResultViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.resultDate.text = data[position].date
        holder.resultProbabilityOfFraud.text = data[position].probabilityOfFraud
        holder.resultTypeOfFraud.text = data[position].typeOfFraud

        val inputStream = context.assets.open(data[position].imagePath)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        Glide.with(context)
            .load(bitmap)
            .into(holder.resultImage)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
