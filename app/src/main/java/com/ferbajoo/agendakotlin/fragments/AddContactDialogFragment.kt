package com.ferbajoo.agendakotlin.fragments

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ferbajoo.agendakotlin.BR
import com.ferbajoo.agendakotlin.databases.Functions
import com.ferbajoo.agendakotlin.databinding.AddContactDialogBinding
import com.ferbajoo.agendakotlin.models.Contacts
import kotlinx.android.synthetic.main.add_contact_dialog.*

/**
 * Created by
 *      feuribe on 23/02/2018.
 */
class AddContactDialogFragment : DialogFragment(), View.OnClickListener {

    private var listener: ActionSuccess? = null
    private var contact: Contacts? = null
    private var action: Int = 0


    fun newInstance(listener: ActionSuccess, action: Int): AddContactDialogFragment {
        val fragment = AddContactDialogFragment()
        fragment.listener = listener
        fragment.action = action
        return fragment
    }

    fun newInstance(listener: ActionSuccess, action: Int, contact: Contacts): AddContactDialogFragment {
        val fragment = AddContactDialogFragment()
        fragment.listener = listener
        fragment.contact = contact
        fragment.action = action
        return fragment
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_save_contact.setOnClickListener(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = AddContactDialogBinding.inflate(inflater, container, false)

        if (action == 2) {
            binding.spGener.setSelection(if (contact?.gender == "Hombre") 1 else 2)
            binding.setVariable(BR.contact, contact)
            binding.executePendingBindings()
        }

        return binding.root
    }

    override fun onClick(p0: View?) {
        if (!TextUtils.isEmpty(et_name_add.text.toString().trim())
                && !TextUtils.isEmpty(et_phone_add.toString().trim())
                && sp_gener.selectedItem != "Seleccione") {

            val name = et_name_add.text.toString().trim()
            val phone = et_phone_add.text.toString().trim()
            val age = et_age_add.text.toString().trim()
            val gener = sp_gener.selectedItem.toString()

            if (action==2){
                updateContact(contact?.id,name, phone, age, gener, context, contact?.position)
            }else{
                insertContact(name, phone, age, gener, context)
            }
        } else {
            Toast.makeText(context, "Solo la edad puede quedar vacía", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateContact(id_contact: Long?, name: String, phone: String, age: String, gener: String, context: Context, position: Int?) {
        val count = Functions().updateContact(id_contact,name,phone, age, gener,context)

        val contact = Contacts()
        contact.id = id_contact ?: "0".toLong()
        contact.name = name
        contact.phone = phone
        contact.age = age.toInt()
        contact.gender = gener

        if (count > 0){
            listener?.contactEditSucess(position, contact)
            dismiss()
        }
    }

    private fun insertContact(name: String, phone: String, age: String, gener: String, context: Context) {

        val id = Functions().saveContact(name, phone, age, gener, context)

        if (id > 0) {
            listener?.contactAddSuccess(id)
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        val params = dialog.window
        if (params != null) {
            params.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            params.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    interface ActionSuccess {
        fun contactAddSuccess(id: Long)
        fun contactEditSucess(position: Int?, contact: Contacts)
        fun contactDelete(position: Int?)
    }

}