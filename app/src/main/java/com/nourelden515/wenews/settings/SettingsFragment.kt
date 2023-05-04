package com.nourelden515.wenews.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.nourelden515.wenews.BuildConfig
import com.nourelden515.wenews.authentication.AuthenticationActivity
import com.nourelden515.wenews.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var viewModel: SettingsViewModel
    lateinit var versionTV: TextView
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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

        binding.logOut.setOnClickListener {
//          requireActivity().run{
//              startActivity(Intent(this, LoginActivity::class.java))
//              finish()
//          }
//            auth.signOut()
//            val intent = Intent(view.context, LoginActivity::class.java)
//            startActivity(intent)
            val intent = Intent(activity, AuthenticationActivity::class.java)
            startActivity(intent)
        }
        val switch = binding.theme
        val sharedPreferences = requireActivity(). getSharedPreferences("Mode", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val nightMode = sharedPreferences.getBoolean("night",false)

        if (nightMode ){
            switch.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        binding.theme.setOnCheckedChangeListener { _, isChecked ->

            if (!isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("night", false)
                editor.apply()

            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("night",true)
                editor.apply()
            }
        }
    }

}




