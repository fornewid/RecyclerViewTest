package soup.sample.recyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import soup.sample.recyclerview.model.Item
import soup.sample.recyclerview.model.Section

class MainViewModel : ViewModel() {

    private val _sections = MutableLiveData<List<Section>>()
    val sections: LiveData<List<Section>>
        get() = _sections

    init {
        viewModelScope.launch(Dispatchers.Default) {
            val sections = (0 until SECTION_COUNT).map {
                val base = 10 * it
                Section(
                    title = "Section title $it",
                    items = (base until base + ITEM_COUNT).map { id -> Item("item $id") }
                )
            }
            _sections.postValue(sections)
        }
    }

    companion object {
        private const val SECTION_COUNT = 20
        private const val ITEM_COUNT = 30
    }
}
