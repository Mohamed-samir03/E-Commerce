package com.mosamir.e_commerce.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.denzcoskun.imageslider.models.SlideModel
import com.mosamir.e_commerce.databinding.FragmentHomeBinding
import com.mosamir.e_commerce.home.domain.model.DataX
import com.mosamir.e_commerce.util.IResult
import com.mosamir.e_commerce.util.SessionManager
import com.mosamir.e_commerce.util.SessionManager.hide
import com.mosamir.e_commerce.util.SessionManager.show
import com.mosamir.e_commerce.util.SessionManager.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController
    private val homeViewModel by viewModels<HomeViewModel>()
    lateinit var data:ArrayList<DataX>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
        data = ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel("https://bit.ly/44tFkVr"))
        imageList.add(SlideModel("https://bit.ly/3qM0v7p"))
        imageList.add(SlideModel("https://bit.ly/45rZ3Gi"))
        binding.imageSlider.setImageList(imageList)

        val token = SessionManager.getToken(requireContext()).toString()
        homeViewModel.getProducts(token)

        homeViewModel.getProductResult.observe(requireActivity()){
            when(it){
                is IResult.Success ->{
                    data = it.data.data.data as ArrayList<DataX>

                    val productAdapter = ProductAdapter(requireContext(),data)
                    binding.rvHomeProduct.apply {
                        adapter = productAdapter
                        layoutManager = GridLayoutManager(requireContext(), 2)
                        hasFixedSize()
                    }
                    binding.homeProgressBar.hide()
                }
                is IResult.Fail ->{
                    toast(it.error.toString())
                    binding.homeProgressBar.hide()
                }
                is IResult.Loading ->{
                    binding.homeProgressBar.show()
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