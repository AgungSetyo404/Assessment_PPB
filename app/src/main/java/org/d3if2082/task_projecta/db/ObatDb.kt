package org.d3if2082.task_projecta.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if2082.task_projecta.models.Obat

@Database(entities = [ObatEntity::class], version = 1, exportSchema = false)

abstract class ObatDb : RoomDatabase() {

    abstract val dao: ObatDao

    companion object {

        @Volatile
        private var INSTANCE: ObatDb? = null

        fun getInstance(context: Context): ObatDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ObatDb::class.java,
                        "obat.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}