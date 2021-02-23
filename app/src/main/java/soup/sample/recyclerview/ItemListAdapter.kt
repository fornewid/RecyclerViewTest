package soup.sample.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import soup.sample.recyclerview.ItemListAdapter.ItemViewHolder
import soup.sample.recyclerview.databinding.ItemItemBinding
import soup.sample.recyclerview.model.Item

class ItemListAdapter : ListAdapter<Item, ItemViewHolder>(IdBasedDiffCallback { it.label }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(
        private val binding: ItemItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.label.text = item.label
        }
    }
}
