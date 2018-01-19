package com.stark.base.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.model.InvokeParam
import com.jph.takephoto.model.TContextWrap
import com.jph.takephoto.model.TResult
import com.jph.takephoto.permission.InvokeListener
import com.jph.takephoto.permission.PermissionManager
import com.qiniu.android.storage.UploadManager
import com.stark.base.common.BaseApplication
import com.stark.base.injection.component.ActivityComponent
import com.stark.base.injection.component.DaggerActivityComponent
import com.stark.base.injection.module.ActivityModule
import com.stark.base.injection.module.LifecycleProviderModule
import com.stark.base.mvp.presenter.BasePresenter
import com.stark.base.mvp.view.BaseView
import com.stark.base.wighets.ProgressLoading
import org.jetbrains.anko.toast
import java.io.File
import javax.inject.Inject

abstract class BaseTakePhotoActivity<T : BasePresenter<*>> : BaseActivity(), BaseView, TakePhoto.TakeResultListener, InvokeListener {


    private lateinit var mTakePhoto: TakePhoto

    @Inject
    lateinit var mPresenter: T

    lateinit var mProgressLoading: ProgressLoading

    lateinit var activityComponent: ActivityComponent

    private lateinit var tempFile: File
    private val mUploadManager: UploadManager by lazy { UploadManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInject()
        injectComponent()

        mTakePhoto = TakePhotoImpl(this@BaseTakePhotoActivity, this@BaseTakePhotoActivity)
        mTakePhoto.onCreate(savedInstanceState)

        mProgressLoading = ProgressLoading(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        mTakePhoto.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    /*
    Dagger注册
    */
    protected abstract fun injectComponent()

    /*
       初始Activity Component
    */
    private fun initActivityInject() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

    /**
     * 拍照时产生的临时照片
     */
    fun createFile() {
        val tempFileName = "${System.currentTimeMillis()}.png"
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            tempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }
        tempFile = File(filesDir, tempFileName)
    }

    /**
     * 拍照结果监听接口的回调方法
     */
    override fun takeSuccess(result: TResult?) {
        Log.i("takePhoto", "takeSuccess-" + result?.image?.originalPath)
        Log.i("takePhoto", "takeSuccess-" + result?.image?.compressPath)
    }

    override fun takeCancel() {
        Log.i("takePhoto", "takeCancel")
    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.i("takePhoto", "takeFail" + result?.image?.compressPath)
    }

    private lateinit var invokeParam: InvokeParam

    /**
     * InvokeListener的抽象方法
     */
    override fun invoke(invokeParam: InvokeParam): PermissionManager.TPermissionType {
        val type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.method)
        if (PermissionManager.TPermissionType.WAIT == type) {
            this.invokeParam = invokeParam
        }
        return type
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //以下代码为处理Android6.0、7.0动态权限所需
        val type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this)
    }


    override fun showLoading() = mProgressLoading.showLoading()

    override fun hideLoading() = mProgressLoading.hideLoading()

    override fun onError(msg: String) = toast(msg)

}
