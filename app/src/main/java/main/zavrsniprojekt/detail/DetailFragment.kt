/*
 *  Copyright 2019, The Android Open Source Project
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package main.zavrsniprojekt.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import main.zavrsniprojekt.database.PlayerDatabase
import main.zavrsniprojekt.databinding.FragmentDetailBinding
import main.zavrsniprojekt.uvod.Uvod

class DetailFragment : Fragment() {
    private lateinit var viewModel1: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val data = DetailFragmentArgs.fromBundle(requireArguments()).selectedProperty


        val dataSource = PlayerDatabase.getInstance(application).playerDatabaseDao
        val viewModelFactory = DetailViewModelFactory(data, dataSource, application)
        viewModel1 = ViewModelProvider(
            this, viewModelFactory
        ).get(DetailViewModel::class.java)
        binding.viewModel = viewModel1

        viewModel1.insertPlayer.observe(viewLifecycleOwner, {
            if (it) {
                findNavController().navigate(DetailFragmentDirections.myPlayers())
            }
        })

        return binding.root
    }

}