package com.example.todousingroomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface AppDao {

    @Insert
    suspend fun insertUser(vararg user:User)

    @Query("SELECT * FROM user")
    fun getAllUser(): LiveData<List<User>>

    @Delete
    suspend fun deteteUser(user:User)//single user delete

    @Update
    suspend fun updateUser(user:User)//update single user

    @Query("DELETE FROM user")
    suspend fun deleteAllUser()  //delete All user

}