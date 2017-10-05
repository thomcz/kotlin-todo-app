package thomcz.kotlintodo.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Copyright (c) 2017
 * Created by Thomas Czogalik on 28.09.2017
 */
@Dao
interface ItemDao {
    @Insert
    fun addItem(item: Item?)

    @Query("SELECT * FROM Item")
    fun getAllItems() : LiveData<List<Item>>

    @Delete
    fun deleteItem(item : Item)
}