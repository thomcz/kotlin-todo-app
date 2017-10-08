package thomcz.kotlintodo.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

/**
 * Copyright (c) 2017
 * Created by Thomas Czogalik on 28.09.2017
 */
@Dao
interface ItemDao {
    @Insert
    fun addItem(item: Item?)

    @Query("SELECT * FROM Item")
    fun getAllItems(): LiveData<List<Item>>

    @Update
    fun updateItem(item: Item)

    @Delete
    fun deleteItem(item: Item)
}