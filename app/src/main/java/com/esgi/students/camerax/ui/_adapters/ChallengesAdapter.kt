package com.esgi.students.camerax.ui._adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.esgi.students.camerax.R
import com.esgi.students.camerax.bo.Challenge

class ChallengesAdapter() :
    ListAdapter<Challenge, ChallengesAdapter.ChallengeViewHolder>(ChallengeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_challenge_item, parent, false)
        return ChallengeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        val challenge = getItem(position)
        holder.bind(challenge)
    }
    inner class ChallengeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(challenge: Challenge) {
            itemView.findViewById<TextView>(R.id.recipeName).text = challenge.recipe.name
            itemView.findViewById<TextView>(R.id.challengeParticipants).text = "0 / ${challenge.maxParticipants}"

            val imageView = itemView.findViewById<ImageView>(R.id.recipeImage)
            Glide.with(imageView)
                .load(challenge.recipe.image.accessUrl)
                .apply(RequestOptions().transform(
                    CenterCrop(),
                    RoundedCorners(64)
                ))
                .into(imageView)
        }
    }
}

class ChallengeDiffCallback : DiffUtil.ItemCallback<Challenge>() {
    override fun areItemsTheSame(oldItem: Challenge, newItem: Challenge): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Challenge, newItem: Challenge): Boolean {
        return oldItem._id == newItem._id // Replace with a unique identifier in Recipe
    }
}
