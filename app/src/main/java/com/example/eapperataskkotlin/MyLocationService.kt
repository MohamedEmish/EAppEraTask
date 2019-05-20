package com.example.eapperataskkotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.android.gms.location.LocationResult

class MyLocationService : BroadcastReceiver() {
    companion object{
        const val ACTION_PROCESS_UPDATED = "com.example.eapperataskkotlin.UPDATE_LOCATION"
    }
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null){
            val action = intent.action
            if (action == ACTION_PROCESS_UPDATED) {
                val result = LocationResult.extractResult(intent)
                if (result != null){
                    val  location = result.lastLocation
                    val locationString = StringBuilder(location.longitude.toString())
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
