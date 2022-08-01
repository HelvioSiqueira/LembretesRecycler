package com.example.lembretesrecycler.fragments

import android.os.Bundle
import androidx.preference.*
import com.example.lembretesrecycler.R

class ConfigFragment: PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {

    private lateinit var listPrefTamanho: ListPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.app_preferences, null)

        listPrefTamanho = findPreference<ListPreference>(getString(R.string.pref_tamanho_letras)) as ListPreference

        fillSummary(listPrefTamanho)
    }

    override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {

        val stringValue = newValue.toString()

        if (preference == listPrefTamanho){
            val index = listPrefTamanho.findIndexOfValue(stringValue)

            if (index >= 0){
                listPrefTamanho.summary = listPrefTamanho.entries[index]
            }
        }

        return true
    }


    private fun fillSummary(preference: Preference){
        preference.onPreferenceChangeListener = this

        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val value = pref.getString(preference.key, "")
        onPreferenceChange(preference, value)
    }
}