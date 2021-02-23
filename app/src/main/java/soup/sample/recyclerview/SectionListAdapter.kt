package soup.sample.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import soup.sample.recyclerview.SectionListAdapter.SectionViewHolder
import soup.sample.recyclerview.databinding.ItemSectionBinding
import soup.sample.recyclerview.model.Section

class SectionListAdapter :
    ListAdapter<Section, SectionViewHolder>(IdBasedDiffCallback { it.title }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        return SectionViewHolder(
            ItemSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SectionViewHolder(
        private val binding: ItemSectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val adapter = ItemListAdapter()

        init {
            binding.nestedRecyclerView.itemAnimator = null
            binding.nestedRecyclerView.adapter = adapter
        }

        fun bind(section: Section) {
            binding.sectionTitle.text = section.title
            adapter.submitList(section.items)
        }
    }
}
