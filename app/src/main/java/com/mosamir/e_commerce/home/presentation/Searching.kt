package com.mosamir.e_commerce.home.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mosamir.e_commerce.databinding.FragmentShoppingBinding
import com.mosamir.e_commerce.home.domain.model.DataX
import com.mosamir.e_commerce.home.domain.model.ProductResponse
import com.mosamir.e_commerce.util.IResult
import com.mosamir.e_commerce.util.NetworkState
import com.mosamir.e_commerce.util.SessionManager
import com.mosamir.e_commerce.util.SessionManager.hide
import com.mosamir.e_commerce.util.SessionManager.show
import com.mosamir.e_commerce.util.getData
import com.mosamir.e_commerce.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Searching : Fragment() {

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
            val token = SessionManager.getToken(requireContext()).toString()
            homeViewModel.searchProducts(token,text)
        }

        observeOnSearch()

    }

    private fun observeOnSearch(){
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.searchProductResult.collect {networkState->
                    when (networkState?.status) {
                        NetworkState.Status.SUCCESS -> {
                            val result = networkState.data as IResult<ProductResponse>
                            data = result.getData()?.data?.data as ArrayList<DataX>
                            val productAdapter = ProductAdapter(requireContext(),data)
                            binding.rvSearchProducts.apply {
                                adapter = productAdapter
                                layoutManager = GridLayoutManager(requireContext(), 2)
                                hasFixedSize()
                            }
                            binding.searchProgressBar.hide()
                        }

                        NetworkState.Status.FAILED -> {
                            showToast(networkState.msg.toString())
                            binding.searchProgressBar.hide()
                        }

                        NetworkState.Status.RUNNING-> {
                            binding.searchProgressBar.show()
                        }

                        else -> {}
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