package com.nourelden515.wenews.ui.settings

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.nourelden515.wenews.R
import com.nourelden515.wenews.databinding.FragmentChangePasswordBinding

@Suppress("UNREACHABLE_CODE", "NAME_SHADOWING")
class ChangePasswordFragment : Fragment() {
    private lateinit var binding:FragmentChangePasswordBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mUser: FirebaseUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_change_password, container, false)
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth.currentUser!!
        binding.save.setOnClickListener {
            val currentPassword = binding.yourPass.editText?.text.toString()
            val newPassword =  binding.newPass.editText?.text.toString()
            val confirmPassword =  binding.confirmPassword.editText?.text.toString()
            if (TextUtils.isEmpty(currentPassword)) {
                binding.yourPass.error = "Enter current password"
                binding.yourPass.requestFocus()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(newPassword)) {
                binding.newPass.error = "Enter new password"
                binding.newPass.requestFocus()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(confirmPassword)) {
                binding.confirmPassword.error = "Confirm new password"
                binding.confirmPassword.requestFocus()
                return@setOnClickListener
            }

            if (newPassword != confirmPassword) {
                binding.confirmPassword.error = "Passwords do not match"
                binding.confirmPassword.requestFocus()
                return@setOnClickListener
            }
            val credential = EmailAuthProvider.getCredential(mUser.email!!, currentPassword)

            mUser.reauthenticate(credential).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mUser.updatePassword(newPassword).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val profileUpdates = UserProfileChangeRequest.Builder()
                                .setDisplayName("New Name")
                                .build()

                            mUser.updateProfile(profileUpdates)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        // User profile updated successfully
                                        Toast.makeText(requireContext(), "User profile updated successfully", Toast.LENGTH_LONG).show()

                                    } else {
                                        // User profile update failed
                                        Toast.makeText(requireContext(), "User profile update failed", Toast.LENGTH_LONG).show()

                                    }
                                }
                        } else {
                            Toast.makeText(requireContext(), "Password update failed", Toast.LENGTH_LONG).show()

                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Re authentication failed", Toast.LENGTH_LONG).show()

                }
            }

        }
        return view
    }
    }
