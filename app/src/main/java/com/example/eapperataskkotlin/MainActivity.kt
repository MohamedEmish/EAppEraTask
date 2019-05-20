package com.example.eapperataskkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val name: String? = SavedPreferences.getUserName(this)
        val pass: String? = SavedPreferences.getUserPass(this)

        btn_login.setOnClickListener {
            if (name == null && pass == null){
                val newName:String = et_name.text.toString()
                val newPass:String = et_password.text.toString()
                SavedPreferences.setUserName(this,newName)
                SavedPreferences.setUserPassword(this,newPass)

                startActivity(Intent(this, LoggedActivity::class.java))

            }else{
                if (et_name.text.toString() == name){
                    if (et_password.text.toString() == pass){
                        startActivity(Intent(this, LoggedActivity::class.java))
                    }else{
                        Toast.makeText(this,"Wrong Password",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this,"UnKnown User",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
