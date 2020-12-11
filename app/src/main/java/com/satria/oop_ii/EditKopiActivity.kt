package com.satria.oop_ii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.satria.oop_ii.Database.AppRoomDB
import com.satria.oop_ii.Database.Kopi
import kotlinx.android.synthetic.main.activity_edit_kopi.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditKopiActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_kopi)
        setupListener()
    }

    fun setupListener(){
        btn_saveLaptop.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.KopiDao().addKopi(
                    Kopi(0, txt_merk.text.toString(), Integer.parseInt(txt_stok.text.toString()), Integer.parseInt(txt_harga.text.toString()) )
                )
                finish()
            }
        }
    }
}