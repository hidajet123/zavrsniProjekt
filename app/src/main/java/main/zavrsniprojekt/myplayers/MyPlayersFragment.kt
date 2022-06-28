package main.zavrsniprojekt.myplayers

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import main.zavrsniprojekt.database.PlayerDatabase
import main.zavrsniprojekt.databinding.FragmentMyPlayersBinding

class MyPlayersFragment : Fragment() {
    private lateinit var viewModel: MyPlayersViewModel
    val listener = this
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMyPlayersBinding.inflate(inflater)
        var application = requireNotNull(activity).application
        val dataSource = PlayerDatabase.getInstance(application).playerDatabaseDao
        val viewModelFactory = MyPlayersFactory(dataSource, application)

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(MyPlayersViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.view = viewModel
        viewModel.lista.observe(viewLifecycleOwner, {
            binding.playerRecycler.apply {
                layoutManager = LinearLayoutManager(this.context)
                adapter = MyPlayersAdapter(it, viewModel.listener)
            }
        })

        viewModel.delete.observe(viewLifecycleOwner, {
            findNavController().navigate(
                MyPlayersFragmentDirections.toInfo(
                    viewModel.lista.value?.get(it)?.name.toString(),
                    viewModel.lista.value?.get(it)?.surname.toString(),
                    viewModel.lista.value?.get(it)?.currentTeam.toString(),
                    viewModel.lista.value?.get(it)?.position.toString()
                )
            )

        })

        return binding.root
    }
}