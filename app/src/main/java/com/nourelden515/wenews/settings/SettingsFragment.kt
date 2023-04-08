package com.nourelden515.wenews.settings

import android.app.UiModeManager.MODE_NIGHT_NO
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nourelden515.wenews.BuildConfig
import com.nourelden515.wenews.R
import com.nourelden515.wenews.authentication.LoginActivity
import com.nourelden515.wenews.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding

    private lateinit var viewModel: SettingsViewModel
    lateinit var versionTV: TextView
 private lateinit var selectedMode: String
    private var selectedModeIndex: Int = 0
    private val mode = arrayOf("Light Mode", "Dark Mode")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        versionTV=binding.Version
        val version = "Version " + BuildConfig.VERSION_NAME +"."+ BuildConfig.VERSION_CODE.toString()
        versionTV.text = version
        val showDialog:TextView = binding.theme

      binding.logOut.setOnClickListener {
//          requireActivity().run{
//              startActivity(Intent(this, LoginActivity::class.java))
//              finish()
//          }
          val intent = Intent (view.context, LoginActivity::class.java)
          startActivity(intent)
      }
     binding.theme.setOnClickListener {
         showRadioConfirmationDialog()
     }
    }
    private fun darkMode(){
        // check the mode
        val isDarkMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO
       // set the other
        AppCompatDelegate.setDefaultNightMode(if (isDarkMode) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_NO)
     //   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        activity?.recreate()
    }
   private fun lightMode(){
       val isLightMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
       // set the other
       AppCompatDelegate.setDefaultNightMode(if (isLightMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_YES)
       //   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
       activity?.recreate()
   }

    private fun showRadioConfirmationDialog() {
        selectedMode = mode[selectedModeIndex]
        val builder = AlertDialog.Builder(requireContext())
       builder.setTitle("Choose Mode")
        builder .setSingleChoiceItems(mode, selectedModeIndex) { dialog_, which ->
                    selectedModeIndex = which
                    selectedMode = mode[which]
                    when (which) {
                        0 -> {
                            darkMode()
                        }
                        1->
                        {
                            lightMode()
                        }
                    }

                }
        }
    }


