package com.githang.gradledoc.common

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-09-11
 * @since 2016-09-11
 */
interface View<O> {
    fun showProgressDialog()

    fun dismissProgressDialog()

    fun showToast(msg: String)

    fun onHandle(data: O)
}
