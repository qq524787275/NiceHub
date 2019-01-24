package com.zhuzichu.mvvm.global.theme

import androidx.databinding.ObservableField
import com.zhuzichu.mvvm.R
import com.zhuzichu.mvvm.utils.getColor

object ThemeConfig {

    val colorPrimary: ObservableField<Int> = ObservableField()
    val windowBackgroud: ObservableField<Int> = ObservableField()
    val textColorPrimary: ObservableField<Int> = ObservableField()
    val textColorSeconday: ObservableField<Int> = ObservableField()
    val hintColor: ObservableField<Int> = ObservableField()

    fun initColorPrimary(color: Int) {
        colorPrimary.set(color)
    }

    fun initTheme(isDark: Boolean = false) {
        if (isDark) {
            windowBackgroud.set(getColor(R.color.colorBackgroundDark))
            textColorPrimary.set(getColor(R.color.colorPrimaryTextDark))
            textColorSeconday.set(getColor(R.color.colorSecondTextDark))
            hintColor.set(getColor(R.color.colorHintDark))
        } else {
            windowBackgroud.set(getColor(R.color.colorBackground))
            textColorPrimary.set(getColor(R.color.colorPrimaryText))
            textColorSeconday.set(getColor(R.color.colorSecondText))
            hintColor.set(getColor(R.color.colorHint))
        }
    }
}