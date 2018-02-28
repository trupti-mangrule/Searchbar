package com.example.truptimangrule.day17challengesearchbar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View;
import android.widget.*
import com.example.truptimangrule.day17challengesearchbar.R.id.search_bar
import kotlinx.android.synthetic.main.activity_main.*

import android.text.method.TextKeyListener.clear
import java.util.*
import java.util.List


public class MainActivity : AppCompatActivity() {

    var adapter: CustomAdapter? = null

    var arrayList: ArrayList<String> = ArrayList<String>()
    var tempArrayList: ArrayList<String> = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        arrayList=ArrayList<String>()
        arrayList.add("January");
        arrayList.add("February");
        arrayList.add("March");
        arrayList.add("April");
        arrayList.add("May");
        arrayList.add("June");
        arrayList.add("July");
        arrayList.add("August");
        arrayList.add("September");
        arrayList.add("October");
        arrayList.add("November");
        arrayList.add("December");
        tempArrayList.addAll(arrayList)
        adapter= CustomAdapter(tempArrayList)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        recyclerView.setAdapter(adapter)

        search_bar.setQueryHint("Search View Hint")

        search_bar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                //Task HERE
                return false
            }

        })

    }

    fun filter(charText: String) {
        var charText = charText
        charText = charText.toLowerCase(Locale.getDefault())
        tempArrayList.clear()
        if (charText.length == 0) {
            tempArrayList.addAll(arrayList)
        } else {
            for (wp in arrayList) {
                if (wp.toLowerCase(Locale.getDefault()).contains(charText)) {
                    tempArrayList.add(wp)
                }
            }
        }
        adapter?.notifyDataSetChanged()
    }

    class CustomAdapter(val lapList: ArrayList<String>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

        //this method is returning the view for each item in the list
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
            return ViewHolder(v)
        }

        //this method is binding the data on the list
        override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
            holder.tv_numbers.setText(lapList.get(position))
        }

        //this method is giving the size of the list
        override fun getItemCount(): Int {
            return lapList.size
        }

        //the class is hodling the list view
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var tv_numbers: TextView =itemView.findViewById(R.id.tv_numbers)
        }
    }


}
