package com.mosamir.e_commerce.util

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun AppCompatActivity.showToast(massage: Any) {
    Toast.makeText(this, "$massage", Toast.LENGTH_LONG).show()
}

fun Fragment.showToast(massage: Any) {
    Toast.makeText(requireContext(), "$massage", Toast.LENGTH_LONG).show()
}

fun Fragment.showToastShort(massage: Any) {
    Toast.makeText(requireContext(), "$massage", Toast.LENGTH_SHORT).show()
}

fun View.visibilityInVisible() {
    this.visibility = View.INVISIBLE
}

fun View.visibilityGone() {
    this.visibility = View.GONE
}

fun View.visibilityVisible() {
    this.visibility = View.VISIBLE
}

fun Fragment.showSnackBarMessage(message: String) {
    val snack = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
    snack.show()
}

fun View.disable() {
    isEnabled = false
}

fun View.enabled() {
    isEnabled = true
}