package com.neobis.compositiongame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.neobis.compositiongame.R
import com.neobis.compositiongame.databinding.FragmentGameBinding
import com.neobis.compositiongame.databinding.FragmentGameFinishedBinding
import com.neobis.compositiongame.domain.entities.GameResult
import com.neobis.compositiongame.domain.entities.GameSettings
import com.neobis.compositiongame.domain.entities.Level
import java.lang.RuntimeException

class GameFinished : Fragment() {
    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentBinding == null")

    private lateinit var gameResult: GameResult
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[GameViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    retryGame()
                }
            })

        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
        viewModelObservers()
    }

    private fun viewModelObservers() {
        val imageRes = if (gameResult.winner) {
            R.drawable.ic_smile
        } else R.drawable.ic_sad
        binding.emojiResult.setImageResource(imageRes)

        binding.tvRequiredAnswers.text = String.format(
            getString(R.string.required_score),
            gameResult.gameSettings.minCountOfRightAnswers
        )
        binding.tvScoreAnswers.text = String.format(
            getString(R.string.score_answers),
            gameResult.countOfRightAnswers)

        binding.tvRequiredPercentage.text = String.format(
            getString(R.string.required_percentage),
            gameResult.gameSettings.minPercentOfRightAnswers
        )
        binding.tvScorePercentage.text = String.format(
            getString(R.string.score_percentage),
            getPercentOfRightAnswers())
    }

    private fun getPercentOfRightAnswers()= with(gameResult){
        if(countOfRightAnswers==0) 0
        else ((countOfRightAnswers / countOfQuestions.toDouble()) *100).toInt()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager
            .popBackStack(GameFragment.NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    private fun parseArgs() {
        requireArguments().getParcelable<GameResult>(KEY_RESULTS)?.let {
            gameResult = it
        }
    }

    companion object {
        const val KEY_RESULTS = "level"

        fun newInstance(result: GameResult): GameFinished {
            return GameFinished().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_RESULTS, result)
                }
            }
        }
    }
}