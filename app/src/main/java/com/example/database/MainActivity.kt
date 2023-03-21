package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var database : Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        database = Database(applicationContext)

        binding.btnadd.setOnClickListener {

            database.addData(
                binding.edtname.text.toString(),
                binding.edtsurname.text.toString(),
                binding.edtaddress.text.toString()
            )

            binding.btnshow.setOnClickListener {
                var list = database.showData()
                binding.stdlists.layoutManager = LinearLayoutManager(applicationContext)
                binding.stdlists.adapter = StudentAdapter(list)
            }
        }
    }
}