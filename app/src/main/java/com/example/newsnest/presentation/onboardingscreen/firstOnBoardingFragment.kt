package com.example.newsnest.presentation.onboardingscreen
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsnest.R
import com.example.newsnest.core.shared.utils.Pref
import com.example.newsnest.databinding.FragmentFirstBinding
import com.example.newsnest.databinding.FragmentOnboardingBinding
class firstOnBoardingFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textNext.text = "Finish"
        binding.textNext.setOnClickListener {
            Pref.setOnBoardPref(requireContext(),true)
            findNavController().navigate(R.id.action_onboardingFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}