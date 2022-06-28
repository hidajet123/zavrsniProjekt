package main.zavrsniprojekt.infoplayer

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import main.zavrsniprojekt.R
import main.zavrsniprojekt.database.PlayerDatabase
import main.zavrsniprojekt.databinding.ActivityMainBinding.inflate
import main.zavrsniprojekt.databinding.FragmentBlankBinding
import main.zavrsniprojekt.databinding.FragmentMyPlayersBinding
import main.zavrsniprojekt.myplayers.MyPlayersViewModel


class InfoPlayerFragment : Fragment() {
    private lateinit var viewModelFactory: InfoPlayerFactory
    private lateinit var viewModel: InfoPlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentBlankBinding.inflate(inflater)
        var application = requireNotNull(activity).application
        val dataSource = PlayerDatabase.getInstance(application).playerDatabaseDao
        viewModelFactory = InfoPlayerFactory(
            dataSource,
            InfoPlayerFragmentArgs.fromBundle(requireArguments()).name,
            InfoPlayerFragmentArgs.fromBundle(requireArguments()).surname,
            InfoPlayerFragmentArgs.fromBundle(requireArguments()).team,
            InfoPlayerFragmentArgs.fromBundle(requireArguments()).position
        );

        viewModel = ViewModelProvider(this, viewModelFactory).get(InfoPlayerViewModel::class.java)

        viewModel.sharePlayer.observe(viewLifecycleOwner, {
            if (it) {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    ("Name: ").plus(viewModel.player.plus("\n")).plus("Team: ").plus(viewModel.team)
                        .plus("\n").plus("Position: ").plus(viewModel.position)
                )

                startActivity(Intent.createChooser(shareIntent, "Posalji"))
                viewModel.noShare()
            }
        })

        binding.info = viewModel
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.deletePlayer()
        findNavController().navigate(InfoPlayerFragmentDirections.backToMyPlayer())

        return true

    }
}