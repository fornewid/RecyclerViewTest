package soup.sample.recyclerview

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import soup.sample.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = SectionListAdapter()
        binding.recyclerView.itemAnimator = null
        binding.recyclerView.adapter = adapter

        viewModel.sections.observe(this, adapter::submitList)
    }
}
