package com.example.weatherforcasting.ui.weather.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.weatherforcasting.data.model.City
import com.example.weatherforcasting.databinding.SpinnerCityOneItemBinding

class SpinnerCustomAdpter(context: Context?, resource: Int, val list: ArrayList<City>)
    : ArrayAdapter<City>(context!!, resource, list) {
   override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
       var binding : SpinnerCityOneItemBinding?=null
       var viewHolder : ViewHolder?=null
       try {
     if (convertView==null) {
               val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
             // view = inflater.inflate(R.layout.spinner_item, parent, false)
           binding = SpinnerCityOneItemBinding.inflate(inflater,parent,false)

         viewHolder = ViewHolder(binding)
         viewHolder.binding.root.tag = viewHolder
       } else  {
         viewHolder = convertView.tag as ViewHolder
     }
       } catch (e: Exception) {

       }
        return viewHolder?.binding?.root!! //view!!
    }
    override fun getCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var binding : SpinnerCityOneItemBinding?=null
        var viewHolder : ViewHolder?=null
        try {
                val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                binding = SpinnerCityOneItemBinding.inflate(inflater,parent,false)
                viewHolder = ViewHolder(binding)
                viewHolder.binding.root.tag = viewHolder

            val rssItem: City? = super.getItem(position)
            rssItem?.let {
                viewHolder.binding.itemCredit.text =
                    /*list.get(position)*/rssItem.cityNameEn // in case of ar we can use get local language later
            }

        } catch (e: Exception) {
        }
        return viewHolder?.binding?.root!! //view!!
    }


     class ViewHolder (val binding: SpinnerCityOneItemBinding) {
    }

}
