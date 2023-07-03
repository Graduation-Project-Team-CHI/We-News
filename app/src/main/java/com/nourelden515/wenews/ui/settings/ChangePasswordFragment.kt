package com.nourelden515.wenews.ui.settings

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.nourelden515.wenews.R
import com.nourelden515.wenews.data.repository.UserRepository
import com.nourelden515.wenews.databinding.FragmentChangePasswordBinding
import com.nourelden515.wenews.ui.base.ViewModelFactory

class ChangePasswordFragment : Fragment() {
    private lateinit var binding: FragmentChangePasswordBinding
    private lateinit var auth: FirebaseAuth
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory(UserRepository())
        )[SettingsViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentChangePasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding.save.setOnClickListener {
            changePassword()
        }
    }

    private fun changePassword() {
        if (binding.yourPass.editText?.text!!.isNotEmpty() &&
            binding.newPass.editText?.text!!.isNotEmpty() &&
            binding.confirmPassword.editText?.text!!.isNotEmpty()
        ) {
            if (binding.newPass.editText?.text.toString() == binding.confirmPassword.editText?.text.toString()) {
                val user: FirebaseUser? = auth.currentUser
                if (user != null && user.email != null) {
                    val credential = EmailAuthProvider
                        .getCredential(user.email!!, binding.yourPass.editText?.text.toString())

                    // Prompt the user to re-provide their sign-in credentials
                    user.reauthenticate(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Log.d(TAG, "User re-authenticated.")
                                Toast.makeText(
                                    context,
                                    "re-authenticated Successful",
                                    Toast.LENGTH_SHORT
                                ).show()
                                user.updatePassword(binding.newPass.editText?.text.toString())
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Log.d(TAG, "User password updated.")
                                            Toast.makeText(
                                                context,
                                                "Password updated",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            viewModel.logOut()
                                            findNavController().navigate(R.id.action_changePasswordFragment_to_loginFragment)
                                        }
                                    }
                            } else {
                                Toast.makeText(
                                    context,
                                    "re-authenticated field",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    findNavController().navigate(R.id.action_changePasswordFragment_to_loginFragment)
                }
            } else {
                Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "please enter all fields", Toast.LENGTH_SHORT).show()
        }
    }
}
