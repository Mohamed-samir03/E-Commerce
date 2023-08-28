package com.mosamir.e_commerce.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.denzcoskun.imageslider.models.SlideModel
import com.mosamir.e_commerce.databinding.FragmentHomeBinding
import com.mosamir.e_commerce.home.domain.model.DataX


class Home : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
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


        val data:ArrayList<DataX> = ArrayList()


        data.add(DataX(
            description = "ابل ايفون 12 برو ماكق",
            discount = 0,
            id = 20,
            image = "https://student.valuxapps.com/storage/uploads/products/1615440322npwmU.71DVgBTdyLL._SL1500_.jpg",
            images = listOf(),
            in_cart = false,
            in_favorites = true,
            name = "ابل ايفون 12 برو ماكس",
            old_price = 500.0,
            price = 500.0
        ))

        data.add(DataX(
            description = "ابل ايفون 12 برو ماكس - 256جيجابيت, ازرق",
            discount = 40,
            id = 20,
            image = "https://student.valuxapps.com/storage/uploads/products/1615440322npwmU.71DVgBTdyLL._SL1500_.jpg",
            images = listOf(),
            in_cart = false,
            in_favorites = false,
            name = "ابل ايفون 12 برو ماكس - 256جيجابيت, ازرق",
            old_price = 500.0,
            price = 300.0
        ))

        data.add(DataX(
            description = "ابل ايفون 12 برو ماكس - 256جيجابيت, ازرق",
            discount = 0,
            id = 20,
            image = "https://student.valuxapps.com/storage/uploads/products/1615440322npwmU.71DVgBTdyLL._SL1500_.jpg",
            images = listOf(),
            in_cart = false,
            in_favorites = true,
            name = "ابل ايفون 12 برو ماكس - 256جيجابيت, ازرق",
            old_price = 500.0,
            price = 500.0
        ))

        val productAdapter = ProductAdapter(requireContext(),data)
        binding.rvHomeProduct.apply {
            adapter = productAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            hasFixedSize()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}