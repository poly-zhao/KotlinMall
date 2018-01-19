package com.stark.usercenter.activity

import android.os.Bundle
import com.stark.base.ui.activity.BaseMvpActivity
import com.stark.usercenter.R
import com.stark.usercenter.UserPrefsUtils
import com.stark.usercenter.data.protocal.UserInfo
import com.stark.usercenter.injection.DaggerUserComponent
import com.stark.usercenter.mvp.presenter.LoginPresenter
import com.stark.usercenter.mvp.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView {
    override fun onLogin(userInfo: UserInfo) {
        toast("登录成功")
        UserPrefsUtils.putUserInfo(userInfo)
        startActivity<UserInfoActivity>()
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {
        mLoginBtn.setOnClickListener {
            var mobile = mMobileEt.text.toString()
            var pwd = mPwdEt.text.toString()
            mPresenter.login(mobile, pwd, "")
        }

        mHeaderBar.getRightView().setOnClickListener{
            startActivity<RegisterActivity>()
        }

        mForgetPwdTv.setOnClickListener{
            startActivity<ForgetPwdActivity>()
        }

    }
}
