package thomcz.kotlintodo.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Copyright (c) 2017
 * Created by Thomas Czogalik on 28.09.2017
 */
@Entity
class Item constructor(var title: String, var description: String, var checked: Boolean = false) {

    constructor() : this("", "")

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}