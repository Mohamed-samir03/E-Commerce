package com.mosamir.e_commerce.shopping.presentation

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
import androidx.recyclerview.widget.GridLayoutManager
import com.mosamir.e_commerce.databinding.FragmentFavouriteBinding
import com.mosamir.e_commerce.shopping.domain.model.favourite.DataX
import com.mosamir.e_commerce.shopping.domain.model.favourite.FavouriteResponse
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
class Favourite : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController
    private val favouriteViewModel by viewModels<HomeViewModel>()
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
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val token = SessionManager.getToken(requireContext()).toString()
        favouriteViewModel.getFavourites(token)

        observeOnGetFavourites()

    }

    private fun observeOnGetFavourites(){
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                favouriteViewModel.getFavouritesResult.collect {networkState->
                    when (networkState?.status) {
                        NetworkState.Status.SUCCESS -> {
                            val result = networkState.data as IResult<FavouriteResponse>
                            data = result.getData()?.data?.data as ArrayList<DataX>
                            val productAdapter = FavouriteAdapter(requireContext(), data)
                            binding.rvFavourites.apply {
                                adapter = productAdapter
                                layoutManager = GridLayoutManager(requireContext(), 2)
                                hasFixedSize()
                            }
                            binding.favouritesProgressBar.hide()
                        }

                        NetworkState.Status.FAILED -> {
                            showToast(networkState.msg.toString())
                            binding.favouritesProgressBar.hide()
                        }

                        NetworkState.Status.RUNNING-> {
                            binding.favouritesProgressBar.show()
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