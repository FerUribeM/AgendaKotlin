package com.ferbajoo.agendakotlin.anko_views

import android.view.View
import com.ferbajoo.agendakotlin.MainActivity
import com.ferbajoo.agendakotlin.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.wrapContent

/**
 * Created by
 *          feuribe on 08/03/2018.
 */
class RecyclerViewMain : AnkoComponent<MainActivity>{

    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui){
        recyclerView{
            id = R.id.rv_contacts_sim_anko
            lparams(width = matchParent, height = wrapContent)
        }
    }


}