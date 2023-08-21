package com.mosamir.e_commerce.login.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mosamir.e_commerce.databinding.FragmentLoginBinding
import com.mosamir.e_commerce.HomeActivity
import com.mosamir.e_commerce.util.IResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.goBackFromLogin.setOnClickListener {

        }

        binding.btnLogin.setOnClickListener {
            loginViewModel.loginUser(binding.etLoginEmail.text.toString(),binding.etLoginPassword.text.toString())
        }

        binding.tvNotHaveAccount.setOnClickListener {
            val action = LoginDirections.actionLoginToRegister()
            mNavController.navigate(action)
        }

        loginViewModel.loginResult.observe(requireActivity()){
            when(it){
                is IResult.Success ->{
                    val intent = Intent(requireContext(), HomeActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                    binding.loginProgressBar.isVisible = false
                }
                is IResult.Fail ->{
                    Toast.makeText(requireContext(), it.error.toString(), Toast.LENGTH_SHORT).show()
                    binding.loginProgressBar.isVisible = false
                }
                is IResult.Loading ->{
                    binding.loginProgressBar.isVisible = true
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}