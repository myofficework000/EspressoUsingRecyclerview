package com.example.espressodemo3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class CustomAdapter(
    private val mDataSet: List<String>,
    private val mContext: Context
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view.
        val v: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (position == mDataSet.size / 2 /* calculate middle element position */) {
            viewHolder.isInTheMiddle = true
            viewHolder.textView.text = mContext.resources.getString(R.string.middle)
        } else {
            viewHolder.isInTheMiddle = false
            viewHolder.textView.text = mDataSet[position]
        }
    }

    // Return the size of your data set (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mDataSet.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val textView: TextView

        // We'll use this field to showcase matching the holder from the test.
        var isInTheMiddle = false

        init {
            textView = v.findViewById<View>(R.id.textView) as TextView
        }
    }
}