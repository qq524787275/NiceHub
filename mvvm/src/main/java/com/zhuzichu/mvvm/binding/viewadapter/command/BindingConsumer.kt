package com.zhuzichu.mvvm.binding.viewadapter.command

/**
 * Created by wb.zhuzichu18 on 2019/1/17.
 */
interface BindingConsumer<T> {
    fun call(t: T)
}