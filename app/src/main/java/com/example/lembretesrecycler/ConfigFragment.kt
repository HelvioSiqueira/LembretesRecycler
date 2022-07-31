package com.example.lembretesrecycler

import android.content.Intent
import android.os.Bundle
import androidx.preference.*


class ConfigFragment: PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.app_preferences, null)

    }

    override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onPreferenceClick(preference: Preference): Boolean {
        TODO()

    }
}