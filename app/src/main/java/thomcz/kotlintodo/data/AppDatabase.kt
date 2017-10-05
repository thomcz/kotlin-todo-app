package thomcz.kotlintodo.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Copyright (c) 2017
 * Created by Thomas Czogalik on 28.09.2017
 */

@Database(entities = arrayOf(Item::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemModel(): ItemDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "borrow_db")
                        .build()
            }
            return INSTANCE
        }
    }

}