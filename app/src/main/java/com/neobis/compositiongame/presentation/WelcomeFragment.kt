package com.neobis.compositiongame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.neobis.compositiongame.R
import com.neobis.compositiongame.databinding.FragmentGameBinding
import com.neobis.compositiongame.databinding.FragmentWellcomeBinding
import java.lang.RuntimeException

class WelcomeFragment : Fragment(){
    private var _binding : FragmentWellcomeBinding? = null
    private val binding : FragmentWellcomeBinding
        get() = _binding ?: throw RuntimeException("FragmentBinding == null")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWellcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonUnderstand.setOnClickListener{
            launchChooseLevelFragment()
        }
    }

    private fun launchChooseLevelFragment() {
        findNavController().navigate(R.id.action_welcomeFragment_to_fragmentChooseLevel)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
