package com.example.newsnest.presentation.auth.signinscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.newsnest.R
import com.example.newsnest.databinding.FragmentLoginBinding
import com.example.newsnest.domain.model.UserRequest
import com.example.newsnest.presentation.auth.authviewmodel.AuthViewModel
import com.example.newsnest.utils.NetWorkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            val validationResult = validateDataUserInput()
            if (validationResult.first) {
                authViewModel.signin(getUserRequest())

            }//agr fist true hu tu yah chaly ga otherwide else
            else {
                binding.txtError.text =
                    validationResult.second//second error set krdia ju error a ay ga ab apis sy
            }
            //  findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        binding.btnSignUp.setOnClickListener {
            findNavController().popBackStack()
        }
        bindObserver()

    }


    private fun getUserRequest(): UserRequest {
        val emailAddress = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text.toString()
//        val username = binding.txtUsername.text.toString()
        return UserRequest(emailAddress, password, "")
    }

    private fun validateDataUserInput(): Pair<Boolean, String> {
        val userRequest = getUserRequest()
        //  return authViewModel.validateCredentials(username, emailAddress = emailAddress, password = password)
        return authViewModel.validateCredentials(
            userRequest.username,
            userRequest.email,
            userRequest.password,
            true
        )
    }

    private fun bindObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                authViewModel.userResponse.collect {
                    // binding.progressBar.isVisible = false
                    when (it) {
                        is NetWorkResult.Idle -> {
                            binding.progressBar.isVisible = false
                        }

                        is NetWorkResult.Loading -> {
                            binding.progressBar.isVisible = true
                        }

                        is NetWorkResult.Success -> {
                            binding.progressBar.isVisible = false
                            //token
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        }

                        is NetWorkResult.Error -> {
                            binding.progressBar.isVisible = false
                            binding.txtError.text = it.message
                        }


                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


