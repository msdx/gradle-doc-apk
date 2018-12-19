package com.githang.gradledoc.common;

import android.content.Context;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-09-11
 * @since 2016-09-11
 */
public interface Presenter<O, V extends View<O>> {
    V getView();

    void request(Context context, String url);

    void request(Context context, String url, boolean forceRefresh);

    abstract class Base<O, V extends View<O>> implements Presenter<O, V> {
        private final V mView;
        private final Model<O> mModel;

        public Base(V view, Model<O> model) {
            mView = view;
            mModel = model;
        }

        @Override
        public V getView() {
            return mView;
        }

        @Override
        public void request(final Context context, final String url) {
            this.request(context, url, false);
        }

        @Override
        public void request(final Context context, final String url, final boolean forceRefresh) {
            getView().showProgressDialog();
            Observable.create(new Observable.OnSubscribe<O>() {
                @Override
                public void call(Subscriber<? super O> subscriber) {
                    try {
                        String content = null;
                        if (!forceRefresh) {
                            content = mModel.requestFromCache(url);
                        }
                        if (content == null) {
                            content = mModel.requestFromNetwork(url);
                        }
                        subscriber.onNext(mModel.handleContent(content));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<O>() {
                        @Override
                        public void call(O o) {
                            getView().onHandle(o);
                            getView().dismissProgressDialog();
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            throwable.printStackTrace();
                            getView().showToast(throwable.getMessage());
                        }
                    });
        }
    }
}
