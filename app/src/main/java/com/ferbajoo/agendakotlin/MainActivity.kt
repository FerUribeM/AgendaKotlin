package com.ferbajoo.agendakotlin

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.ferbajoo.agendakotlin.activities.ContactsSimActivity
import com.ferbajoo.agendakotlin.adapters.ContactsAdapter
import com.ferbajoo.agendakotlin.databases.Functions
import com.ferbajoo.agendakotlin.fragments.AddContactDialogFragment
import com.ferbajoo.agendakotlin.models.Contacts
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener,
        AddContactDialogFragment.ActionSuccess, SearchView.OnQueryTextListener, NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = tb_main as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Contacts"

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_contacts_search, menu)

        val searchitem = menu?.findItem(R.id.contact_search)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        var searchView: SearchView? = null
        if (searchitem != null) {
            searchView = searchitem.actionView as SearchView
            searchView.setOnQueryTextListener(this)
        }

        if (searchView != null) {
            searchView.queryHint = "Buscar contacto"
            
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.componentName))
            searchView.setOnQueryTextListener(this)
        }

        return super.onCreateOptionsMenu(menu)
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

    override fun onQueryTextChange(query: String?): Boolean {
        if (rv_contacts.adapter != null) {
            (rv_contacts.adapter as ContactsAdapter).filter.filter(query)
        }
        return true
    }

    override fun onQueryTextSubmit(newText: String?): Boolean {
        if (rv_contacts.adapter != null) {
            (rv_contacts.adapter as ContactsAdapter).filter.filter(newText)
        }
        return true
    }

    private fun fillAdapter() {
        val adapter = ContactsAdapter(Functions().getAllContacts(this), this)
        rv_contacts.hasFixedSize()
        rv_contacts.layoutManager = GridLayoutManager(this, 1)
        rv_contacts.itemAnimator = DefaultItemAnimator()
        rv_contacts.adapter = adapter
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_contact_sim -> {
                startActivity(Intent(this, ContactsSimActivity::class.java))
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
