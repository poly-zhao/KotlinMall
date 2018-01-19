package com.stark.usercenter.activity

import android.os.Bundle
import com.stark.base.ui.activity.BaseMvpActivity
import com.stark.usercenter.R
import com.stark.usercenter.injection.DaggerUserComponent
import com.stark.usercenter.mvp.presenter.ForgetPwdPresenter
import com.stark.usercenter.mvp.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity

class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)
        initView()
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).build().inject(this)
        mPresenter.mView = this
    }

    override fun onForgetPwd(b: Boolean) {
        if (b) {
            startActivity<ResetPwdActivity>("mobile" to mMobileEt.text.toString())
        }
    }

    fun initView() {
        mNextBtn.setOnClickListener {
            val tel = mMobileEt.text.toString()
            val code = mVerifyCodeEt.text.toString()

            mPresenter.forgetPwd(tel, code)
        }
    }
}
