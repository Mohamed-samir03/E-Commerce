package com.mosamir.e_commerce.shopping.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.denzcoskun.imageslider.models.SlideModel
import com.mosamir.e_commerce.databinding.FragmentHomeBinding
import com.mosamir.e_commerce.shopping.domain.model.DataX
import com.mosamir.e_commerce.shopping.domain.model.ProductResponse
import com.mosamir.e_commerce.shopping.domain.model.adddelete.AddDeleteFavouriteResponse
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
class Home : Fragment(),ProductAdapter.OnFavoriteClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController
    private val homeViewModel by viewModels<HomeViewModel>()
    lateinit var data:ArrayList<DataX>
    private var token = ""
    lateinit var productAdapter:ProductAdapter


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

        token = SessionManager.getToken(requireContext()).toString()

        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel("https://bit.ly/44tFkVr"))
        imageList.add(SlideModel("https://bit.ly/3qM0v7p"))
        imageList.add(SlideModel("https://bit.ly/45rZ3Gi"))
        binding.imageSlider.setImageList(imageList)

        val token = SessionManager.getToken(requireContext()).toString()
        homeViewModel.getProducts(token)

        observeOnGetProducts()
        observeOnAddDeleteProducts()
    }

    private fun observeOnGetProducts(){
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.getProductResult.collect {networkState->
                    when (networkState?.status) {
                        NetworkState.Status.SUCCESS -> {
                           val result = networkState.data as IResult<ProductResponse>
                            data = result.getData()?.data?.data as ArrayList<DataX>
                            productAdapter= ProductAdapter(requireContext(), data)
                            binding.rvHomeProduct.apply {
                                adapter = productAdapter
                                layoutManager = GridLayoutManager(requireContext(), 2)
                                hasFixedSize()
                            }
                            productAdapter.setOnFavoriteClickListener(this@Home)
                            binding.homeProgressBar.hide()
                        }

                        NetworkState.Status.FAILED -> {
                            showToast(networkState.msg.toString())
                            binding.homeProgressBar.hide()
                        }

                        NetworkState.Status.RUNNING-> {
                            binding.homeProgressBar.show()
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun observeOnAddDeleteProducts(){
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.addDeleteFavouriteResult.collect {networkState->
                    when (networkState?.status) {
                        NetworkState.Status.SUCCESS -> {
                            val result = networkState.data as IResult<AddDeleteFavouriteResponse>

                            binding.homeProgressBar.hide()
                        }

                        NetworkState.Status.FAILED -> {
                            showToast(networkState.msg.toString())
                            binding.homeProgressBar.hide()
                        }

                        NetworkState.Status.RUNNING-> {
                            binding.homeProgressBar.show()
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

    override fun onFavoriteClick(product: DataX, position: Int) {
        homeViewModel.addDeleteFavourite(token,product.id)
//        productAdapter.notifyItemChanged(position)
    }

}