package ru.agladkov.podlodka_compose_basics_demo.old_android.master

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.agladkov.podlodka_compose_basics_demo.R

class MasterAdapter : RecyclerView.Adapter<MasterAdapter.MasterViewHolder>() {

    private val dataList: MutableList<MasterData> = mutableListOf()
    var onItemClick: ((MasterData) -> Unit)? = null

    fun setNewData(newData: List<MasterData>) {
        dataList.clear()
        dataList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MasterViewHolder(
            itemView = layoutInflater.inflate(R.layout.cell_master, parent, false),
            onItemClick = onItemClick
        )
    }

    override fun onBindViewHolder(holder: MasterViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    class MasterViewHolder(itemView: View, val onItemClick: ((MasterData) -> Unit)? = null) :
        RecyclerView.ViewHolder(itemView) {
        private val titleView = itemView.findViewById<TextView>(R.id.masterTitleView)
        private val subtitleView = itemView.findViewById<TextView>(R.id.masterSubtitleView)

        fun bind(model: MasterData) {
            itemView.setOnClickListener {
                onItemClick?.invoke(model)
            }

            titleView.text = "Item ID ${model.masterId}"
            subtitleView.text = model.masterTitle
        }
    }
}