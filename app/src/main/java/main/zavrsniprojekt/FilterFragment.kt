package main.zavrsniprojekt

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import main.zavrsniprojekt.database.PlayerDatabase
import main.zavrsniprojekt.databinding.FilterFagmentFragmentBinding
import main.zavrsniprojekt.overview.OverviewViewModel

class FilterFragment : Fragment() {

    private val viewModel: FilterFagmentViewModel by lazy {
        ViewModelProvider(this).get(FilterFagmentViewModel::class.java)
    }
    private lateinit var database: PlayerDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FilterFagmentFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.textType = FilterFragmentArgs.fromBundle(requireArguments()).typedProperty.toString()

        viewModel.readyToFilter.observe(viewLifecycleOwner, {
            if (it != null) {
                Log.d("Value za input filter: ","desilo seeeeee")
                OverviewViewModel.filter = it
                this.findNavController().navigate(FilterFragmentDirections.actionFilterList(it))
            }
        })

        return binding.root
    }

}