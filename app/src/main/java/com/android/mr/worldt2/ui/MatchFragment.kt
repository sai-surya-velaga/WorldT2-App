package com.android.mr.worldt2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.android.mr.worldt2.databinding.FragmentMatchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchFragment : Fragment() {

    private var _binding: FragmentMatchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MatchViewModel by viewModels()
    private val args: MatchFragmentArgs by navArgs() // gets teamA and teamB from navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Start match with selected teams
        viewModel.startMatch(args.team1, args.team2)

        // Observe match state and update UI
        viewModel.matchState.observe(viewLifecycleOwner) { state ->

            val battingTeam = state.currentBattingTeam
            val bowlingTeam = state.otherTeam

            // Update Team Names
            binding.tvBattingTeam.text = "${battingTeam.name} (Batting)"
            binding.tvBowlingTeam.text = "${bowlingTeam.name} (Bowling)"

            // Update Scores
            binding.tvBattingScore.text = "Score: ${state.runs}/${state.wickets}"
            binding.tvBowlingScore.text = "Score: ${state.otherTeamScore}/${state.otherTeamWickets}"

            // Update Overs (6 balls = 1 over)
            val battingOvers = "${state.balls / 6}.${state.balls % 6}"
            val bowlingOvers = "${state.otherTeamBalls / 6}.${state.otherTeamBalls % 6}"

            binding.tvBattingOvers.text = "Overs: $battingOvers"
            binding.tvBowlingOvers.text = "Overs: $bowlingOvers"

            // Update outcome and status
            binding.tvBallOutcome.text = "${state.lastOutcome}"

            // Enable or disable button
            binding.btnPlayBall.isEnabled = !state.matchEnded
            if (state.matchEnded) {
                binding.tvBallOutcome.text =""
                binding.btnPlayBall.text = "Match Over"
               if (state.runs > state.otherTeamScore) binding.tvMatchResult.text = "${state.currentBattingTeam.name} wins"
                else binding.tvMatchResult.text ="${state.otherTeam.name} wins"
            }
        }

        // Play next ball
        binding.btnPlayBall.setOnClickListener {
            viewModel.playNextBall()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

