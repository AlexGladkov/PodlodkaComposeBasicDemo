package ru.agladkov.podlodka_compose_basics_demo.old_android.master

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
        private val composeView = itemView as ComposeView

        fun bind(model: MasterData) {
            itemView.setOnClickListener {
                onItemClick?.invoke(model)
            }

            composeView.setContent {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)) {
                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = "Item ID ${model.masterId}",
                        fontWeight = FontWeight.Medium
                    )
                    Text(model.masterTitle)
                }
            }
        }
    }
}