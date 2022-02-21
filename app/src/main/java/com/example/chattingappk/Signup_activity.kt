package com.example.chattingappk

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Signup_activity : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var name : EditText
    private lateinit var password : EditText
    private lateinit var register : Button
    private lateinit var have_account : TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.hide()
        auth = FirebaseAuth.getInstance()

        email= findViewById(R.id.register_email_id)
        name= findViewById(R.id.register_name_id)
        password= findViewById(R.id.register_pasword_id)
        register= findViewById(R.id.register_id)
        have_account= findViewById(R.id.have_account_id)
    }

    fun have_account(view: View) {
        val intent = Intent(this , LoginActivity::class.java)
        startActivity(intent)
    }

    fun btn_register(view: View) {
        val remail =email.text.toString()
        val rpassword =password.text.toString()

        register(remail, rpassword)
    }

    private fun register(remail:String, rpassword: String )
    {
        auth.createUserWithEmailAndPassword(remail, rpassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@Signup_activity, MainActivity::class.java)
                    startActivity(intent)
                } else {

                    Toast.makeText(this@Signup_activity, "Error try again!" , Toast.LENGTH_LONG).show()


                }
            }

    }
}