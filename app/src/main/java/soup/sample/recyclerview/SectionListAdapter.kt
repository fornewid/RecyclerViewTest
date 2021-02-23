package soup.sample.recyclerview

import android.os.Parcelable
import android.util.ArrayMap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import soup.sample.recyclerview.SectionListAdapter.SectionViewHolder
import soup.sample.recyclerview.databinding.ItemSectionBinding
import soup.sample.recyclerview.model.Section

class SectionListAdapter :
    ListAdapter<Section, SectionViewHolder>(IdBasedDiffCallback { it.title }) {

    private val recycledViewPool = RecyclerView.RecycledViewPool()
    private val stateMap = ArrayMap<String, Parcelable>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        return SectionViewHolder(
            ItemSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            recycledViewPool
        )
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val item = getItem(position)
        holder.restoreState(stateMap[item.title])
        holder.bind(item)
    }

    override fun onViewRecycled(holder: SectionViewHolder) {
        val item = getItem(holder.adapterPosition)
        stateMap[item.title] = holder.saveState()
        super.onViewRecycled(holder)
    }

    class SectionViewHolder(
        private val binding: ItemSectionBinding,
        recycledViewPool: RecyclerView.RecycledViewPool
    ) : RecyclerView.ViewHolder(binding.root) {

        private val adapter = ItemListAdapter()

        init {
            binding.nestedRecyclerView.setRecycledViewPool(recycledViewPool)
            binding.nestedRecyclerView.setHasFixedSize(true)
            binding.nestedRecyclerView.itemAnimator = null
            binding.nestedRecyclerView.adapter = adapter
        }

        fun bind(section: Section) {
            binding.sectionTitle.text = section.title
            adapter.submitList(section.items)
        }

        fun saveState(): Parcelable? {
            return binding.nestedRecyclerView.layoutManager?.onSaveInstanceState()
        }

        fun restoreState(state: Parcelable?) {
            binding.nestedRecyclerView.layoutManager?.onRestoreInstanceState(state)
        }
    }
}
