package com.ferbajoo.agendakotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import com.ferbajoo.agendakotlin.adapters.ContactsAdapter
import com.ferbajoo.agendakotlin.databases.Functions
import com.ferbajoo.agendakotlin.fragments.AddContactDialogFragment
import com.ferbajoo.agendakotlin.models.Contacts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, AddContactDialogFragment.ActionSuccess {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = tb_main as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Contacts"

        fab_add_contacts.setOnClickListener(this)

        fillAdapter()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab_add_contacts -> {
                val pop = AddContactDialogFragment().newInstance(this, 1)
                pop.show(supportFragmentManager, "AddContact")
            }
        }
    }

    override fun contactAddSuccess(id: Long) {
        val contact = Functions().getContact(this, id)
        if (rv_contacts.adapter != null) {
            (rv_contacts.adapter as ContactsAdapter).addContact(contact)
        }
    }

    override fun contactEditSucess(position: Int?, contact: Contacts) {
        if (position != null && rv_contacts.adapter != null) {
            (rv_contacts.adapter as ContactsAdapter).updateContact(position, contact)
        }
    }

    override fun contactDelete(position: Int?) {
        if (position != null && rv_contacts.adapter != null) {
            (rv_contacts.adapter as ContactsAdapter).deleteContact(position)
        }
    }

    fun fillAdapter() {
        val adapter = ContactsAdapter(Functions().getAllContacts(this), this)
        rv_contacts.hasFixedSize()
        rv_contacts.layoutManager = GridLayoutManager(this, 1)
        rv_contacts.itemAnimator = DefaultItemAnimator()
        rv_contacts.adapter = adapter
    }

}
