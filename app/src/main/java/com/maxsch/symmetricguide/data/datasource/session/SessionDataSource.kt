package com.maxsch.symmetricguide.data.datasource.session

import android.content.SharedPreferences
import com.maxsch.symmetricguide.entity.session.Session

class SessionDataSource(
    private val prefs: SharedPreferences
) {

    private companion object {
        const val USERNAME_KEY = "username_key"
        const val PASSWORD_KEY = "password_key"
    }

    var session: Session?
        get() {
            val username = prefs.getString(USERNAME_KEY, null)
            val password = prefs.getString(PASSWORD_KEY, null)
            return if (username != null && password != null)
                Session(
                    username,
                    password
                )
            else
                null
        }
        set(value) {
            prefs.edit().apply {
                putString(USERNAME_KEY, value?.username)
                putString(PASSWORD_KEY, value?.password)
                apply()
            }
        }
}