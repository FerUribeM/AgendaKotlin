package com.ferbajoo.agendakotlin.adapters

import android.databinding.ViewDataBinding
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import com.ferbajoo.agendakotlin.adapters.ContactsAdapter.ContactsViewHolder
import com.ferbajoo.agendakotlin.databinding.ItemContactsBinding
import com.ferbajoo.agendakotlin.fragments.AddContactDialogFragment
import com.ferbajoo.agendakotlin.fragments.DeleteDialogFragment
import com.ferbajoo.agendakotlin.models.Contacts

/**
 * Created by
 *          feuribe on 23/02/2018.
 */
class ContactsAdapter(private val contacts: ArrayList<Contacts>, private val listener: AddContactDialogFragment.ActionSuccess) : RecyclerView.Adapter<ContactsViewHolder>() {

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val binding = ItemContactsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactsViewHolder(binding, listener)
    }

    fun addContact(contact: Contacts) {
        contacts.add(contact)
        notifyDataSetChanged()
    }

    fun updateContact(position: Int, contact: Contacts) {
        contacts.removeAt(position)
        contacts.add(position, contact)
        notifyItemChanged(position, contact)
    }

    fun deleteContact(position: Int) {
        contacts.removeAt(position)
        notifyItemRemoved(position)
    }

    class ContactsViewHolder(private val binding: ViewDataBinding, private var listener: AddContactDialogFragment.ActionSuccess) : RecyclerView.ViewHolder(binding.root), View.OnClickListener, View.OnLongClickListener {

        private lateinit var mContact: Contacts

        fun bind(data: Any) {
            binding.setVariable(BR.contact, data)
            binding.executePendingBindings()
            binding.root.setOnClickListener(this)
            binding.root.setOnLongClickListener(this)
            mContact = data as Contacts
        }


        override fun onClick(p0: View?) {
            if (adapterPosition > -1) {
                mContact.position = adapterPosition
                val pop = AddContactDialogFragment().newInstance(listener, 2, mContact)
                pop.show((p0?.context as AppCompatActivity).supportFragmentManager, "AddContact")
            }
        }

        override fun onLongClick(p0: View?): Boolean {
            if (adapterPosition > -1) {
                mContact.position = adapterPosition
                val delete = DeleteDialogFragment().newInstance(listener, mContact)
                delete.show((p0?.context as AppCompatActivity).supportFragmentManager, "DeleteContact")
                return true
            }
            return false
        }

    }


}