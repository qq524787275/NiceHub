package com.zhuzichu.nicehub

import android.app.Application
import android.view.View
import com.zhuzichu.mvvm.base.BaseViewModel
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingAction
import com.zhuzichu.mvvm.binding.viewadapter.command.BindingCommand

/**
 * Created by wb.zhuzichu18 on 2019/1/16.
 */
class ContainerViewModel(application: Application) : BaseViewModel(application) {

    val goDetailFragment: BindingCommand<View> = BindingCommand(object : BindingAction {
        override fun call() {
//            Integer.parseInt("goldze")
            startFragment(action = R.id.action_containerFragment_to_detailFragment)
        }
    })
}