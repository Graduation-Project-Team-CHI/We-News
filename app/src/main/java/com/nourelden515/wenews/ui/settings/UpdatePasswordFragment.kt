package com.nourelden515.wenews.ui.settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.nourelden515.wenews.R
import com.nourelden515.wenews.databinding.FragmentUpdatePasswordBinding

class UpdatePasswordFragment : Fragment() {
    private lateinit var binding:FragmentUpdatePasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            binding = FragmentUpdatePasswordBinding.inflate(layoutInflater, container, false)
            return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.confirmPassword.setOnClickListener {
            val newPassword = binding.newPassword.editText?.text.toString()
            val user = FirebaseAuth.getInstance().currentUser
            if (!newPassword.isNullOrEmpty()) {
                user?.updatePassword(newPassword)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Password updated successfully
                        findNavController().navigate(R.id.action_updatePasswordFragment_to_settingsFragment)
                    } else {
                        // An error occurred while updating the password
                        val errorMessage = task.exception?.message
                        Log.e("ChangePassword", errorMessage.toString())
                    }
                }
            } else{
                // Handle the case where the new password is empty or null
                Log.e("ChangePassword", "New password is empty or null")
            }
        }
    }
}