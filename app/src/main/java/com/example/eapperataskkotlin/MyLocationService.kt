package com.example.eapperataskkotlin

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import java.lang.Exception
import java.lang.StringBuilder

class MyLocationService : BroadcastReceiver() {
    companion object{
        val ACTION_PROCESS_UPDATED = "com.example.eapperataskkotlin.UPDATE_LOCATION"
    }
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null){
            val action = intent!!.action
            if (action.equals(ACTION_PROCESS_UPDATED)){
                val result = LocationResult.extractResult(intent)
                if (result != null){
                    val  location = result.lastLocation
                    var  locationString  = StringBuilder(location.longitude .toString())
                        .append("\\n")
                        .append(location.latitude.toString())
                        .toString()
                    try {
                        LoggedActivity.getMainInstance().updateView(locationString)
                    }catch (e:Exception){

                        Toast.makeText(context,locationString,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }


}
