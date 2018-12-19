package com.githang.gradledoc.app.contents

import com.githang.gradledoc.common.Presenter
import com.githang.gradledoc.common.View

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-09-11
 * @since 2016-09-11
 */
internal class ContentPresenter<V : View<List<ChapterUrl>>>(view: V) : Presenter.Base<List<ChapterUrl>, V>(view, ChapterUrlModel())
