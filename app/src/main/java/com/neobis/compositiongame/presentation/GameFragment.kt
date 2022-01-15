package com.neobis.compositiongame.presentation

import android.content.res.ColorStateList
import android.graphics.Color.red
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.neobis.compositiongame.databinding.FragmentGameBinding
import com.neobis.compositiongame.domain.entities.GameResult
import java.lang.RuntimeException

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentBinding == null")

    private val args by navArgs<GameFragmentArgs>()
    private val viewModelFactory by lazy {
        //val args = GameFragmentArgs.fromBundle(requireArguments())
        GameViewModelFactory(requireActivity().application, args.level)
    }
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        )[GameViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        observeViewModel()
    }
    private fun observeViewModel() {
        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchFinishedFragment(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun launchFinishedFragment(result: GameResult) {
        findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameFinished(result))
    }
}