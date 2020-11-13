package com.example.flutter_app

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.chi.devicelibrary.base.*
import com.chi.devicelibrary.base.DeviceManagerService.PatientServiceBinder
import com.chi.devicelibrary.debug.DeviceStateActivity
import com.chi.devicelibrary.devices.pilldispenser.HiPeeAlarm
import com.chi.devicelibrary.utilities.Preferences
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import kotlin.random.Random


class MainActivity: FlutterActivity(), DeviceManagerListener {

    var mBinder: PatientServiceBinder? = null
    var mService: DeviceManagerService? = null

    protected var mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            mBinder = service as PatientServiceBinder
            mService = mBinder!!.getService()
            mService!!.setListener(this@MainActivity)

            Toast.makeText(this@MainActivity, "DeviceManagerService connected", Toast.LENGTH_LONG).show()
        }

        override fun onServiceDisconnected(name: ComponentName) {
            Log.e("MAIN_ACT", "*********** Disconnected from Service")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Preferences.removeKey(this, DeviceManagerService.PATIENT_DEVICES_PREFS_KEY)
        val i = Intent(this, DeviceManagerService::class.java)
        startService(i)

        GeneratedPluginRegistrant.registerWith(FlutterEngine(this))

        MethodChannel(flutterEngine?.getDartExecutor(), "playground").setMethodCallHandler { call, result ->
            if (call.method == "check") {
//                val greetings: String = helloFromNativeCode()
                onBtnTestBox3()
//                result.success(greetings)
            }
        }

        val i2 = Intent(this, DeviceManagerService::class.java)
        bindService(i2, mConnection, 0)
    }

    private fun helloFromNativeCode(): String {
        return Random(System.nanoTime()).nextInt(1, 10).toString()
    }

    fun onBtnTestBox3() {
        val deviceList = PatientDeviceList()

        deviceList.add("34:81:F4:50:21:FC", "SP10W", "Spirometer", "Spirometer", 1);
        deviceList.add("34:81:F4:4D:8A:FF", "EET-1", "EET-1", "Ear Thermometer", 2);
        deviceList.add("34:81:F4:4B:B9:51", "BC401", "BC401", "Urine Analyzer", 3);
        deviceList.add("34:81:F4:4D:69:C3", "BG01", "BG01", "Blood Glucose", 4);
        deviceList.add("00:0C:BF:2B:6E:F0", "PM10", "PM10", "ECG", 5)

        startTest(deviceList)
    }

    private fun startTest(deviceList: PatientDeviceList) {
        if (mService == null) {
            Toast.makeText(this, "Can not start, please wait till service is connected", Toast.LENGTH_LONG).show()
        } else {
            mService!!.updateDevices(deviceList)
        }
        val j = Intent(this, DeviceStateActivity::class.java)
        startActivityForResult(j, 101)
    }

    override fun DeviceConnected(p0: BaseMedicalDevice?) {
    }

    override fun DeviceDisconnected(p0: BaseMedicalDevice?) {
    }

    override fun DeviceNotConnected(p0: BaseMedicalDevice?) {
    }

    override fun NoResultFound(p0: BaseMedicalDevice?) {
    }

    override fun DeviceResults(p0: BaseMedicalDevice?, p1: MutableList<DeviceResult>?) {
    }

    override fun AddDeviceLog(p0: BaseMedicalDevice?, p1: DeviceLog?) {
    }

    override fun SendMsgToCommandCenter(p0: String?) {
    }

    override fun AlarmsSaved(p0: HiPeeAlarm?, p1: String?, p2: Long) {
    }
}
