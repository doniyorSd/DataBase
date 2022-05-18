package com.example.database.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.database.R
import com.example.database.databinding.ItemRvBinding
import com.example.database.entity.User

class RvAdapter(var list: ArrayList<User>, var onClickListener: OnClickListener) :
    RecyclerView.Adapter<RvAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int, user: User) {
            val bind = ItemRvBinding.bind(itemView)
            bind.tvName.text = user.name
            bind.tvPassword.text = user.password

            bind.delete.setOnClickListener {
                onClickListener.deleteClick(position, user)
            }
            bind.edit.setOnClickListener {
                onClickListener.editClick(position, user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(position, list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnClickListener {
        fun editClick(position: Int, user: User)

        fun deleteClick(position: Int, user: User)
    }
}