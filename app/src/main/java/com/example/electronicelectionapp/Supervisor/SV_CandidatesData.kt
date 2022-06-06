//package com.example.electronicelectionapp.Supervisor
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.example.electronicelectionapp.R
//import kotlinx.android.synthetic.main.activity_sv_home_page.*
//
//class SV_CandidatesData : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_sv_candidates_data)
//
//        back.setOnClickListener {
//            onBackPressed()
//        }
//
//        setting.setOnClickListener {
//            val intent = Intent(this@SV_CandidatesData, SV_Settings::class.java)
//            startActivity(intent)
//        }
//
//    }
//}