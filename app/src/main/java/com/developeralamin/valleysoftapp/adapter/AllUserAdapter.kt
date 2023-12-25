package com.developeralamin.valleysoftapp.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.developeralamin.valleysoftapp.databinding.AllUserShowLayoutBinding
import com.developeralamin.valleysoftapp.model.User

class AllUserAdapter(var context: Context, var list: List<User>) :
    RecyclerView.Adapter<AllUserAdapter.AllUserViewHolder>() {

    //var list: ArrayList<User> = ArrayList()
    inner class AllUserViewHolder(val binding: AllUserShowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    val colorList = arrayListOf<String>("#7158e2",
        "#00a8ff",
        "#2c3e50",
        "#05c46b",
        "#575fcf",
        "#ff793f",
        "#18dcff")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllUserViewHolder {
        val binding =
            AllUserShowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return AllUserViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: AllUserViewHolder,
        position: Int
    ) {

        if (position % 7 == 0) {
            holder.binding.itemLayout.setCardBackgroundColor(Color.parseColor(colorList[0]))
        } else if (position % 7 == 1) {
            holder.binding.itemLayout.setCardBackgroundColor(Color.parseColor(colorList[1]))
        } else if (position % 7 == 2) {
            holder.binding.itemLayout.setCardBackgroundColor(Color.parseColor(colorList[2]))
        } else if (position % 7 == 3) {
            holder.binding.itemLayout.setCardBackgroundColor(Color.parseColor(colorList[3]))
        } else if (position % 7 == 4) {
            holder.binding.itemLayout.setCardBackgroundColor(Color.parseColor(colorList[4]))
        } else if (position % 7 == 5) {
            holder.binding.itemLayout.setCardBackgroundColor(Color.parseColor(colorList[5]))
        } else if (position % 7 == 6) {
            holder.binding.itemLayout.setCardBackgroundColor(Color.parseColor(colorList[6]))
        }

        val item = list[position]
        holder.binding.userId.text = "ID: " + item.id.toString()
        holder.binding.userName.text = "UserName: " + item.username.toString()
        holder.binding.userEmail.text = "Email: " + item.email.toString()

        setAnimation(holder.binding.root)
    }

    fun setAnimation(view: View) {
        val animation: Animation =
            AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
        view.animation = animation
    }

    override fun getItemCount(): Int {
        return list.size
    }
}