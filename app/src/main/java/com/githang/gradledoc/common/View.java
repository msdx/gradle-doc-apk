package com.githang.gradledoc.common;

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-09-11
 * @since 2016-09-11
 */
public interface View<P extends Presenter, O> {
    void showProgressDialog();

    void dismissProgressDialog();

    void showToast(String msg);

    void onHandle(O object);
}
