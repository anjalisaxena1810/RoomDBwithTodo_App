package com.example.todousingroomdb

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.EventLogTags
import android.view.*
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todousingroomdb.databinding.ActivityMainBinding
import com.example.todousingroomdb.databinding.CreateNoteDialogBinding
import com.example.todousingroomdb.databinding.LayoutCustomViewBinding
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var factory: AppViewModelFactory
    private lateinit var viewModel: AppViewModel

//    private lateinit var dateFormat: SimpleDateFormat
//    private lateinit var  date:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val appDao = AppDatabase.getInstance(this).appDao
        factory = AppViewModelFactory(AppRepository(appDao))
        viewModel = ViewModelProvider(this, factory)[AppViewModel::class.java]


//        dateFormat = SimpleDateFormat("dd.MM.yyy")
//        date = dateFormat.format(Date())




        binding.recyclerView.layoutManager = GridLayoutManager(this,2)

        viewModel.user.observe(this, Observer {
            val adapter = AppRecyclerviewAdapter(it, object:OnItemClickListener{
                override fun onItemClick(position: Int, user: User) {
              val intent= Intent(this@MainActivity,UpdateActivity::class.java)
                    intent.putExtra("USER_DATA", Gson().toJson(user))
                    startActivity(intent)
                }

            })
            binding.recyclerView.adapter = adapter
        })




        binding.ftBtnAdd.setOnClickListener {

            val dialogBinding : CreateNoteDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this),R.layout.create_note_dialog, null, false)
            val dialog = Dialog(this)
            dialog.setContentView(dialogBinding.root)

            val manager = WindowManager.LayoutParams()
            manager.width = WindowManager.LayoutParams.MATCH_PARENT
            manager.height = WindowManager.LayoutParams.WRAP_CONTENT
            dialog.window!!.attributes = manager
            dialog.show()


            dialogBinding.btnSave.setOnClickListener {
              val user = User(0,dialogBinding.Title.text.toString(),dialogBinding.Descrip.text.toString())
//                  "Created_At: $date"+" "+dialogBinding.clkTime.text.toString())
                viewModel.saveData(user)
                dialog.dismiss()

            }
           dialogBinding.close.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
         R.id.it_deleteAll ->{
             viewModel.deleteAllUser()

         }
        }
        return super.onOptionsItemSelected(item)
    }
}
