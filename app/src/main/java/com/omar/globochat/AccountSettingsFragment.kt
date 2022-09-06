package com.omar.globochat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.preference.MultiSelectListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat


class AccountSettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.account_settings, rootKey)

        val publicInfoPref = context?.let { MultiSelectListPreference(it) }
        if (publicInfoPref != null) {
            publicInfoPref.entries = resources.getStringArray(R.array.entries_public_info)
            publicInfoPref.entryValues = resources.getStringArray(R.array.values_public_info)
            publicInfoPref.key = getString(R.string.key_public_info)
            publicInfoPref.title = getString(R.string.title_public_info)
            publicInfoPref.isIconSpaceReserved = false
        }

        val logOutPref = context?.let { Preference(it) }
        if(logOutPref != null) {
            logOutPref.key = getString(R.string.key_log_out)
            logOutPref.title = getString(R.string.title_log_out)
            logOutPref.isIconSpaceReserved = false
        }

        val deleteAccountPref = context?.let { Preference(it) }
        if( deleteAccountPref != null ){
            deleteAccountPref.key = getString(R.string.key_delete_account)
            deleteAccountPref.summary = getString(R.string.summary_delete_account)
            deleteAccountPref.title = getString(R.string.title_delete_account)
            deleteAccountPref.icon = ResourcesCompat.getDrawable(resources, android.R.drawable.ic_menu_delete, null)
        }

        val privacyCatogry = context?.let { PreferenceCategory(it) }
        if( privacyCatogry != null ){
            privacyCatogry.title = getString(R.string.title_privacy)
            privacyCatogry.isIconSpaceReserved = false
        }
        val securityCatogry = context?.let { PreferenceCategory(it) }
        if( securityCatogry != null ){
            securityCatogry.title = getString(R.string.title_security)
            securityCatogry.isIconSpaceReserved = false
        }

        val prefScreen = context?.let { preferenceManager.createPreferenceScreen(it) }
        if( prefScreen != null ){
            prefScreen.addPreference(privacyCatogry!!)
            prefScreen.addPreference(publicInfoPref!!)
            prefScreen.addPreference(securityCatogry!!)
            prefScreen.addPreference(logOutPref!!)
            prefScreen.addPreference(deleteAccountPref!!)
        }

         preferenceScreen = prefScreen
    }
}