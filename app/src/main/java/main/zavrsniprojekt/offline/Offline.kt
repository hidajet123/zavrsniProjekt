package main.zavrsniprojekt.offline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import main.zavrsniprojekt.R
import main.zavrsniprojekt.databinding.FragmentOfflineBinding

class Offline : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding=FragmentOfflineBinding.inflate(inflater)
        val viewModel=ViewModelProvider(this).get(OfflineViewModel::class.java)
        binding.noInternet=viewModel

        viewModel.click.observe(viewLifecycleOwner,{
            if(it){
                findNavController().navigate(OfflineDirections.toUvod())
                viewModel.afterClick()
            }
        })
        return binding.root
    }

}