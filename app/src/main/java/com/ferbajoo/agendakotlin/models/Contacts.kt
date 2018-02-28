package com.ferbajoo.agendakotlin.models

import android.databinding.BindingAdapter
import android.view.View
import android.widget.LinearLayout

/**
 * Created by
 *          feuribe on 23/02/2018.
 */
data class Contacts(var id: Long = 0, var name: String = "", var phone: String = "", var gender: String = "", var age: Int = 0, var position: Int = -1) {

    companion object {
        @BindingAdapter("android:visibility")
        @JvmStatic
        fun setVisibility(linear: LinearLayout, age: Int) {
            linear.visibility = if (age == 0) View.GONE else View.VISIBLE
        }

    }

}