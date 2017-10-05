package thomcz.kotlintodo.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_add.*
import thomcz.kotlintodo.data.Item
import thomcz.kotlintodo.R
import thomcz.kotlintodo.viewmodel.TodoAddViewModel

class AddActivity : AppCompatActivity() {

    lateinit private var viewModel : TodoAddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this).get(TodoAddViewModel::class.java)

        fab.setOnClickListener { _ ->
            addItem()
            finish()
        }
    }

    private fun addItem() {
        val title = findViewById<EditText>(R.id.add_title)
        val description = findViewById<EditText>(R.id.add_description)
        viewModel.addItem(Item(title.text.toString(), description.text.toString()))
    }

}
