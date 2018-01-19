package com.stark.usercenter.activity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.jph.takephoto.permission.InvokeListener
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import com.stark.base.ProviderConstant.Companion.KEY_SP_USER_GENDER
import com.stark.base.ProviderConstant.Companion.KEY_SP_USER_ICON
import com.stark.base.ProviderConstant.Companion.KEY_SP_USER_MOBILE
import com.stark.base.ProviderConstant.Companion.KEY_SP_USER_NAME
import com.stark.base.ProviderConstant.Companion.KEY_SP_USER_SIGN
import com.stark.base.common.BaseConstant
import com.stark.base.ui.activity.BaseTakePhotoActivity
import com.stark.base.utils.AppPrefsUtils
import com.stark.base.utils.GlideUtils
import com.stark.usercenter.R
import com.stark.usercenter.UserPrefsUtils
import com.stark.usercenter.data.protocal.UserInfo
import com.stark.usercenter.injection.DaggerUserComponent
import com.stark.usercenter.mvp.presenter.UserInfoPresenter
import com.stark.usercenter.mvp.view.UserInfoView
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import java.io.File


/**
 * 用户信息
 */
class UserInfoActivity : BaseTakePhotoActivity<UserInfoPresenter>(), UserInfoView, TakePhoto.TakeResultListener, InvokeListener {


    override fun onEditUserInfo(result: UserInfo) {
        UserPrefsUtils.putUserInfo(result)
        toast("保存成功")
    }

    private var mUserIcon: String? = null
    private var mUserName: String? = null
    private var mUserMobile: String? = null
    private var mUserGender: String? = null
    private var mUserSign: String? = null

    private lateinit var mTakePhoto: TakePhoto
    private lateinit var tempFile: File
    private val mUploadManager: UploadManager by lazy { UploadManager() }

    private var mLocalFileUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        mTakePhoto = TakePhotoImpl(this@UserInfoActivity, this@UserInfoActivity)
        mTakePhoto.onCreate(savedInstanceState)

        initView()
        initData()
    }

    fun initData() {
        mUserIcon = AppPrefsUtils.getString(KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.getString(KEY_SP_USER_NAME)
        mUserMobile = AppPrefsUtils.getString(KEY_SP_USER_MOBILE)
        mUserGender = AppPrefsUtils.getString(KEY_SP_USER_GENDER)
        mUserSign = AppPrefsUtils.getString(KEY_SP_USER_SIGN)

        mRemoteFileUrl = mUserIcon
        if (mUserIcon != "") {
            GlideUtils.loadUrlImage(this, mUserIcon!!, mUserIconIv)
        }
        mUserNameEt.setText(mUserName)
        mUserMobileTv.text = mUserMobile

        if (mUserGender == "0") {
            mGenderMaleRb.isChecked = true
        } else {
            mGenderFemaleRb.isChecked = true
        }

        mUserSignEt.setText(mUserSign)
    }

    private fun initView() {
        mUserIconView.setOnClickListener {
            AlertView("上传头像", null, "取消", null, arrayOf("拍照", "从相册中选择"), this,
                    AlertView.Style.ActionSheet, object : OnItemClickListener {
                override fun onItemClick(o: Any, position: Int) {
                    mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)

                    when (position) {
                        0 -> {
                            createFile()
                            mTakePhoto.onPickFromCapture(Uri.fromFile(tempFile))
                        }
                        1 -> {
                            mTakePhoto.onPickFromGallery()
                        }
                    }
                }
            }).show()
        }
        mHeaderBar.getRightView().setOnClickListener {
            mPresenter.editUserInfo(mRemoteFileUrl!!,
                    mUserNameEt.text?.toString() ?: "",
                    if (mGenderMaleRb.isChecked) "0" else "1"
                    , mUserSignEt.text?.toString() ?: ""
            )
        }
    }

    private var mRemoteFileUrl: String? = null

    override fun onGetUploadToken(token: String) {
        println("获取到骑牛token: $token")
        mProgressLoading.showLoading()
        mUploadManager.put(mLocalFileUrl, null, token, object : UpCompletionHandler {
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                mRemoteFileUrl = BaseConstant.IMAGE_SERVER_ADDRESS + response?.get("hash")
                Log.i("图片网址:", mRemoteFileUrl)
                GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFileUrl!!, mUserIconIv)
                mProgressLoading.hideLoading()
            }
        }, null)
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).build().inject(this)
        mPresenter.mView = this
    }


    /**
     * TakePhoto.TakeResultListener的方法
     */
    override fun takeSuccess(result: TResult?) {
        mLocalFileUrl = result?.image?.originalPath
        mPresenter.getUploadToken()
    }
}
