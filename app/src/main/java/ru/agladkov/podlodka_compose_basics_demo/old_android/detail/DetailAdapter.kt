package ru.agladkov.podlodka_compose_basics_demo.old_android.detail

import android.preference.PreferenceActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.agladkov.podlodka_compose_basics_demo.R
import java.lang.IllegalStateException
import java.util.*

enum class ItemType(val id: Int) {
    Header(0), Char(1), Description(2), Image(3)
}

open class BaseDetailModel(
    val itemId: String = UUID.randomUUID().toString(),
    val itemType: ItemType
)

data class CharDetailModel(
    val title: String
) : BaseDetailModel(itemType = ItemType.Char)

data class DescriptionDetailModel(
    val description: String
) : BaseDetailModel(itemType = ItemType.Description)

data class HeaderDetailModel(
    val title: String
) : BaseDetailModel(itemType = ItemType.Header)

data class ImageDetailModel(
    val imageUrl: String
) : BaseDetailModel(itemType = ItemType.Image)

interface BindableViewHolder {
    fun bind(model: BaseDetailModel)
}

class DetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val _items: MutableList<BaseDetailModel> = mutableListOf()

    fun setNewData(data: List<BaseDetailModel>) {
        _items.clear()
        _items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemType = when (viewType) {
            ItemType.Image.id -> ItemType.Image
            ItemType.Header.id -> ItemType.Header
            ItemType.Description.id -> ItemType.Description
            ItemType.Char.id -> ItemType.Char
            else -> throw IllegalStateException("Incorrect item type $viewType")
        }

        val layoutInflater = LayoutInflater.from(parent.context)

        return when (itemType) {
            ItemType.Image -> ImageViewHolder(
                layoutInflater.inflate(
                    R.layout.cell_header_image,
                    parent,
                    false
                )
            )
            ItemType.Description -> DescriptionViewHolder(
                layoutInflater.inflate(
                    R.layout.cell_description,
                    parent,
                    false
                )
            )
            ItemType.Char -> CharViewHolder(
                layoutInflater.inflate(
                    R.layout.cell_characteristic,
                    parent,
                    false
                )
            )
            ItemType.Header -> HeaderViewHolder(
                layoutInflater.inflate(
                    R.layout.cell_header,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BindableViewHolder).bind(_items[position])
    }

    override fun getItemCount(): Int = _items.size
    override fun getItemViewType(position: Int): Int = _items[position].itemType.id

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), BindableViewHolder {
        private val titleView = itemView.findViewById<TextView>(R.id.titleView)

        override fun bind(model: BaseDetailModel) {
            (model as? HeaderDetailModel)?.let {
                titleView.text = it.title
            }
        }
    }

    class CharViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), BindableViewHolder {
        private val characteristicTitleView =
            itemView.findViewById<TextView>(R.id.characteristicTitleView)

        override fun bind(model: BaseDetailModel) {
            (model as? CharDetailModel)?.let {
                characteristicTitleView.text = it.title
            }
        }
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), BindableViewHolder {

        override fun bind(model: BaseDetailModel) {
            (model as? ImageDetailModel)?.let {

            }
        }
    }

    class DescriptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        BindableViewHolder {
        private val descriptionView = itemView.findViewById<TextView>(R.id.descriptionView)

        override fun bind(model: BaseDetailModel) {
            (model as? DescriptionDetailModel)?.let {
                descriptionView.text = it.description
            }
        }
    }
}