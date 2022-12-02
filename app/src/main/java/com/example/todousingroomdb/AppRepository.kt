package com.example.todousingroomdb

class AppRepository(private val appDao:AppDao) {

    suspend fun saveUser(user:User){
        appDao.insertUser(user)
    }
    suspend fun updateData(user: User){
        appDao.updateUser(user)
    }

    suspend fun deleteSingleUser(user: User){
        appDao.deteteUser(user)
    }

    suspend fun deleteAllUser(){
        appDao.deleteAllUser()
    }



    val user = appDao.getAllUser()
}