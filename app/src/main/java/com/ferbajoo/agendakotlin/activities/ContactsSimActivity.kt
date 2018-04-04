package com.ferbajoo.agendakotlin.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.ferbajoo.agendakotlin.MainActivity
import com.ferbajoo.agendakotlin.R
import com.ferbajoo.agendakotlin.adapters.ContactSimAdapter
import com.ferbajoo.agendakotlin.anko_views.RecyclerViewMain
import com.ferbajoo.agendakotlin.models.ContactsSim
import kotlinx.android.synthetic.main.content_sim.*
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

/**
 * Created by
 *          feuribe on 08/03/2018.
 */
class ContactsSimActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_sim)

        val toolbar = tb_contact_sim as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Contacts SIM"

        val mview = RecyclerViewMain().createView(AnkoContext.Companion.create(this, MainActivity(),false))

        coordinator_sim.addView(mview)

        val rv_sim = mview.find<RecyclerView>(R.id.rv_contacts_sim_anko)

        rv_sim.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val items = arrayListOf<ContactsSim>()
        (0..20).map {
            items.add(ContactsSim("Titulo $it","Descripcion $it", "Precio $it"))
        }

        rv_sim.adapter = ContactSimAdapter(items)

    }

}