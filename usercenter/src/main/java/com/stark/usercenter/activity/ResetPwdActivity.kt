package com.stark.usercenter.activity

import android.os.Bundle
import com.stark.base.ui.activity.BaseMvpActivity
import com.stark.usercenter.R
import com.stark.usercenter.injection.DaggerUserComponent
import com.stark.usercenter.mvp.presenter.ResetPwdPresenter
import com.stark.usercenter.mvp.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast

class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView {
    override fun onResetPwd(b: Boolean) {
        if (b) {
            startActivity(intentFor<LoginActivity>().singleTop().clearTask())
            toast("密码修改成功")
        }
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)

        mConfirmBtn.setOnClickListener {
            mPresenter.resetPwd(intent.getStringExtra("mobile"), mPwdEt.text.toString())
        }
    }
}
