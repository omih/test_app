package com.example.data.storage.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.data.model.entity.UserEntity
import com.example.data.storage.Tables

@Dao
abstract class UserDao : BaseDao<UserEntity> {
    @Query("DELETE FROM ${Tables.USERS}")
    abstract fun clear()

}