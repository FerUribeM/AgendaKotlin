package com.ferbajoo.agendakotlin.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ferbajoo.agendakotlin.R
import com.ferbajoo.agendakotlin.databases.Functions
import com.ferbajoo.agendakotlin.models.Contacts
import kotlinx.android.synthetic.main.delete_contact_dialog.*

/**
 * Created by
 *          feuribe on 28/02/2018.
 */
class DeleteDialogFragment : DialogFragment(), View.OnClickListener {

    private var listener: AddContactDialogFragment.ActionSuccess? = null
    private var contact: Contacts? = null

    fun newInstance(listener: AddContactDialogFragment.ActionSuccess, contact: Contacts): DeleteDialogFragment {
        val fragment = DeleteDialogFragment()
        fragment.listener = listener
        fragment.contact = contact
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.delete_contact_dialog, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_delete_contact.setOnClickListener(this)
        btn_cancel_contact.setOnClickListener(this)
        tv_name_delete.text = contact?.name
    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.btn_delete_contact) {
            Functions().deleteContact(context,contact?.id)
            listener?.contactDelete(contact?.position)
        }
        dismiss()
    }

    override fun onStart() {
        super.onStart()
        val params = dialog.window
        if (params != null) {
            params.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            params.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

}