package com.uty.databasekoneksi5170411110

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.ProgressDialog
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.priority
import com.androidnetworking.interfaces.JSONObjectRequesListener
import kotlinx.android.synthetic.main.activity_manage_fakultas.*
import org.json.JSONObject
import javax.xml.transform.Templates
import kotlin.math.log

class ManageFakultasActivity : AppCompatActivity() {

    lateinit var i: Intent
    lateinit var add:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_fakultas)

        add = findViewById((R.id.btnCreate))

        i = intent

        if(i.hasExtra"editmode")){

            if(i.getStringExtra("editmode")){
                if(i.getStringExtra("editmode")){
                    if(i.getStringExtra("editmode").equals("1")){
                        onEditMode()
                    }
                }
                add.setOnClickListener{
                    onCreate()
                }
            }
            private fun onEditMode() {
                TODO("not implemented")
                (use File | settings | File Templates)
               txt_kodefakultas.setText(i.getStringArrayExtra("txt_kodefakultas"))
                txt_namafakultas.setText(i.getStringArrayExtra("txt_namafakultas"))

                btnCreate.visibility = View.GONE
                btn_Update.visibility = View.VISIBLE
                btnDelete.visibility = View.VISIBLE
            }

            private fun onCreate(){

                val loading = progressDialog(this)
                loading.setMessage("menambahkan data...")
                loading.show()

                AndroidNetworking.post(ApiEndPoint.CREATE).addBodyparameter("kode fakultas",txt_kodefakultas.text.toString()).addBodyparameter("nama fakultas",txt_namafakultas.text.toString())
                    .setpriority(priority.MEDIUM)
                    .build()
                    .getAsJSONObject(object : JSONObjectRequesListener{
                        override fun onResponse: JSONObject?) {
                            loading.dinamiss()

                            Toast.makeText((applicationContext,response?.geString("message"),Toast.LENGTH_SHORT)).show()

                            if(response?.getString("message")?.contains("successfully")!!){
                                loading.dismiss()
                                Toast.makeText(aplicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()
                            }
                        })
                    }

            }
        }
    }
}
