package com.example.clase08.covidCases

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clase08.CovidCase
import com.example.clase08.IAdapterView
import com.example.clase08.OnClickListener
import com.example.clase08.R

class CovidRecyclerViewAdapter(override val onClickListener: OnClickListener):
        RecyclerView.Adapter<CovidRecyclerViewAdapter.CovidViewHolder>(), IAdapterView {

    var data = listOf<CovidCase>()


    //Metodo donde crear el layout de la celda a ver
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.covid_view_holder,parent, false)
        return CovidViewHolder(view)
    }

    // Metodo que entrega información al viewHolder
    override fun onBindViewHolder(holder: CovidViewHolder, position: Int) {
        val item = data[position]
        holder.bindView(item)
        //Se aplica la interfaz entregada en este caso por la MainActivity
        holder.itemView.setOnClickListener {
            onClickListener.onClickItem(item)
        }
    }
    // Metodo que define el largo de la lista
    override fun getItemCount(): Int {
        return data.size
    }
    // Metodo donde agrega un item al recyclerView
    override fun addItem(item: Any) {
        if(item is CovidCase){
//            data.add(item)
            notifyDataSetChanged()
        }
        this.notifyDataSetChanged()
    }

    //Este metodo utiliza la lista que le entrega el viewmodel
    fun set(cases: List<CovidCase>){
        this.data = cases
        this.notifyDataSetChanged()
    }


    // Clase interna con la definición del ViewHolder
    inner class CovidViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        fun bindView(item: CovidCase){
            val countryTextView = view.findViewById<TextView>(R.id.country_text_view)
            val positiveTextView = view.findViewById<TextView>(R.id.positive_text_view)
            val negativeTextView = view.findViewById<TextView>(R.id.negative_text_view)
            countryTextView.text = item.attributes.Country_Region
            positiveTextView.text = item.attributes.Active.toString()
            negativeTextView.text = item.attributes.Deaths.toString()

        }
    }

}