package com.mosamir.e_commerce.profile.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mosamir.e_commerce.util.AuthActivity
import com.mosamir.e_commerce.databinding.FragmentProfileBinding
import com.mosamir.e_commerce.profile.domain.model.ProfileResponse
import com.mosamir.e_commerce.profile.domain.model.UpdateProfileRequest
import com.mosamir.e_commerce.util.Constants
import com.mosamir.e_commerce.util.IResult
import com.mosamir.e_commerce.util.NetworkState
import com.mosamir.e_commerce.util.SessionManager
import com.mosamir.e_commerce.util.SessionManager.disable
import com.mosamir.e_commerce.util.SessionManager.enabled
import com.mosamir.e_commerce.util.SessionManager.hide
import com.mosamir.e_commerce.util.SessionManager.show
import com.mosamir.e_commerce.util.SessionManager.toast
import com.mosamir.e_commerce.util.getData
import com.mosamir.e_commerce.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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

//        profileViewModel.getProfileUser(token)
//
//        observeOnGetProfile()
        getProfileFromLocal()


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

        observeOnUpdateProfile()

        binding.btnProfileCancel.setOnClickListener {
            hideEditDesign()
//            profileViewModel.getProfileUser(token)
            getProfileFromLocal()
        }

        binding.logout.setOnClickListener {
            SessionManager.clearData(requireContext())
            val intent = Intent(requireContext(), AuthActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

    }

    private fun observeOnGetProfile(){
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                profileViewModel.getProfileResult.collect {networkState->
                    when (networkState?.status) {
                        NetworkState.Status.SUCCESS -> {
                            val result = networkState.data as IResult<ProfileResponse>
                            binding.etProfileName.setText(result.getData()?.data?.name)
                            binding.etProfileEmail.setText(result.getData()?.data?.email)
                            binding.etProfilePhone.setText(result.getData()?.data?.phone)
                            binding.profileProgressBar.hide()
                        }

                        NetworkState.Status.FAILED -> {
                            showToast(networkState.msg.toString())
                            binding.profileProgressBar.hide()
                        }

                        NetworkState.Status.RUNNING-> {
                            binding.profileProgressBar.show()
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun observeOnUpdateProfile(){
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                profileViewModel.updateProfileResult.collect {networkState->
                    when (networkState?.status) {
                        NetworkState.Status.SUCCESS -> {
                            val result = networkState.data as IResult<ProfileResponse>
                            binding.etProfileName.setText(result.getData()?.data?.name)
                            binding.etProfileEmail.setText(result.getData()?.data?.email)
                            binding.etProfilePhone.setText(result.getData()?.data?.phone)
                            binding.profileProgressBar.hide()
                            hideEditDesign()
                        }

                        NetworkState.Status.FAILED -> {
                            showToast(networkState.msg.toString())
                            binding.profileProgressBar.hide()
                        }

                        NetworkState.Status.RUNNING-> {
                            binding.profileProgressBar.show()
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun getProfileFromLocal(){
        val name = SessionManager.getString(requireContext(), Constants.USER_NAME)
        val email = SessionManager.getString(requireContext(), Constants.USER_EMAIL)
        val phone = SessionManager.getString(requireContext(), Constants.USER_PHONE)
        binding.etProfileName.setText(name)
        binding.etProfileEmail.setText(email)
        binding.etProfilePhone.setText(phone)
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