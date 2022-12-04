package com.example.todousingroomdb

import androidx.room.*
import java.util.*


@Entity
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="ID")
    var id:Int,
    @ColumnInfo(name ="TITLE")
    var Title:String,
    @ColumnInfo(name = "DESCRIPTION")
    var Description:String
//    @ColumnInfo(name = "CREATED/MODIFIED_at")
//   var  CreateAt : String





)
