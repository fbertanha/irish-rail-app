package com.felipebertanha.irishrailapp.featureforecast.presentation.listadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.felipebertanha.irishrailapp.R
import com.felipebertanha.irishrailapp.databinding.ItemTrainBinding
import com.felipebertanha.irishrailapp.extensions.TextViewExtensions.setStringFromResource
import com.felipebertanha.irishrailapp.featureforecast.domain.model.Train

/**
 * Created by felipebertanha on 21/October/2022
 */
class TrainsAdapter :
    ListAdapter<Train, TrainsAdapter.TrainsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainsViewHolder {
        val binding = ItemTrainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrainsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrainsViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size

    inner class TrainsViewHolder(private val binding: ItemTrainBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(train: Train) {
            with(binding) {
                tvTrainCode.text = train.code
                tvDestination.setStringFromResource(R.string.train_towards_to, train.destination)
                tvExpectedDeparture.setStringFromResource(R.string.train_expected_departure_at, train.expectedDeparture)
                tvExpectedDepartureInMinutes.setStringFromResource(R.string.train_expected_departure_in_minutes, train.dueIn)
            }
        }
    }



    private class DiffCallback : DiffUtil.ItemCallback<Train>() {
        override fun areItemsTheSame(oldItem: Train, newItem: Train) =
            oldItem.code == newItem.code

        override fun areContentsTheSame(oldItem: Train, newItem: Train) = oldItem == newItem
    }

}

