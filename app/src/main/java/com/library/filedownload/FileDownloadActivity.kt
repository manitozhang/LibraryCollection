package com.library.filedownload

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.ToastUtils
import com.common.base.BaseActivity
import com.common.http.helper.filedownload.FileDownloadHelper
import com.common.http.helper.filedownload.OnDownloadListener
import com.common.util.BitmapUtil.fileStoragePath
import com.library.R

/**
 * 文件下载页面
 */
class FileDownloadActivity : BaseActivity() {
    private val ids = 0
    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvSpeed: TextView? = null
    private var fileDownloadHelper: FileDownloadHelper? = null
    override val layout: Int
        get() = R.layout.activity_file_download

    override fun initViewIds() {
        progressBar = findViewById(R.id.progress_bar)
        tvProgress = findViewById(R.id.tv_progress)
        tvSpeed = findViewById(R.id.tv_speed)
    }

    override fun initView() {}
    override fun initData(savedInstanceState: Bundle?) {}
    /**
     * 开始下载
     *
     * @param view
     */
    fun btnStart(view: View?) {
        startDownload()
    }

    /**
     * 开始下载
     */
    private fun startDownload() {
        fileDownloadHelper = FileDownloadHelper()
        fileDownloadHelper!!.downloadFile(fileUrl, fileStoragePath, false, object : OnDownloadListener {
            override fun onPending(id: Int, soFarBytes: Int, totalBytes: Int) {}
            override fun onProgress(id: Int, speed: Int, soFarBytes: Int, totalBytes: Int) {
                tvProgress!!.text = "$soFarBytes / $totalBytes"
                tvSpeed!!.text = "$speed KB/s"
                progressBar!!.max = totalBytes
                progressBar!!.progress = soFarBytes
            }

            override fun onComplete(path: String?) {
                ToastUtils.showShort("下载完成")
                //安装App
                AppUtils.installApp(path)
            }

            override fun onError(e: Throwable?) {
                ToastUtils.showShort("下载异常" + e!!.message)
            }
        })
    }

    /**
     * 暂停下载
     *
     * @param view
     */
    fun btnPause(view: View?) {
        fileDownloadHelper!!.pause()
    }

    companion object {
        private const val fileUrl = "https://library-collection.oss-cn-beijing.aliyuncs.com/file/file.apk"
    }
}