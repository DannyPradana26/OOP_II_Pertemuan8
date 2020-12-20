package com.satria.oop_ii

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.satria.oop_ii.Database.AppRoomDB
import com.satria.oop_ii.Database.Constant
import com.satria.oop_ii.Database.Kopi
import kotlinx.android.synthetic.main.activity_kopi.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KopiActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    lateinit var KopiAdapter: KopiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kopi)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadKopi()
    }
    fun loadKopi(){
        CoroutineScope(Dispatchers.IO).launch {
            val allKopi = db.KopiDao().getKopi()
            Log.d("KopiActivity", "dbResponse: $allKopi")
            withContext(Dispatchers.Main) {
                KopiAdapter.setData(allKopi)
            }
        }
    }

    fun setupListener() {
        btn_createKopi.setOnClickListener {
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }
    fun setupRecyclerView() {
        KopiAdapter = KopiAdapter(arrayListOf(), object : KopiAdapter.OnAdapterListener {
            override fun onClick(kopi: Kopi) {
                intentEdit(kopi.id, Constant.TYPE_READ)
            }


            override fun onDelete(kopi: Kopi){
                deleteDialog(kopi)
            }

        })
        list_Kopi.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = KopiAdapter
        }
    }

        fun intentEdit(kopiId: Int, intentType: Int ) {
            startActivity(
                Intent(applicationContext, EditKopiActivity::class.java)
                    .putExtra("intent_id", kopiId)
                    .putExtra("intent_type", intentType)
            )
        }
        private fun deleteDialog(kopi: Kopi) {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.apply {
                setTitle("Konfirmasi")
                setMessage("Yakin ingin menghapus data ini?")
                setNegativeButton("Batal") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                setPositiveButton("Hapus") { dialogInterface, i ->
                    dialogInterface.dismiss()
                    CoroutineScope(Dispatchers.IO).launch {
                        db.KopiDao().deleteKopi(kopi)
                        loadKopi()
                    }
                }
            }
            alertDialog.show()
        }
    }