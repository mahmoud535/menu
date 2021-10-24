package com.mahmoud.foodapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_ticket.view.*
import kotlinx.android.synthetic.main.title_tekit.view.*

class MainActivity : AppCompatActivity() {
    var adapter:foodadapter?=null
    var listoffood=ArrayList<food>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadfood()
        adapter= foodadapter(listoffood,this)
        gvFoods.adapter=adapter
    }
    fun loadfood(){
       // listoffood.add(food("Title-App"," general",R.drawable.p3))
        listoffood.add(food("Coffee","coffee Black",R.drawable.p1))
        listoffood.add(food("Espersso","Coffee with milk",R.drawable.p2))
        listoffood.add(food("[french fires]","Fresh fruit juice",R.drawable.p3))
       // listoffood.add(food("Title-App"," Beverage",R.drawable.p3))
        listoffood.add(food("Honey","Natural honey",R.drawable.p4))
        listoffood.add(food("Strawberry","Fresh strawberry without chemicals",R.drawable.p5))
        listoffood.add(food("Suger cubes","A plate containing vegetable sugar cubes",R.drawable.p3))
    }
   inner class foodadapter:BaseAdapter {
       var context: Context? = null

       var listoffoodslocal = ArrayList<food>()

       constructor(listoffood: ArrayList<food>, context: Context) {
           this.context = context
           listoffoodslocal = listoffood
       }

       override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
           val food = listoffoodslocal[position]
          /* if (food.name == "Title-App") {
               var inflator =
                   context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
               val foodvew = inflator.inflate(R.layout.title_tekit, null)
               foodvew.tvtitle.text=food.des
               return foodvew

           } else {*/


               var inflator =
                   context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
               val foodvew = inflator.inflate(R.layout.food_ticket, null)
               foodvew.tv_name.text = food.name!!
               //foodvew.tvdes.text = food.des!!
               foodvew.tvimage.setImageResource(food.image!!)
               foodvew.tvimage.setOnClickListener {
                   //move to text
                   val intent = Intent(context, FoodDetails::class.java)
                   intent.putExtra("name", food.name!!)
                   intent.putExtra("des", food.des!!)
                   intent.putExtra("image", food.image!!)
                   context!!.startActivity(intent)
                   // add(position)

               }

               return foodvew
          // }
       }


        override fun getItem(position: Int): Any {
       return listoffoodslocal[position]
        }

        override fun getItemId(position: Int): Long {
           return position.toLong()
        }

        override fun getCount(): Int {
            return listoffoodslocal.size
        }

    }
   // ---------------to delete element--------
    fun delete(index:Int){
        listoffood.removeAt(index)
        adapter!!.notifyDataSetChanged()
    }
    //--------------
    //--------------to add element-----------
    fun add (index: Int){
        listoffood.add(index,listoffood[index])
        adapter!!.notifyDataSetChanged()
    }
}