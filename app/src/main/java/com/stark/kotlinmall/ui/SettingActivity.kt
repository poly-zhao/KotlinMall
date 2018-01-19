package com.stark.kotlinmall.ui

import android.os.Bundle
import com.stark.base.ui.activity.BaseActivity
import com.stark.kotlinmall.R
import com.stark.usercenter.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*
import org.jetbrains.anko.toast

/*
    设置界面
 */
class SettingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        mUserProtocolTv.setOnClickListener {
            toast("用户协议")
        }
        mFeedBackTv.setOnClickListener {
            toast("反馈意见")
        }
        mAboutTv.setOnClickListener {
            toast("关于")
        }

        //退出登录，清空本地用户数据
        mLogoutBtn.setOnClickListener {
            UserPrefsUtils.putUserInfo(null)
            finish()
        }
    }
}
