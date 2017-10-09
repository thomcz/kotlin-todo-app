package thomcz.kotlintodo.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import thomcz.kotlintodo.data.Item
import thomcz.kotlintodo.R
import thomcz.kotlintodo.adapter.RecyclerViewAdapter
import thomcz.kotlintodo.viewmodel.TodoListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : TodoListViewModel
    private lateinit var recyclerView : RecyclerView
    private lateinit var recyclerViewAdapter : RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
           startActivity(Intent(this, AddActivity::class.java))
        }

        recyclerView = findViewById(R.id.recycler_view)
        recyclerViewAdapter = RecyclerViewAdapter(ArrayList(), View.OnClickListener {
            checkedTodo(it)
            updateItem(it)
        })
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = recyclerViewAdapter

        viewModel = ViewModelProviders.of(this).get(TodoListViewModel::class.java)

        viewModel.getItems()?.observe(this, Observer<List<Item>> {
            items ->
            items?.let {
                recyclerViewAdapter.addItems(it) }
        })
    }

    private fun updateItem(it: View) {
        val item = (it.parent as View).tag as Item
        item.checked = !item.checked
        viewModel.updateItem(item)
    }

    fun deleteItem(view: View) {
        val item = (view.parent as View).tag as Item
        viewModel.deleteItem(item)
    }

    private fun checkedTodo(it: View) {
        val view = it.parent as View
        val title = view.findViewById<TextView>(R.id.todo_item_title)
        val desc = view.findViewById<TextView>(R.id.todo_item_desc)
        val item = view.tag as Item
        RecyclerViewAdapter.Companion.setStrikeThrough(item.checked, title, desc)
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }


}
