package org.d3if2082.task_projecta.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ObatDao {

    @Insert
    fun insert(obat: ObatEntity)

    @Query("SELECT * FROM obatR ORDER BY id DESC LIMIT 1")
    fun getLastObat(): LiveData<ObatEntity>
}