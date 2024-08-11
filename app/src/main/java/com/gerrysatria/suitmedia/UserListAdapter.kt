package com.gerrysatria.suitmedia

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gerrysatria.core.choose_name_data
import com.gerrysatria.core.databinding.UserItemBinding
import com.gerrysatria.core.domain.model.User

class UserListAdapter(private val onUserSelected: (User) -> Unit) : ListAdapter<User, UserListAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(data: User){
            binding.apply {
                Glide.with(imgUser.context)
                    .load(Uri.parse(data.avatar))
                    .circleCrop()
                    .into(imgUser)

                txtUserName.text = "${data.firstName} ${data.lastName}"
                txtUserEmail.text = data.email

                itemView.setOnClickListener {
                    onUserSelected(data)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>(){
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}