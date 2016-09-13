package com.githang.gradledoc.common;

import android.content.Context;
import android.util.Log;

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
public interface Presenter<O, V extends View<? extends Presenter, O>> {
    V getView();

    void request(Context context, String url);

    void forceRequest(Context context, String url);

    O handleContent(String content);

    abstract class Base<O, V extends View<? extends Base, O>> implements Presenter<O, V> {
        private V mView;

        public Base(V view) {
            mView = view;
        }

        @Override
        public V getView() {
            return mView;
        }

        public void request(final Context context, final String url) {
            getView().showProgressDialog();
            Observable.create(new Observable.OnSubscribe<O>() {
                @Override
                public void call(Subscriber<? super O> subscriber) {
                    Log.e("request:", Thread.currentThread().getName() + " call");
                    String content = HttpProxy.loadFromCache(url);
                    if (content != null) {
                        subscriber.onNext(handleContent(content));
                        return;
                    }
                    try {
                        content = HttpProxy.loadFromNetwork(context, url);
                        subscriber.onNext(handleContent(content));
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

        public void forceRequest(final Context context, final String url) {
            getView().showProgressDialog();
            Observable.create(new Observable.OnSubscribe<O>() {
                @Override
                public void call(Subscriber<? super O> subscriber) {
                    try {
                        String content = HttpProxy.loadFromNetwork(context, url);
                        subscriber.onNext(handleContent(content));
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
