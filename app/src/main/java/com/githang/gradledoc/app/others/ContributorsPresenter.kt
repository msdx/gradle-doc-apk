package com.githang.gradledoc.app.others

import com.githang.gradledoc.common.Presenter
import com.githang.gradledoc.common.View

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-09-11
 * @since 2016-09-11
 */
internal class ContributorsPresenter<V : View<List<Contributor>>>(view: V) : Presenter.Base<List<Contributor>, V>(view, ContributorModel())
