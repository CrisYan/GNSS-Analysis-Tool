package com.abecerra.gnssanalysis.core.base

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import com.abecerra.gnssanalysis.core.computation.GnssService

/**
 * This BaseGnssActivity creates the GnssService and implements the connection with it.
 */
abstract class BaseGnssActivity : BaseActivity() {

    protected var mService: GnssService? = null

    private val mConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as? GnssService.PvtServiceBinder
            binder?.service?.let {
                mService = it
                this@BaseGnssActivity.onGnssServiceConnected(it)
            }
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        startGnssService()
    }

    private fun startGnssService() {
        val intent = Intent(this, GnssService::class.java)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        mService?.let {
            unbindService(mConnection)
        }
    }

    /**
     * This onGnssServiceConnected function is used to bind listeners to the service once Binder is connected.
     * @param service GnssService not null instance used to bind the listeners.
     */
    abstract fun onGnssServiceConnected(service: GnssService)

}