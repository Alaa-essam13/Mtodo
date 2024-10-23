package com.example.mtodo

import androidx.annotation.DrawableRes

sealed class OnboardingModel (
    @DrawableRes val img: Int,
    val title: String,
    val description: String
){
    data object FirstPage: OnboardingModel(
        R.drawable.img1,
        "Create and Manage Tasks",
        "Create and manage your tasks with ease."
    )
    data object SecondPage: OnboardingModel(
        R.drawable.img2,
        "Let's Start",
        "Create and manage your tasks with ease."
    )
}