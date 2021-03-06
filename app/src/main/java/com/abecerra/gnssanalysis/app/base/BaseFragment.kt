package com.abecerra.gnssanalysis.app.base

import androidx.fragment.app.Fragment
import com.abecerra.gnssanalysis.app.navigator.Navigator
import com.abecerra.gnssanalysis.app.utils.AppSharedPreferences
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class BaseFragment : Fragment() {

    protected val navigator: Navigator by inject { parametersOf(this.context) }
    protected val mPrefs: AppSharedPreferences by inject()

    protected fun replaceFragment(fragmentContainer: Int, fragment: Fragment) {
        childFragmentManager.beginTransaction().replace(fragmentContainer, fragment).commit()
    }
}
