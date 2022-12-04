package com.example.todousingroomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.todousingroomdb.databinding.ActivityUpdateBinding
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class UpdateActivity : AppCompatActivity() {
    private  lateinit var binding:ActivityUpdateBinding
    private lateinit var factory: AppViewModelFactory
    private lateinit var viewModel: AppViewModel
    lateinit var user:User
//    private lateinit var dateFormat: SimpleDateFormat
//    private lateinit var  date:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_update)


        factory = AppViewModelFactory(AppRepository(AppDatabase.getInstance(this).appDao))
        viewModel = ViewModelProvider(this, factory)[AppViewModel::class.java]
        user = Gson().fromJson(intent.getStringExtra("USER_DATA"),User::class.java)

//        dateFormat = SimpleDateFormat("dd.MM.yyy")
//
//        date = dateFormat.format(Date())


        binding.etUpdatetitle.setText(user.Title)
        binding.etUpdateDescrip.setText(user.Description)
//        binding.time2.setText("Current Date: $date Time: ")

        binding.btnUpdate.setOnClickListener {
            viewModel.updateData(User(user.id,binding.etUpdatetitle.text.toString(), binding.etUpdateDescrip.text.toString()))
//                "Modified_At: $date"+" "+binding.time2.text.toString()))
            Toast.makeText(this,"Record updated",Toast.LENGTH_SHORT).show()
            finish()
        }

    }


     override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_delete_item, menu)
    return super.onCreateOptionsMenu(menu)

}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
        R.id.it_deletesingle -> {
            viewModel.deleteSingleUser(user)

            Toast.makeText(this,"Record Deleted",Toast.LENGTH_SHORT).show()
            finish()


        }
        R.id.it_uedit -> {
            binding.btnUpdate.visibility = View.VISIBLE



        }
    }
       return super.onOptionsItemSelected(item)
}
}