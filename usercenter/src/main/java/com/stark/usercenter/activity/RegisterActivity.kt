package com.stark.usercenter.activity

import android.os.Bundle
import com.stark.base.ui.activity.BaseMvpActivity
import com.stark.usercenter.R
import com.stark.usercenter.injection.DaggerUserComponent
import com.stark.usercenter.mvp.presenter.RegisterPresenter
import com.stark.usercenter.mvp.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mBtnRegister.setOnClickListener {
            mPresenter.register(mEtTel.text.toString(),
                    mEtVerifyCode.text.toString(), mEtPwd.text.toString())
        }
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).build().inject(this)
        mPresenter.mView = this
    }

    override fun onRegisterResult(result: String) = toast(result)

}
