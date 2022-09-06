package com.omar.globochat

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.*


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        val dataStore = DataStore()
//        preferenceManager.preferenceDataStore = dataStore

       val accSettingsPref =  findPreference<Preference>(getString(R.string.key_account_settings))

        accSettingsPref?.setOnPreferenceClickListener {

            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_frag) as NavHostFragment
            val navController = navHostFragment.navController
            val action = SettingsFragmentDirections.actionSettingsToAccSettings()
            navController.navigate(action)
            true
        }

        val statusPref = findPreference<EditTextPreference>("key_status")
        statusPref?.setOnPreferenceChangeListener { preference, newValue ->

            val newStatus = newValue as String
            if(newStatus.isEmpty()){
                Toast.makeText(context, "You didn't enter anything", Toast.LENGTH_SHORT).show()
                false
            }
            else {
                true
            }
        }

        val notificationPref = findPreference<SwitchPreferenceCompat>("key_new_msg_notif")
        notificationPref?.summaryProvider =
            Preference.SummaryProvider<SwitchPreferenceCompat> { preference ->
            if(preference.isChecked){
                    "Notifications are enabled"
                } else{
                    "Notifications are disabled"
                }
            }
        notificationPref?.preferenceDataStore = dataStore

        val isNotifEnabled = dataStore.getBoolean("key_new_msg_notif", false)
    }


    class DataStore : PreferenceDataStore(){

        override fun getBoolean(key: String?, defValue: Boolean): Boolean {

            if (key == "key_new_msg_notif"){
                Log.i("DataStore", "putBoolean: $key")
            }
            return defValue
        }

        override fun putBoolean(key: String?, value: Boolean) {

            if (key == "key_new_msg_notif"){
                Log.i("DataStore", "putBoolean: $value")
            }
        }
    }
}