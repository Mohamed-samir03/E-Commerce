package com.mosamir.e_commerce.register.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mosamir.e_commerce.util.HomeActivity
import com.mosamir.e_commerce.databinding.FragmentRegisterBinding
import com.mosamir.e_commerce.util.IResult
import com.mosamir.e_commerce.util.SessionManager.hide
import com.mosamir.e_commerce.util.SessionManager.show
import com.mosamir.e_commerce.util.SessionManager.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Register : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController
    private val registerViewModel by viewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            val name = binding.etRegisterName.text.toString()
            val phone = binding.etRegisterPhone.text.toString()
            val email = binding.etRegisterEmail.text.toString()
            val password = binding.etRegisterPassword.text.toString()
            registerViewModel.registerUser(name=name,phone=phone,email=email,password=password, image = "")
        }

        binding.tvHaveAccount.setOnClickListener {
            val action = RegisterDirections.actionRegisterToLogin()
            mNavController.navigate(action)
        }

        registerViewModel.registerResult.observe(requireActivity()){
            when(it){
                is IResult.Success ->{
                    val intent = Intent(requireContext(), HomeActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                    binding.registerProgressBar.hide()
                }
                is IResult.Fail ->{
                    toast(it.error.toString())
                    binding.registerProgressBar.hide()
                }
                is IResult.Loading ->{
                    binding.registerProgressBar.show()
                }
                else -> {}
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}