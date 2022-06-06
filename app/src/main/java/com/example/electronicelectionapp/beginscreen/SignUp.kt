package com.example.electronicelectionapp.beginscreen

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.util.Log
import android.widget.Button
import android.widget.PopupWindow
import androidx.core.view.isVisible
import com.example.electronicelectionapp.Candidate.CA_HomePage
import com.example.electronicelectionapp.R
import com.example.electronicelectionapp.Student.ST_HomePage
import com.example.electronicelectionapp.Supervisor.SV_HomePage
import com.example.electronicelectionapp.theRemote.*
import com.example.electronicelectionapp.theRemote.dataClass.SignupUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.your_title_signup.*
import kotlinx.android.synthetic.main.your_title_signup.view.*
import kotlinx.coroutines.*

class SignUp : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()
//        lateinit var viewmodel: SignupViewModel
        val TAG = "MainActivity"
        var title = "title"

        val mText = "Login"
        val mSpannableString = SpannableString(mText)
        val mUnderline = UnderlineSpan()

        mSpannableString.setSpan(mUnderline, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        login.text = mSpannableString

        login.setOnClickListener {
            val intent = Intent(this@SignUp, Login::class.java)
            startActivity(intent)
        }

        ///////////////////////////// START OF VALIDATION /////////////////////////////
        fun validateFullName():Boolean{
            if (fullNameETSignup.text.isNullOrEmpty()){
                fullNameTILSignup.helperText = "Required"
                return false
            }else{
                fullNameTILSignup.helperText = null
                return true
            }
        }
        fun validateUsername():Boolean{
            if (usernameETSignup.text.isNullOrEmpty()){
                usernameTILSignup.helperText = "Required"
                return false
            }else{
                return true
            }
        }
        fun validateEmail():Boolean{

             if (emailETSignup.text.isNullOrEmpty()){
                 emailTILSignup.helperText = "Required"
                 return false
             }else{
                 emailTILSignup.helperText = null
                 return true
             }
         }
        fun validatePassword():Boolean{
             if (passwordETSignup.text.isNullOrEmpty()){
                 passwordTILSignup.helperText = "Required"
                 return false
             }else if (passwordETSignup.text != confirmPasswordETSignup.text){
                 passwordTILSignup.helperText = "Not Matched"
                 confirmPasswordTILSignup.helperText = "Not Matched"
                 return false
            }else{
                 passwordTILSignup.helperText = null
                 confirmPasswordTILSignup.helperText = null
                 return true
             }
         }
        fun validateTitle():Boolean{
            if (yourTitleBTN.text == "Your Title"){
                required.isVisible = true
                return false
            }else {
                required.isVisible = false
                return true
            }
        }
        fun validateIDNumber(): Boolean{
            if (idNumberETSignup.text.isNullOrEmpty()){
                idNumberTILSignup.helperText = "Required"
                return false
            }else if (idNumberETSignup.text!!.length < 7 ){
                idNumberTILSignup.helperText = "Not Valid ID Number"
                return false
            }else {
                 idNumberTILSignup.helperText = null
                 return true
            }
        }
        fun validatePhoneNumber(): Boolean{
            if (phoneNumberETSignup.text.isNullOrEmpty()){
                phoneNumberTILSignup.helperText = "Required"
                return false
            } else if (phoneNumberETSignup.text!!.length < 11){
                phoneNumberTILSignup.helperText = "Not Valid Phone Number"
                return false
            }else{
                phoneNumberTILSignup.helperText = null
                return true
            }
        }
        fun validateNationalID(): Boolean{
            if (nationalIdETSignup.text.isNullOrEmpty()){
                nationalIdTILSignup.helperText = "Required"
                return false
            } else if (nationalIdETSignup.text!!.length < 14){
                nationalIdTILSignup.helperText = "Not Valid National ID"
                return false
            }else{
                nationalIdTILSignup.helperText = null
                return true
            }
        }
        /////////////////////////////// END OF VALIDATION /////////////////////////////

        yourTitleBTN.setOnClickListener {
            val window = PopupWindow(this@SignUp)
            val view = layoutInflater.inflate(R.layout.your_title_signup, null)
            window.contentView = view
            val doneBTN = view.findViewById<Button>(R.id.doneBTN)


            doneBTN.setOnClickListener {
                if(view.yourTitleSupervisor.isChecked){
                    window.dismiss()
                    yourTitleBTN.text = "Supervisor"
                    yourTitleBTN.setTextColor(Color.BLACK)
                    title = "Supervisor"
                }else if (view.yourTitleCandidate.isChecked){
                    window.dismiss()
                    yourTitleBTN.text = "Candidate"
                    yourTitleBTN.setTextColor(Color.BLACK)
                    title = "Candidate"
                } else if (view.yourTitleStudent.isChecked){
                    window.dismiss()
                    yourTitleBTN.text = "Student"
                    yourTitleBTN.setTextColor(Color.BLACK)
                    title = "Student"
                }
            }

            window.showAsDropDown(yourTitleBTN)
        }

//        val repository = Repository()
//        val viewModelFactory = SignupViewModelFactory(repository)
//        viewmodel = ViewModelProvider(this, viewModelFactory).get(SignupViewModel::class.java)

        val retrofitInstance = RetrofitInstance

        suspend fun post(signupUser: SignupUser) = withContext(Dispatchers.IO){
                    Log.d(TAG, "......")
                    val response = retrofitInstance.api.addAPIUser(signupUser)

            when {
                response.code() == 201 -> {
                    Log.d(TAG, "Created")
                    Log.d(TAG, response.code().toString())
                    Log.d(TAG, response.body().toString())
//                    Log.d(TAG, response.body())

                    when (response.body()?.your_title) {
                        "Supervisor" -> {
                            val intent = Intent(this@SignUp, SV_HomePage::class.java)
                            startActivity(intent)
                        }
                        "Candidate" -> {
                            val intent = Intent(this@SignUp, CA_HomePage::class.java)
                            startActivity(intent)
                        }
                        "Student" -> {
                            val intent = Intent(this@SignUp, ST_HomePage::class.java)
                            startActivity(intent)
                        }
                        else -> {
                            val intent = Intent(this@SignUp, Login::class.java)
                            startActivity(intent)
                        }
                    }

                }
                response.code() == 406 -> {
                    Log.d(TAG, "Not accepted")
                    Log.d(TAG, response.code().toString())
                    Log.d(TAG, response.body().toString())
                    // please check your email and username
                }
                response.code() == 200 -> {
                    Log.d(TAG, "ok")
                }
                else -> {
                    Log.d(TAG, "failed")
                }
            }

        }

        fun confirmTrueValidation(): Boolean {
            if (
                !validateFullName() &&
                !validateUsername() &&
                !validateEmail() &&
                !validatePassword() &&
                !validateTitle() &&
                !validateIDNumber() &&
                !validatePhoneNumber() &&
                !validateNationalID()
                ){
                return false
            }
            return true
        }

        BTN_signup.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                if (confirmTrueValidation()){
                    post( SignupUser(
                                "response",
                                emailETSignup.text.toString(),
                                usernameETSignup.text.toString(),
                                passwordETSignup.text.toString(),
                                confirmPasswordETSignup.text.toString(),
                                fullNameETSignup.text.toString(),
                                title,
                                idNumberETSignup.text.toString().toLong(),
                                phoneNumberETSignup.text.toString().toLong(),
                                nationalIdETSignup.text.toString().toLong(),
                                "token")
                    )
                }
            }
        }
    }
}

//"^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
//"[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
//            val checkEmail =
//                    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
//                    "\\@" +
//                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
//                    "(" +
//                    "\\." +
//                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
//                    ")+"

//else if (!emailETSignup.text!!.matches(checkEmail.toRegex())){
//                 emailTILSignup.helperText = "InValid Email Address"
//                 return false
//             }





//            val checkPassword =
//                Pattern.compile("^" +
//                            "(?=.*[0-9])" +         //a digit must occur at least once
//                            "(?=.*[a-z])" +         //a lower case letter must occur at least once
//                            "(?=.*[A-Z])" +         //an upper case letter must occur at least once
//                            //"(?=.*[@#$%^&+=])" +    //a special character must occur at least once you can replace with your special characters
//                            //"(?=\\S+$)" +           //no whitespace allowed in the entire string
//                            ".{4,}" +               //at least 4 char
//                            "$" )

//else if (!checkPassword.matcher(passwordETSignup.text).matches()){
//                 passwordTILSignup.helperText = "password should contain (A-Z or a-z or 0-9)"
//                 return false
//             }
