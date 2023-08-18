package com.tes.assessment.persentation.account

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.tes.assessment.utils.DataDummy
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor() : ViewModel() {
    fun getAccountMenu(): List<AccountMenu> = DataDummy.generateAccountMenu()
}