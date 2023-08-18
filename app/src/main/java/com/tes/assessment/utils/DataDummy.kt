package com.tes.assessment.utils

import com.heycoding.assessment.R
import com.tes.assessment.persentation.account.AccountMenu

object DataDummy {
    fun generateAccountMenu(): ArrayList<AccountMenu> {
        val accountMenu = ArrayList<AccountMenu>()
        accountMenu.add(
            AccountMenu(
                menuAccountId = 1,
                menuAccountImage = R.drawable.ic_dark_mode,
                menuAccountName = "Enable Dark Mode",
                menuAccountSwitch = 1
            )
        )
        accountMenu.add(
            AccountMenu(
                menuAccountId = 2,
                menuAccountImage = R.drawable.ic_language,
                menuAccountName = "Change Language",
                menuAccountSwitch = 0
            )
        )
        return accountMenu
    }
}