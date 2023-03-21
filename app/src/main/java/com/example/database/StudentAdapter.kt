package com.example.database

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class StudentAdapter(list: ArrayList<StudentModel>) :
    RecyclerView.Adapter<StudentAdapter.StudentHolder>() {

    var list = list
    lateinit var context:Context
   lateinit var database : Database

    class StudentHolder(itemView : View) : ViewHolder(itemView) {
        var txtid = itemView.findViewById<TextView>(R.id.txtid)
        var txtname = itemView.findViewById<TextView>(R.id.txtname)
        var txtsurname = itemView.findViewById<TextView>(R.id.txtsurname)
        var txtaddress = itemView.findViewById<TextView>(R.id.txtaddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        context = parent.context
        return StudentHolder(LayoutInflater.from(parent.context).inflate(R.layout.std_item,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {

        database = Database(context)

        holder.txtid.text = list.get(position).id.toString()
        holder.txtname.text = list.get(position).name
        holder.txtsurname.text = list.get(position).surname
        holder.txtaddress.text = list.get(position).address

        holder.itemView.setOnClickListener {

            var dailog = Dialog(context)
            dailog.setContentView(R.layout.data_update)

            var did = dailog.findViewById<TextView>(R.id.edtid)
            var dname = dailog.findViewById<TextView>(R.id.edtname)
            var dsurname = dailog.findViewById<TextView>(R.id.txtsurname)
            var daddress = dailog.findViewById<TextView>(R.id.txtaddress)
            var btnupdate = dailog.findViewById<TextView>(R.id.btnupdate)

            did.text = list.get(position).id.toString()
            dname.setText(list.get(position).name)
            dsurname.setText(list.get(position).surname)
            daddress.setText(list.get(position).address)


            btnupdate.setOnClickListener {

                database.updateData(
                    dname.text.toString(),
                    dsurname.text.toString(),
                    daddress.text.toString(),
                    did.text.toString().toInt()
                )
                dailog.dismiss()
            }
            dailog.show()
        }
    }
}
