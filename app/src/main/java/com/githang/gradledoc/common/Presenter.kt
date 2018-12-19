package com.githang.gradledoc.common

import android.content.Context
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.IOException

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-09-11
 * @since 2016-09-11
 */
interface Presenter<O, V : View<O>> {
    val view: V

    fun request(context: Context, url: String)

    fun request(context: Context, url: String, forceRefresh: Boolean)

    abstract class Base<O, V : View<O>>(override val view: V, private val model: Model<O>) : Presenter<O, V> {

        override fun request(context: Context, url: String) {
            this.request(context, url, false)
        }

        override fun request(context: Context, url: String, forceRefresh: Boolean) {
            view.showProgressDialog()
            Observable.create(Observable.OnSubscribe<O> { subscriber ->
                try {
                    var content: String? = null
                    if (!forceRefresh) {
                        content = model.requestFromCache(url)
                    }
                    if (content == null) {
                        content = model.requestFromNetwork(url)
                    }
                    subscriber.onNext(model.handleContent(content))
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ o ->
                        view.onHandle(o)
                        view.dismissProgressDialog()
                    }, { throwable ->
                        throwable.printStackTrace()
                        view.showToast(throwable.message ?: return@subscribe)
                        view.dismissProgressDialog()
                    })
        }
    }
}
