package com.satria.oop_ii

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.satria.oop_ii.Database.AppRoomDB
import kotlinx.android.synthetic.main.activity_kopi.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KopiActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kopi)
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val allLaptop = db.KopiDao().getAllLaptop()
            Log.d("Laptopctivity", "dbResponse: $allLaptop")
        }
    }

    fun setupListener() {
        btn_createLaptop.setOnClickListener {
            startActivity(Intent(this, EditKopiActivity::class.java))
        }
    }
}