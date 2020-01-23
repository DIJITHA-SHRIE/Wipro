package com.example.innovat.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.innovat.Model.RowResponse
import com.example.innovat.R
import com.example.innovat.databinding.AdapterLayBinding

class DataAdapter (val items : ArrayList<RowResponse>, val context: Context) : RecyclerView.Adapter<DataViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterLayBinding.inflate(inflater,parent,false)
        return DataViewHolder(binding.root, binding)

    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
            holder.bind(items.get(position).title,items.get(position).description,items.get(position).imageHref,context)

    }




}


class DataViewHolder constructor(itemView: View, binding: AdapterLayBinding): RecyclerView.ViewHolder(itemView){

    private var mBinding: AdapterLayBinding

    init {
        mBinding = binding
    }
    // add ? to pass null as parameter
    fun bind(title:String?, description:String?, imageHref : String?,context:Context){

        if(title!=null){
        mBinding.title.text = title}
        else{
            mBinding.title.text = "Not Available"
        }

        if(description!=null){
        mBinding.description.text=description
        }
        else{
            mBinding.description.text ="Not Available"
        }

        val options = RequestOptions().placeholder(R.drawable.innovatepic).error(R.drawable.innovatepic);

        if(imageHref!=null) {
            Glide.with(context)
                .load(imageHref).apply(options)
                .into(mBinding.titleImg)
        }
        else{
            mBinding.titleImg.setImageResource(R.drawable.innovatepic)
        }




    }




}