package xyz.tradingsign.activity

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.commexpert.CommExpertMng
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.truefriend.corelib.commexpert.intrf.IExpertInitListener
import com.truefriend.corelib.commexpert.intrf.IExpertLoginListener

class SplashActivity : AppCompatActivity(), IExpertInitListener, IExpertLoginListener {
    var context: Context? = null

    val permissionListener: PermissionListener = object: PermissionListener {
        override fun onPermissionGranted() {
            initCommExpert()
        }

        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
        }
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            TedPermission.with(context)
                .setPermissionListener(permissionListener)
                .setDeniedMessage("권한 허용을 거부했습니다.\n[설정]-[권한]에서 허용을 해주세요.")
                .setPermissions(
                    android.Manifest.permission.INTERNET,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.ACCESS_WIFI_STATE,
                    android.Manifest.permission.READ_PHONE_STATE
                )
                .check()
        } else {
            initCommExpert()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this@SplashActivity
        CommExpertMng.InitActivity(this@SplashActivity)
        checkPermission()
    }

    private fun initCommExpert() {
        CommExpertMng.InitCommExpert(this@SplashActivity)
        // Listener init
        CommExpertMng.getInstance().SetInitListener(this@SplashActivity)
        CommExpertMng.getInstance().SetLoginListener(this@SplashActivity)
        // 0 real, 1 dev
        CommExpertMng.getInstance().SetDevSetting("0")
    }

    override fun onDestroy() {
        super.onDestroy()
        CommExpertMng.getInstance().Close()
    }

    override fun onSessionConnecting() {
    }

    override fun onSessionConnected(p0: Boolean, p1: String?) {
    }

    override fun onAppVersionState(p0: Boolean) {
    }

    override fun onMasterDownState(p0: Boolean) {
    }

    override fun onMasterLoadState(p0: Boolean) {
    }

    override fun onInitFinished() {
        // Login
        CommExpertMng.getInstance().StartLogin("kndoll", "lgse3601@#$%", "lgse3601!@#$")
    }

    override fun onRequiredRefresh() {
    }

    override fun onLoginResult(p0: Boolean, p1: String?) {
    }

    override fun onAccListResult(p0: Boolean, p1: String?) {
    }

    override fun onPublicCertResult(p0: Boolean) {
    }

    override fun onLoginFinished() {
        Toast.makeText(baseContext, "계좌리스트 조회 TR 성공 => " + CommExpertMng.getInstance().GetAccountNo(0), Toast.LENGTH_SHORT).show()
    }
}