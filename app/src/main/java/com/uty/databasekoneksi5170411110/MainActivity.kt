package com.uty.databasekoneksi5170411110

import android.app.ProgressDialog
import android.content.intent
import android .util.log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import  com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSNObjectRequesListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import javax.net.ssl.ManagerFactoryParameters

class MainActivity : AppCompatActivity() {

    var arrayList = ArrayList<Fakultas>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Data Fakultas"

        recycle_view.setHasFixedSize(true)
        recycle_view.layoutManager = LinearLayoutManager(this)

        mjFloatingActionButton.setOnClickListener {
            startActivity(intent(this,ManagerFacultasActivity::class.java))
        }
        loadAllFakultas()
    }
    override fun onResume(){
        super.onResume()
        loadAllFakultas()
    }
    private fun loadAllFakultas(){
        val loading = progressDialog(this)
        loading.setMessage("memuat data...")
        loading.show()

        AndroidNetworking.get(ApiEndPoint.READ)
            .setpriority(priority.MEDIUM)
            .build
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    arrayList.clear()

                    val jsonArray = response?.optJSONArray("result")

                    if(jsonArray?.length() == 0) {
                        loading.dismiss()
                        Toast.makeText(applicationContext,"Fakultas data is empty, add the data first", Toast.LENGTH_SHORT).show()
                    }
                    for (i in 0 until jsonArray?.length()!!){

                        val jsonObject = jsonArray?.optJSONObject(i)

                            arrayList.add(
                                Fakultas(
                                    jsonObject.getString("kode_fakultas")
                                    jsonObject.String("nama_fakultas")

                                )
                            )
                        if(jsonArray?.length())-1 == i){

                            loading.dismiss()
                            val adapter = RVAAdapterFakultas(applicationContext,arrayList)
                                adapter.notifyDataSetChanged()
                                recycle_view.adapter = adapter
                        }


                    }
                }

                override fun onError(anError: ANError?) {
                    loading.dismis()
                    log.d("ONERROR", anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext, "Connection Failure", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
