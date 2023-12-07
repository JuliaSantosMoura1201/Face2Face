package com.example.face2face.result

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.face2face.R

class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val resultImage: ImageView = itemView.findViewById(R.id.result_image)
    val resultDate: TextView = itemView.findViewById(R.id.result_date)
    val resultProbabilityOfFraud: TextView = itemView.findViewById(R.id.result_probability_of_fraud_value)
    val resultTypeOfFraud: TextView = itemView.findViewById(R.id.result_type_of_fraud_value)
}
