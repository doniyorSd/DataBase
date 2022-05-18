package com.example.database

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.database.adapter.RvAdapter
import com.example.database.databinding.ActivityMainBinding
import com.example.database.databinding.ItemDialogBinding
import com.example.database.db.UserDatabase
import com.example.database.entity.User

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: RvAdapter
    lateinit var list: ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = UserDatabase.getInstance(this)
        list = ArrayList()
        list.addAll(db.userDao().getAllUsers())

        adapter = RvAdapter(list, object : RvAdapter.OnClickListener {
            override fun editClick(position: Int, user: User) {
                val dialog = AlertDialog.Builder(this@MainActivity).create()
                val view = LayoutInflater.from(this@MainActivity)
                    .inflate(R.layout.item_dialog, binding.root, false)
                val bind = ItemDialogBinding.bind(view)

                dialog.setView(view)
                dialog.show()
                bind.name.setText(user.name)
                bind.password.setText(user.password)

                bind.edit.setOnClickListener {
                    val newName = bind.name.text.toString()
                    val newPassword = bind.password.text.toString()

                    user.password = newPassword
                    user.name = newName

                    db.userDao().updateUser(user)
                    list[position] = user
                    adapter.notifyItemChanged(position)
                    dialog.dismiss()
                }
            }

            override fun deleteClick(position: Int, user: User) {

                db.userDao().deleteUser(user)
                list.remove(user)
                adapter.notifyItemRemoved(position)
                adapter.notifyItemRangeChanged(position, list.size)
            }

        })
        binding.rv.adapter = adapter
        binding.save.setOnClickListener {
            val name = binding.login.text.toString()
            val password = binding.password.text.toString()

            val user = User(name, password)
            list.add(user)
            adapter.notifyItemInserted(list.size)

            db.userDao().insertUser(user)
        }
    }
}