package com.githang.gradledoc.app.process

import com.githang.gradledoc.common.Presenter
import com.githang.gradledoc.common.View

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-09-11
 * @since 2016-09-11
 */
internal class ProcessPresenter<V : View<List<Commit>>>(view: V) : Presenter.Base<List<Commit>, V>(view, ProcessModel())
