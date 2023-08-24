package com.mosamir.e_commerce.profile.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mosamir.e_commerce.AuthActivity
import com.mosamir.e_commerce.HomeActivity
import com.mosamir.e_commerce.databinding.FragmentProfileBinding
import com.mosamir.e_commerce.login.presentation.LoginViewModel
import com.mosamir.e_commerce.util.IResult
import com.mosamir.e_commerce.util.SessionManager
import com.mosamir.e_commerce.util.SessionManager.hide
import com.mosamir.e_commerce.util.SessionManager.show
import com.mosamir.e_commerce.util.SessionManager.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Profile : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController
    private val profileViewModel by viewModels<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val token = SessionManager.getToken(requireContext()).toString()

        profileViewModel.getProfileUser(token)

        profileViewModel.getProfileResult.observe(requireActivity()){
            when(it){
                is IResult.Success ->{
                    binding.etProfileName.setText(it.data.data.name)
                    binding.etProfileEmail.setText(it.data.data.email)
                    binding.etProfilePhone.setText(it.data.data.phone)
                    binding.profileProgressBar.hide()
                }
                is IResult.Fail ->{
                    toast(it.error.toString())
                    binding.profileProgressBar.hide()
                }
                is IResult.Loading ->{
                    binding.profileProgressBar.show()
                }
                else -> {}
            }
        }

        binding.logout.setOnClickListener {
            SessionManager.clearData(requireContext())
            val intent = Intent(requireContext(), AuthActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}