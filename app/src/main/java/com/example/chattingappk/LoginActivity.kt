package com.example.chattingappk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var login : Button
    private lateinit var dnt_account : TextView

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth= FirebaseAuth.getInstance()
        supportActionBar?.hide()

        email=findViewById(R.id.login_email_id);
        password=findViewById(R.id.login_pasword_id);
        login=findViewById(R.id.login_id);
        dnt_account=findViewById(R.id.dnt_have_account_id);


    }

    fun dnt_have_account(view: View) {
        val intent = Intent(this, Signup_activity::class.java)
        startActivity(intent)

    }

    fun btnlogin(view: View) {
        val lemail = email.text.toString()
        val lpassword = password.text.toString()

        login(lemail, lpassword)

    }

    private fun login(lemail:String , lpassword: String)
    {
        auth.signInWithEmailAndPassword(lemail, lpassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                   finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(this@LoginActivity, "User Doesn't Exists" , Toast.LENGTH_LONG).show()

                }
            }
    }
}