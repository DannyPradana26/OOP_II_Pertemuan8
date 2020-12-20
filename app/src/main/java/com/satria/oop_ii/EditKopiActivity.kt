package com.satria.oop_ii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.satria.oop_ii.Database.AppRoomDB
import com.satria.oop_ii.Database.Constant
import com.satria.oop_ii.Database.Kopi
import kotlinx.android.synthetic.main.activity_edit_kopi.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditKopiActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    private var kopiId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_kopi)
        setupListener()
        setupView()
    }

    fun setupListener(){
        btn_saveKopi.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.KopiDao().addKopi(
                    Kopi(0, txt_merk.text.toString(), Integer.parseInt(txt_stok.text.toString()), Integer.parseInt(txt_harga.text.toString()) )
                )
                finish()
            }
        }
    }
    fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {

            }
            Constant.TYPE_READ -> {
                btn_saveKopi.visibility = View.GONE
                getKopi()
            }
        }
    }

    fun getKopi() {
        kopiId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val kopi =  db.KopiDao().getKopi( kopiId )[0]
            txt_merk.setText( kopi.merk )
            txt_stok.setText( kopi.stok )
            txt_harga.setText( kopi.harga )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


}