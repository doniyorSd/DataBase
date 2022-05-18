package com.example.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User {
    @ColumnInfo(name = "userId")
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "userName")
    var name: String? = null

    @ColumnInfo(name = "userPassword")
    var password: String? = null

    constructor()
    constructor(name: String?, password: String?) {
        this.name = name
        this.password = password
    }
}
