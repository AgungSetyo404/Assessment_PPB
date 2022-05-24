package org.d3if2082.task_projecta.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "obatR")
data class ObatEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var namaObat: String
)
