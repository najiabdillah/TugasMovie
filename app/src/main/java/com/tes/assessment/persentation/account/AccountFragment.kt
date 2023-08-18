package com.tes.assessment.persentation.account

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import com.heycoding.assessment.databinding.FragmentAccountBinding

@AndroidEntryPoint
class AccountFragment : Fragment(), AccountFragmentCallback {

    private var _fragmentAccountBinding: FragmentAccountBinding? = null
    private val fragmentAccountBinding get() = _fragmentAccountBinding
    private val accountViewModel: AccountViewModel by viewModels<AccountViewModel>()
    private lateinit var accountAdapter: AccountAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentAccountBinding = FragmentAccountBinding.inflate(inflater, container, false)
        return fragmentAccountBinding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accountAdapter = AccountAdapter(this)

        setupObserve()
        setupUI()
    }

    private fun setupObserve() {
        accountViewModel.apply {
            val onAccountMenu = getAccountMenu()
            accountAdapter.setAccountMenuData(onAccountMenu)
        }
    }

    private fun setupUI() {
        fragmentAccountBinding?.apply {
            rvAccountMenu.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                adapter = accountAdapter
                clipToPadding = false
                clipChildren = false
            }
        }
    }


    override fun onChangeLanguage() {
        startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentAccountBinding = null
    }
}