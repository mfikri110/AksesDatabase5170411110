package com.uty.databasekoneksi5170411110

import android.content.context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class  RVAAdapterFakultas(private val context: context, private val arrayList: ArrayList<Fakultas>):
        RecyclerView.Adapter<RVAAdapterFakultas.Holder>() {

    class Holder(val view: View) : RecyclerView.ViewHolder(view)
}