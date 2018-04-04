package com.ferbajoo.agendakotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ferbajoo.agendakotlin.R
import com.ferbajoo.agendakotlin.anko_views.ItemListSim
import com.ferbajoo.agendakotlin.models.ContactsSim
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

/**
 * Created by
 *          feuribe on 08/03/2018.
 */
class ContactSimAdapter(val data: List<ContactsSim>) : RecyclerView.Adapter<ContactSimAdapter.ContactsSimHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ContactsSimHolder =
            ContactsSimHolder(ItemListSim().createView(AnkoContext.Companion.create(parent!!.context, this, false)))

    override fun onBindViewHolder(holder: ContactsSimHolder?, position: Int) {
        holder?.bindView(data[position])
    }

    override fun getItemCount(): Int = data.size

    class ContactsSimHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val textView by lazy {
            itemview.find<TextView>(R.id.txt_title)
        }
        val textViewDesc by lazy {
            itemview.find<TextView>(R.id.txt_desc)
        }
        val textViewPrice by lazy {
            itemview.find<TextView>(R.id.txt_price)
        }

        fun bindView(item: ContactsSim){
            with(item){
                textView.text = title
                textViewDesc.text = desc
                textViewPrice.text = price
            }
        }

    }

}