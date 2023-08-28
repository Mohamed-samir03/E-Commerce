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
import com.mosamir.e_commerce.util.AuthActivity
import com.mosamir.e_commerce.databinding.FragmentProfileBinding
import com.mosamir.e_commerce.profile.domain.model.UpdateProfileRequest
import com.mosamir.e_commerce.util.IResult
import com.mosamir.e_commerce.util.SessionManager
import com.mosamir.e_commerce.util.SessionManager.disable
import com.mosamir.e_commerce.util.SessionManager.enabled
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

        binding.editProfile.setOnClickListener {
            showEditDesign()
        }

        binding.btnProfileSave.setOnClickListener {

            val name = binding.etProfileName.text.toString()
            val email = binding.etProfileEmail.text.toString()
            val phone = binding.etProfilePhone.text.toString()
            val password = binding.etProfilePassword.text.toString()

            if (!password.isNullOrBlank()){
                val profile = UpdateProfileRequest(email,"",name, password, phone)
                profileViewModel.updateProfileUser(token,profile)
            }else{
                toast("you should enter password")
            }

        }

        profileViewModel.updateProfileResult.observe(requireActivity()){
            when(it){
                is IResult.Success ->{
                    binding.etProfileName.setText(it.data.data.name)
                    binding.etProfileEmail.setText(it.data.data.email)
                    binding.etProfilePhone.setText(it.data.data.phone)
                    binding.profileProgressBar.hide()
                    hideEditDesign()
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

        binding.btnProfileCancel.setOnClickListener {
            hideEditDesign()
            profileViewModel.getProfileUser(token)
        }

        binding.logout.setOnClickListener {
            SessionManager.clearData(requireContext())
            val intent = Intent(requireContext(), AuthActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

    }

    private fun showEditDesign(){
        binding.etProfileEmail.enabled()
        binding.etProfilePhone.enabled()
        binding.etProfileName.enabled()
        binding.textInputLayout.show()
        binding.btnProfileSave.show()
        binding.btnProfileCancel.show()
        binding.logout.hide()
        binding.editProfile.hide()
        binding.tvProfile.text = "Edit Profile"
    }

    private fun hideEditDesign(){
        binding.etProfileEmail.disable()
        binding.etProfilePhone.disable()
        binding.etProfileName.disable()
        binding.textInputLayout.hide()
        binding.btnProfileSave.hide()
        binding.btnProfileCancel.hide()
        binding.logout.show()
        binding.editProfile.show()
        binding.tvProfile.text = "Profile"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}