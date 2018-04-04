package com.ferbajoo.agendakotlin.anko_views

import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.ferbajoo.agendakotlin.R
import com.ferbajoo.agendakotlin.adapters.ContactSimAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

/**
 * Created by
 *          feuribe on 08/03/2018.
 */
class ItemListSim : AnkoComponent<ContactSimAdapter>{

    override fun createView(ui: AnkoContext<ContactSimAdapter>): View = with(ui){
        verticalLayout {
            lparams(width = matchParent, height = wrapContent)
            cardView {
                padding = dip(5)
                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    lparams(width = matchParent)

                    imageView(R.mipmap.ic_user_icon){
                        id = R.id.imgItem
                        padding = dip(5)
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }.lparams(width = dip(0), height = dip(70), weight = 1f)

                    linearLayout{
                        padding = dip(5)
                        orientation = LinearLayout.VERTICAL
                        textView("Titulo"){
                            id = R.id.txt_title
                        }
                        textView("Descripcion"){
                            id = R.id.txt_desc
                        }
                        textView("Precio"){
                            id = R.id.txt_price
                            textColor = ContextCompat.getColor(ctx,R.color.colorAccent)
                        }.lparams(){
                            topMargin = dip(5)
                            gravity = Gravity.END
                        }
                    }.lparams(width= dip(0), height = wrapContent, weight = 2f)
                }
            }
        }
    }

}