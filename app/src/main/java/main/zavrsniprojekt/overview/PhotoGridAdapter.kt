package main.zavrsniprojekt.overview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import main.zavrsniprojekt.databinding.GridViewItemBinding
import main.zavrsniprojekt.network.Data
import main.zavrsniprojekt.network.NbaProperty


class PhotoGridAdapter( private val onClickListener: OnClickListener ) :
        ListAdapter<Data,
                PhotoGridAdapter.NbaPropertyViewHolder>(DiffCallback) {

    class NbaPropertyViewHolder(private var binding: GridViewItemBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data) {
            binding.property = data
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): NbaPropertyViewHolder {
        return NbaPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NbaPropertyViewHolder, position: Int) {
        val data = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(data)
        }

        holder.bind(data)
    }

    class OnClickListener(val clickListener: (data:Data) -> Unit) {
        fun onClick(data:Data) = clickListener(data)
    }
}

