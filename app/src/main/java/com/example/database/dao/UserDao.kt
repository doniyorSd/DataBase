package com.example.database.dao

import androidx.room.*
import com.example.database.entity.User

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Insert
    fun insertUsers(vararg user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM User")
    fun getAllUsers(): List<User>
}