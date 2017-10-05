package thomcz.kotlintodo.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
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

    init {
        items = appDatabase?.itemModel()?.getAllItems()
    }
}