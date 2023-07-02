package com.nourelden515.wenews.ui.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.nourelden515.wenews.BuildConfig
import com.nourelden515.wenews.R
import com.nourelden515.wenews.data.repository.UserRepository
import com.nourelden515.wenews.databinding.FragmentSettingsBinding
import com.nourelden515.wenews.ui.base.ViewModelFactory

class
SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory(UserRepository())
        )[SettingsViewModel::class.java]
    }
    lateinit var versionTV: TextView
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        versionTV = binding.Version
        val version =
            "Version " + BuildConfig.VERSION_NAME + "." + BuildConfig.VERSION_CODE.toString()
        versionTV.text = version
        binding.changePassword.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_changePasswordFragment)
        }
        binding.logOut.setOnClickListener {
            viewModel.logOut()
            findNavController().navigate(R.id.action_settingsFragment_to_loginFragment)
        }
        val switch = binding.theme
        val sharedPreferences = requireActivity().getSharedPreferences("Mode", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val nightMode = sharedPreferences.getBoolean("night", false)

        if (nightMode) {
            switch.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        binding.theme.setOnCheckedChangeListener { _, isChecked ->

            if (!isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("night", false)
                editor.apply()

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("night", true)
                editor.apply()
            }
        }
    binding.changePassword.setOnClickListener {
        findNavController().navigate(R.id.action_settingsFragment_to_changePasswordFragment)
    }
   binding.contactUs.setOnClickListener {
       val emailIntent =Intent(Intent.ACTION_SENDTO,Uri.fromParts("mailto","mayarhassan@gmail.com",null))
       startActivity(Intent.createChooser(emailIntent,"send email..."))
         }
   binding.aboutUs.setOnClickListener {
       findNavController().navigate(R.id.action_settingsFragment_to_aboutUSFragment)
   }
   binding.privacyPolicy.setOnClickListener {
       findNavController().navigate(R.id.action_settingsFragment_to_privacyPolicyFragment)
   }
    }
}




