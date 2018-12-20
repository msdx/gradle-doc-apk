package com.githang.gradledoc.common.activity

import android.app.ProgressDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.githang.gradledoc.R
import com.githang.gradledoc.common.View

/**
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-12-03
 * Time: 21:59
 */
abstract class BaseRefreshActivity<O> : BaseActivity(), View<O> {
    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(this).apply {
            setCanceledOnTouchOutside(false)
            setCancelable(true)
            setMessage(getString(R.string.loading))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_refresh) {
            onRefresh()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_refresh, menu)
        return true
    }

    protected abstract fun onRefresh()

    override fun showProgressDialog() {
        progressDialog.show()
    }

    override fun dismissProgressDialog() {
        progressDialog.dismiss()
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
