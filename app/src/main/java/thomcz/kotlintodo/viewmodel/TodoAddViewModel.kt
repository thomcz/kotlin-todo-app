package thomcz.kotlintodo.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import thomcz.kotlintodo.data.AppDatabase
import thomcz.kotlintodo.data.Item

/**
 * Copyright (c) 2017
 * Created by Thomas Czogalik on 05.10.2017
 */

class TodoAddViewModel(application: Application?) : AndroidViewModel(application) {
    private var appDatabase : AppDatabase? = AppDatabase.getDatabase(getApplication())
    private var items : LiveData<List<Item>>?

    fun addItem(item : Item) {
        AddAsyncTask(appDatabase).execute(item)
    }

    class AddAsyncTask (private val appDatabase: AppDatabase?) : AsyncTask<Item, Void, Void>()  {
        override fun doInBackground(vararg p0: Item?): Void? {
            val itemModel = appDatabase?.itemModel()
            itemModel?.addItem(p0[0])
            return null
        }

    }

    init {
        items = appDatabase?.itemModel()?.getAllItems()
    }
}