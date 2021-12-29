package com.neobis.compositiongame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.neobis.compositiongame.R
import com.neobis.compositiongame.databinding.FragmentGameBinding
import com.neobis.compositiongame.domain.entities.GameResult
import com.neobis.compositiongame.domain.entities.Level
import java.lang.RuntimeException

class GameFragment : Fragment() {
    private lateinit var level: Level
    private var _binding : FragmentGameBinding? = null
    private val binding : FragmentGameBinding
    get() = _binding ?: throw RuntimeException("FragmentBinding == null")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun parseArgs(){
       requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
           level = it
       }
    }

    private fun launchFinishedFragment(result: GameResult){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinished.newInstance(result))
            .addToBackStack(null)
            .commit()
    }

    companion object{
        const val KEY_LEVEL = "level"
        const val NAME = "GameFragment"

        fun newInstance(level: Level): GameFragment{
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }
}