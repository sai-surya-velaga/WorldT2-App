package com.android.mr.worldt2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mr.worldt2.databinding.FragmentTeamSelectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamSelectionFragment : Fragment() {

    private var _binding: FragmentTeamSelectionBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TeamSelectionViewModel
    private lateinit var adapter: TeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))[TeamSelectionViewModel::class.java]

        viewModel.teams.observe(viewLifecycleOwner) { teams ->
            adapter = TeamAdapter(teams) { selectedTeams ->
                binding.startMatchButton.isEnabled = selectedTeams.size == 2
            }
            binding.teamRecyclerView.adapter = adapter
        }

        binding.startMatchButton.setOnClickListener {
            val selected = adapter.getSelectedTeams()
            Toast.makeText(requireContext(), "${selected[0].name} vs ${selected[1].name}", Toast.LENGTH_SHORT).show()
            val action = TeamSelectionFragmentDirections
                .actionTeamSelectionFragmentToMatchFragment(selected[0], selected[1])
            findNavController().navigate(action)
        }
        binding.teamRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        viewModel.loadTeams()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
