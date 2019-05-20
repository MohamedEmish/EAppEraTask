package com.example.eapperataskkotlin

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_logged.*

class LoggedActivity : AppCompatActivity() {

    private lateinit var client: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    companion object{
        var instance : LoggedActivity? = null

        fun getMainInstance():LoggedActivity{
            return instance!!
        }
    }

    fun updateView(value: String){
        this@LoggedActivity.runOnUiThread{
            tv_location.text = value
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged)

        instance = this

        btn_start.setOnClickListener {
            checkPermissionNeeded()
        }

        btn_stop.setOnClickListener {
            stopGettingLocation()
        }
    }

    private fun stopGettingLocation() {
        tv_location.text = ""
    }

    private fun checkPermissionNeeded() {
        Dexter.withActivity(this@LoggedActivity)
            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener{
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(intent)
                    getLocation()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {

                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    Toast.makeText(this@LoggedActivity,"You must have permission",Toast.LENGTH_SHORT).show()
                }
            }).check()

    }

    private fun getLocation() {
        buildLocationRequest()
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED)
            return

        client = LocationServices.getFusedLocationProviderClient(this)
        client.requestLocationUpdates(locationRequest,getPendingIntent())
    }

    private fun getPendingIntent() : PendingIntent?{
        val intent = Intent(this@LoggedActivity,MyLocationService:: class.java)
        intent.action = MyLocationService.ACTION_PROCESS_UPDATED

        return PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun buildLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 3000
        locationRequest.smallestDisplacement = 0.5f
    }
}