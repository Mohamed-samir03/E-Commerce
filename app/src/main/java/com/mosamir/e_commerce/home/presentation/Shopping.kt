package com.mosamir.e_commerce.home.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mosamir.e_commerce.databinding.FragmentShoppingBinding
import com.mosamir.e_commerce.home.domain.model.DataX
import com.mosamir.e_commerce.util.IResult
import com.mosamir.e_commerce.util.SessionManager.hide
import com.mosamir.e_commerce.util.SessionManager.show
import com.mosamir.e_commerce.util.SessionManager.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Shopping : Fragment() {

    private var _binding: FragmentShoppingBinding? = null
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
        _binding = FragmentShoppingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etSearchProducts.requestFocus()

        binding.etSearchProducts.addTextChangedListener {
            val text = binding.etSearchProducts.text.toString()
            homeViewModel.searchProducts(text)
        }

        homeViewModel.searchProductResult.observe(requireActivity()){
            when(it){
                is IResult.Success ->{
                    data = it.data.data.data as ArrayList<DataX>
                    val productAdapter = ProductAdapter(requireContext(),data)
                    binding.rvSearchProducts.apply {
                        adapter = productAdapter
                        layoutManager = GridLayoutManager(requireContext(), 2)
                        hasFixedSize()
                    }
                    binding.searchProgressBar.hide()
                }
                is IResult.Fail ->{
                    toast(it.error.toString())
                    binding.searchProgressBar.hide()
                }
                is IResult.Loading ->{
                    binding.searchProgressBar.show()
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