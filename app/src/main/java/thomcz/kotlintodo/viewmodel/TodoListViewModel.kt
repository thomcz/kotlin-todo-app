package thomcz.kotlintodo.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Delete
import android.os.AsyncTask
import thomcz.kotlintodo.data.AppDatabase
import thomcz.kotlintodo.data.Item

/**
 * Copyright (c) 2017
 * Created by Thomas Czogalik on 28.09.2017
 */

class TodoListViewModel(application: Application?) : AndroidViewModel(application) {
    private var appDatabase : AppDatabase? = AppDatabase.getDatabase(getApplication())
    private var items : LiveData<List<Item>>?


    fun getItems() : LiveData<List<Item>>? {
        return items
    }

    fun deleteItem(item : Item) {
        DeleteAsyncTask(appDatabase).execute(item)
    }
    fun updateItem(item : Item) {
        UpdateAsyncTask(appDatabase).execute(item)
    }

    init {
        items = appDatabase?.itemModel()?.getAllItems()
    }

    class DeleteAsyncTask (private val appDatabase: AppDatabase?) : AsyncTask<Item, Void, Void>()  {
        override fun doInBackground(vararg p0: Item): Void? {
            val itemModel = appDatabase?.itemModel()
            itemModel?.deleteItem(p0[0])
            return null
        }
    }
    class UpdateAsyncTask (private val appDatabase: AppDatabase?) : AsyncTask<Item, Void, Void>()  {
        override fun doInBackground(vararg p0: Item): Void? {
            val itemModel = appDatabase?.itemModel()
            itemModel?.updateItem(p0[0])
            return null
        }
    }
}