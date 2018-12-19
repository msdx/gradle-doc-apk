package com.githang.gradledoc.app.chapter

import com.githang.gradledoc.common.Presenter
import com.githang.gradledoc.common.View

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-09-11
 * @since 2016-09-11
 */
internal class ChapterPresenter<V : View<String>>(view: V) : Presenter.Base<String, V>(view, ChapterModel())
