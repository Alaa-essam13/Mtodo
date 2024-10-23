package com.example.mtodo

import android.content.Context

class onboarding(private val context: Context) {
    fun isOnboardingCompleted(): Boolean {
        return context.getSharedPreferences("onboarding", Context.MODE_PRIVATE)
            .getBoolean("completed1", false)
    }

    fun markOnboardingCompleted() {
        context.getSharedPreferences("onboarding", Context.MODE_PRIVATE).edit()
            .putBoolean("completed1", true).apply()
    }
}