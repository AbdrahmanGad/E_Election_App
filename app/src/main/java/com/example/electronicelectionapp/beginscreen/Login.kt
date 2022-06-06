package com.example.electronicelectionapp.beginscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.util.Log
import android.widget.Toast
import com.example.electronicelectionapp.R
import com.example.electronicelectionapp.theRemote.dataClass.LoginUser
import com.example.electronicelectionapp.theRemote.RetrofitInstance
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.auth0.android.jwt.JWT
import com.auth0.android.jwt.Claim
import com.example.electronicelectionapp.Candidate.CA_HomePage
import com.example.electronicelectionapp.Student.ST_HomePage
import com.example.electronicelectionapp.Supervisor.SV_HomePage


class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        val TAG = "MainActivity"


        val mText = "Sign-up"
        val mSpannableString = SpannableString(mText)
        val mUnderline = UnderlineSpan()

        mSpannableString.setSpan(mUnderline, 0, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        signup.text = mSpannableString

        signup.setOnClickListener {
           val intent = Intent(this@Login, SignUp::class.java)
            startActivity(intent)
        }


        val retrofitInstance: RetrofitInstance = RetrofitInstance

        suspend fun loginUserData(loginUser: LoginUser) = withContext(Dispatchers.IO){
            val response = retrofitInstance.api.loginUser(loginUser)
            when {
                response.code() == 200 -> {
                    Log.d(TAG, "Created")
                    Log.d(TAG, response.code().toString())
                    Log.d(TAG, response.body().toString())
//                    Log.d(TAG, response.body())

                    when (response.body()?.your_title) {
                        "Supervisor" -> {
                            val intent = Intent(this@Login, SV_HomePage::class.java)
                            intent.putExtra("SupervisorUsername", response.body()!!.username)
                            startActivity(intent)
                            //Toast.makeText(this@Login, "Supervisor", Toast.LENGTH_SHORT).show()
                        }
                        "Candidate" -> {
                            val intent = Intent(this@Login, CA_HomePage::class.java)
                            intent.putExtra("CandidateUsername", response.body()!!.username)
                            startActivity(intent)
                            //Toast.makeText(this@Login, "Candidate", Toast.LENGTH_SHORT).show()
                        }
                        "Student" -> {
                            val intent = Intent(this@Login, ST_HomePage::class.java)
                            intent.putExtra("StudentUsername", response.body()!!.username)
                            startActivity(intent)
                            //Toast.makeText(this@Login, "Student", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Log.d(TAG, "Wrong title")
                        }
                    }

                }
                response.code() == 401 -> {
                    Log.d(TAG, "UnAuthorized")
                    Log.d(TAG, response.code().toString())
                    Log.d(TAG, response.body().toString())
                    // please check your email and username
                }
//                response.code() == 200 -> {
//                    Log.d(TAG, "ok")
//                }
                else -> {
                    Log.d(TAG, "failed")
                }
            }

        }

        BTN_login.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                loginUserData( LoginUser(
                    " ",
                    usernameETLogin.text.toString(),
                    passwordETLogin.text.toString(),
                    " ",
                    " ",
                )
                )
            }


//            if (usernameET.text.toString() == "super" && passwordET.text.toString().toInt() == 12345){
//                val intent = Intent(this@Login, SV_HomePage::class.java)
//                startActivity(intent)
//            }else if (usernameET.text.toString() =="candidate" && passwordET.text.toString().toInt() == 12345){
//                val intent = Intent(this@Login, CA_HomePage::class.java)
//                startActivity(intent)
//            }else if (usernameET.text.toString() == "student" && passwordET.text.toString().toInt() == 12345){
//                val intent = Intent(this@Login, ST_HomePage::class.java)
//                startActivity(intent)
//            }else{
//                Toast.makeText(this, "wrong credentials !!", Toast.LENGTH_SHORT).show()
//            }
        }





    }
}