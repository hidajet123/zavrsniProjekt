package main.zavrsniprojekt.uvod

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import main.zavrsniprojekt.database.PlayerDatabase
import main.zavrsniprojekt.databinding.FragmentUvodBinding


class Uvod : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUvodBinding.inflate(inflater)

        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        val viewModel = ViewModelProvider(this).get(UvodViewModel::class.java)

        binding.nextFragment = viewModel

        viewModel.listAllPlayer.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished) {
                if (isConnected == false) {
                    findNavController().navigate(UvodDirections.toOffline())
                } else {
                    findNavController().navigate(UvodDirections.toOverview(""))
                    viewModel.finish()
                }
            }
        })
        viewModel.listPlayerDatabase.observe(viewLifecycleOwner, { database ->
            if (database) {
                findNavController().navigate(UvodDirections.toMyPlayers())
                viewModel.playerFinish()

            }
        })

        return binding.root
    }

}