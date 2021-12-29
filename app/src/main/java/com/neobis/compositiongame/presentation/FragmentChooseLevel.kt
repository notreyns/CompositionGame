package com.neobis.compositiongame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.neobis.compositiongame.R
import com.neobis.compositiongame.databinding.FragmentChooseLevelBinding
import com.neobis.compositiongame.databinding.FragmentGameBinding
import com.neobis.compositiongame.domain.entities.Level
import java.lang.RuntimeException

class FragmentChooseLevel : Fragment() {
    private var _binding : FragmentChooseLevelBinding? = null
    private val binding : FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentBinding == null")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLevelTest.setOnClickListener{
            launchGameFragment(Level.TEST)
        }
        binding.buttonLevelEasy.setOnClickListener{
            launchGameFragment(Level.EASY)
        }
        binding.buttonLevelNormal.setOnClickListener{
            launchGameFragment(Level.MEDIUM)
        }
        binding.buttonLevelHard.setOnClickListener{
            launchGameFragment(Level.HARD)
        }
    }
    private fun launchGameFragment(level: Level){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance(level))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        const val NAME = "FragmentChooseLevel"

        fun newInstance(): FragmentChooseLevel{
            return FragmentChooseLevel()
        }
    }
}