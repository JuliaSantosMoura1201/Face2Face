package com.example.face2face.result

import android.content.Context
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

        Glide.with(context)
            .load(data[position].imagePath)
            .into(holder.resultImage)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
