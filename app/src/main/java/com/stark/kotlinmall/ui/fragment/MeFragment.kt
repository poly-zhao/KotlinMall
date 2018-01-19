package com.stark.kotlinmall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stark.base.extend.loadUrl
import com.stark.base.ui.fragment.BaseFragment
import com.stark.base.utils.AppPrefsUtils
import com.stark.kotlinmall.R
import com.stark.kotlinmall.ui.SettingActivity
import com.stark.provider.common.ProviderConstant
import com.stark.provider.common.isLogin
import com.stark.usercenter.activity.LoginActivity
import com.stark.usercenter.activity.UserInfoActivity
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.support.v4.startActivity


/*
    "我的"界面
 */
class MeFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_me, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    /*
        初始化视图
     */
    private fun initView() {
        mUserIconIv.setOnClickListener(this)
        mUserNameTv.setOnClickListener(this)

        mWaitPayOrderTv.setOnClickListener(this)
        mWaitConfirmOrderTv.setOnClickListener(this)
        mCompleteOrderTv.setOnClickListener(this)
        mAllOrderTv.setOnClickListener(this)
        mAddressTv.setOnClickListener(this)
        mShareTv.setOnClickListener(this)
        mSettingTv.setOnClickListener(this)


    }
    override fun onStop() {
        super.onStop()
        println("MeFragment")
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        //判断是否登录的依据是 SP文件中是否储存有token
        if (isLogin()) {
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
            mUserIconIv.loadUrl(AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON))
        } else {
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)
        }
    }

    override fun onClick(view: View) {

        when (view.id) {
            R.id.mUserIconIv, R.id.mUserNameTv -> {
                if (isLogin()) {
                    startActivity<UserInfoActivity>()
                } else {
                    //使用这个方法需 引入
//                    compile "org.jetbrains.anko:anko-support-v4-commons:$anko_version"
                    startActivity<LoginActivity>()
                }
            }
            R.id.mSettingTv -> startActivity<SettingActivity>()
        }
    }

}
