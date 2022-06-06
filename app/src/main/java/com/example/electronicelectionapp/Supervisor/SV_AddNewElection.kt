package com.example.electronicelectionapp.Supervisor

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.electronicelectionapp.R
import com.example.electronicelectionapp.Supervisor.adapter.SV_AddNewElectionCandidatesListRVAdapter
import com.example.electronicelectionapp.theRemote.RetrofitInstance
import com.example.electronicelectionapp.theRemote.dataClass.addNewElection.AddNewElection
import com.example.electronicelectionapp.theRemote.dataClass.addNewElection.Candidates
import com.example.electronicelectionapp.theRemote.dataClass.addNewElection.Voters
import kotlinx.android.synthetic.main.activity_sv_add_new_election.*
import kotlinx.android.synthetic.main.activity_sv_home_page.*
import kotlinx.android.synthetic.main.candidates_popup_screen.view.*
import kotlinx.android.synthetic.main.row_election_st.view.*
import kotlinx.android.synthetic.main.row_election_sv.*
import kotlinx.android.synthetic.main.voters_popup_screen.*
import kotlinx.android.synthetic.main.voters_popup_screen.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList


class SV_AddNewElection : AppCompatActivity() {

//    private var arrayAdapter: ArrayAdapter<String>? = null
//    val res: Resources = resources
//    var arrayList = ArrayList(Arrays.asList(res.getStringArray(R.array.common))).toString()
//    var arrayAdapter = ArrayAdapter<String?>(
//    applicationContext,
//    R.layout.row_candidate_name,
//    R.id.row_election_name_TV,
//    arrayList
//    )

    lateinit var myListRVAdapterSVAddNewElection : SV_AddNewElectionCandidatesListRVAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {

         //mContacts
        //val mRecyclerView: RecyclerView
        var candidateName = ""
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sv_add_new_election)

        val mRecyclerView = findViewById<RecyclerView>(R.id.candidatesRV)

        linearLayoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = linearLayoutManager

        val arrayList: ArrayList<Candidates> = ArrayList<Candidates>()
        //val arrayListCandidatesAdapter: ArrayList<Candidatell> = ArrayList<Candidatell>()
//        val jsonObj = JSONObject()
//        val list: JSONArray = JSONArray(arrayList)




//        arrayList.add("Test1")
//        arrayList.add("Test2")
//        arrayList.add("Test3")

//        getCandidates()

        //var addCandidate = findViewById<AppCompatButton>(R.id.BTN_Candidates)

        //var arrayList: ArrayList<String>
//        arrayList = ArrayList(Arrays.asList(res.getStringArray(R.array.common))).toString()


//
//        listView = findViewById(R.id.candidatesListview)
//        listView.adapter = arrayAdapter
//        listView.setItemsCanFocus(true)

        var theStartDate = " "
        var theEndDate = " "

        var thePublicChoice = "Public"
        var thePrivateChoice = "Private"

        var mEnglish = "False"
        var mY1 = "False"
        var mY1G1 = "False"
        var mY1G2 = "False"
        var mY1G3 = "False"
        var mY1G4 = "False"
        var mY1G5 = "False"
        var mY1G6 = "False"

        ////////////////////////////////////////////////////////////

        val imageViewBack = findViewById<ImageView>(R.id.SVBackAddNewElection)
        imageViewBack.setOnClickListener {
            onBackPressed()
        }
        val imageViewSettings = findViewById<ImageView>(R.id.SVSettingAddNewElection)
        imageViewSettings.setOnClickListener {
            val intent = Intent(this@SV_AddNewElection, SV_Settings::class.java)
            startActivity(intent)
        }

        ///////////////////////////////////////////////////////////
        val mText = "Generate code"
        val mSpannableString = SpannableString(mText)
        val mUnderline = UnderlineSpan()

        mSpannableString.setSpan(mUnderline, 0, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        TV_generateCode.text = mSpannableString

        //////////////////////////////////////////////////////////////////


//        val builder = AlertDialog.Builder(this)

        BTN_Candidates.setOnClickListener {
            val view = View.inflate(this, R.layout.candidates_popup_screen, null)
            val builder = AlertDialog.Builder(this)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(false)
            view.BTN_ADD_CandidateName.setOnClickListener {
                val x = Candidates(view.TIED_CandidateName.text.toString())
                //val y = Candidatell(view.TIED_CandidateName.text.toString())
                arrayList.add(x)
                //arrayListCandidatesAdapter.add(x)

                myListRVAdapterSVAddNewElection = SV_AddNewElectionCandidatesListRVAdapter(baseContext, arrayList)
                myListRVAdapterSVAddNewElection.notifyDataSetChanged()
                mRecyclerView.setHasFixedSize(true)
                mRecyclerView.adapter = myListRVAdapterSVAddNewElection

             dialog.cancel()
            }
        }





//            val window = PopupWindow(this)
//            val view = layoutInflater.inflate(R.layout.candidates_popup_screen, null)
//            window.contentView = view
//            val addButton = view.findViewById<Button>(R.id.BTN_ADD_CandidateName)
//
//            addButton.setOnClickListener {
//                list.add(view.TIED_CandidateName.text.toString())
//                window.dismiss()
//            }
//            window.showAtLocation(BTN_Candidates, Gravity.CENTER, 0, 0)
        //////////////////////////////////////////////////////////////////

        fun validateElectionName(): Boolean{
            if (electionNameET.text.isNullOrEmpty()){
                electionNameTIL.helperText = "Required"
                return false
            }else{
                electionNameTIL.helperText = null
                return true
            }
        }
        fun validateElectionDescription(): Boolean{
            if (electionDescriptionET.text.isNullOrEmpty()){
                electionDescriptionTIL.helperText = "Required"
                return false
            }else{
                electionDescriptionTIL.helperText = null
                return true
            }
        }
//        fun validateCandidates():Boolean{}
        fun validateVoters():Boolean{
            if (mEnglish == "False" || mY1 == "False" || mY1G1 == "False" ||
                    mY1G2 == "False" || mY1G3 == "False" || mY1G4 == "False" ||
                    mY1G5 == "False" || mY1G6 == "False"){
                        requiredVoters.isVisible = true
                return false
            }else{
                requiredVoters.isVisible = false
                return true
            }
        }
        fun validateRoomType():Boolean{
            if (thePublicChoice == "Public" && thePrivateChoice == "Private"){
                requiredRG.isVisible = true
                return false
            }else{
                requiredRG.isVisible = false
                return true
            }
        }
        fun validateStartDate(): Boolean{
            if (theStartDate == " "){
                requiredSD.isVisible = true
                return false
            }else{
                requiredSD.isVisible = false
                return true
            }
        }
        fun validateEndDate(): Boolean{
            if (theEndDate == " "){
                requiredED.isVisible = true
                return false
            }else{
                requiredED.isVisible = false
                return true
            }
        }
        fun validateCandidateDataCvs(): Boolean{
            if (TIET_candidatesDataSV.text.toString().isNullOrEmpty()){
                requiredCandidateDataCvs.isVisible = true
                return false
            }else{
                requiredCandidateDataCvs.isVisible = false
                return true
            }
        }

        BTN_Voters.setOnClickListener {
            val window = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.voters_popup_screen, null)
            window.contentView = view
            val submitVoterButton = view.findViewById<Button>(R.id.BTN_submit_voters)

            submitVoterButton.setOnClickListener {
                if (view.english.isChecked){mEnglish = "True"}
                if (view.Y1.isChecked){ mY1 = "True" }
                if (view.Y1G1.isChecked){ mY1G1 = "True" }
                if (view.Y1G2.isChecked){ mY1G2 = "True" }
                if (view.Y1G3.isChecked){ mY1G3 = "True" }
                if (view.Y1G4.isChecked){ mY1G4 = "True" }
                if (view.Y1G5.isChecked){ mY1G5 = "True" }
                if (view.Y1G6.isChecked){ mY1G6 = "True" }
                window.dismiss()
            }
            window.showAtLocation(BTN_Voters, Gravity.CENTER, 0, 0)
        }

        //////////////////////////////////////////////////////////////////////////////////

        ll_Generate_Code.visibility = View.GONE

        RB_private.setOnClickListener {
            ll_Generate_Code.visibility = View.VISIBLE
            thePrivateChoice = "True"
            thePublicChoice = "False"
        }

        RB_public.setOnClickListener {
            ll_Generate_Code.visibility = View.GONE
            thePrivateChoice = "False"
            thePublicChoice = "True"
        }

        fun getRandomString(): String {
            val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
            val code = (1..10)
                .map { charset.random() }
                .joinToString("")

            return code
        }

        TV_generateCode.setOnClickListener {
            TV_code.text = getRandomString()
            TV_generateCode.isEnabled = !(TV_generateCode.isEnabled)
//            Toast.makeText(this, TV_code.text, Toast.LENGTH_LONG).show()
        }


        //////////////////////////// START OF  DATE ////////////////////////////

        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_WEEK_IN_MONTH)

        startDate.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, mYear, mMonth, mDay ->
                if (mDay <= 9){
                    if(mMonth <= 9){
                        startDate.text = "0$mDay/0${mMonth+1}/$mYear"
                        theStartDate = "$mYear-0${mMonth+1}-0$mDay"
                    }else{
                        startDate.text = "0$mDay/${mMonth+1}/$mYear"
                        theStartDate = "$mYear-${mMonth+1}-0$mDay"
                    }
                } else if (mDay >= 10){
                    if(mMonth <= 9){
                        startDate.text = "$mDay/0${mMonth+1}/$mYear"
                        theStartDate = "$mYear-0${mMonth+1}-$mDay"
                    }else{
                        startDate.text = "$mDay/${mMonth+1}/$mYear"
                        theStartDate = "$mYear-${mMonth+1}-$mDay"
                    }
                }else{
                    startDate.text = "$mDay/${mMonth+1}/$mYear"
                    theStartDate = "$mYear-${mMonth+1}-$mDay"
                }
            }, year, month, day)
            dpd.show()
        }

        endDate.setOnClickListener{
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                if (mDay <= 9){
                    if(mMonth <= 9){
                        endDate.text = "0$mDay/0${mMonth+1}/$mYear"
                        theEndDate = "$mYear-0${mMonth+1}-0$mDay"
                    }else{
                        endDate.text = "0$mDay/${mMonth+1}/$mYear"
                        theEndDate = "$mYear-${mMonth+1}-0$mDay"
                    }
                } else if (mDay >= 10){
                    if(mMonth <= 9){
                        endDate.text = "$mDay/0${mMonth+1}/$mYear"
                        theEndDate = "$mYear-0${mMonth+1}-$mDay"
                    }else{
                        endDate.text = "$mDay/${mMonth+1}/$mYear"
                        theEndDate = "$mYear-${mMonth+1}-$mDay"
                    }
                }else{
                    endDate.text = "$mDay/${mMonth+1}/$mYear"
                    theEndDate = "$mYear-${mMonth+1}-$mDay"
                }
            }, year, month, day)
            dpd.show()
        }
        /////////////////////////////// END OF DATE ///////////////////////////////

        val retrofitInstance = RetrofitInstance

        suspend fun post(addNewElection: AddNewElection) = withContext(Dispatchers.IO){
            val response = retrofitInstance.api.addNewElection(addNewElection)

            if(response.code() == 201){
                Log.d("MainActivity", "Created")
                Log.d("MainActivity", response.body().toString())
            }else if(response.code() == 200){
                Log.d("MainActivity", response.code().toString())
                Log.d("MainActivity", response.body().toString())
                Log.d("MainActivity", response.message())
            }else{
                Log.d("MainActivity", response.code().toString())
                Log.d("MainActivity", response.body().toString())
                Log.d("MainActivity", response.message())
            }
        }

//        fun confirmTrueValidation(): Boolean{
//            if (
//                validateElectionName() &&
//                validateElectionDescription() &&
//                validateVoters() &&
//                validateRoomType() &&
//                validateStartDate() &&
//                validateEndDate() &&
//                validateCandidateDataCvs()
//            ){
//                return true
//            }
//            return false
//        }

        BTN_preview.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
//                if (confirmTrueValidation()){
                    post( AddNewElection(
                        0,
                        ArrayList(arrayList),
                        listOf(Voters(mEnglish,mY1,mY1G1,mY1G2,mY1G3,mY1G4,mY1G5,mY1G6)),
                        electionNameET.text.toString(),
                        theStartDate,
                        theEndDate,
                        electionDescriptionET.text.toString(),
                        thePublicChoice,
                        thePrivateChoice,
                        TIET_candidatesDataSV.text.toString()))
//                }
            }
        }

        //////////////////////////////////////////////////////////////////

    }
//
//    private fun getCandidates() {
////        val mRecyclerView = findViewById<RecyclerView>(R.id.candidatesRV)
//
//    }


}






//        BTN_preview.setOnClickListener {
//            Log.d("MainActivity", theStartDate)
//            Log.d("MainActivity", theEndDate)
//
////            val intent = Intent(this@SV_AddNewElection, SV_Preview::class.java)
////            startActivity(intent)
//        }

        //////////////////////////////////////////////////////////////////

//        val electionList: ArrayList<Election> = ArrayList()
//
//        val electionRecyclerView: ElectionRecyclerView by lazy {
//            ElectionRecyclerView(electionList)
//        }
//
//        RV_sv_elections_homepage.adapter = electionRecyclerView
//
//        BTN_preview.setOnClickListener {
//            electionList.add(
//                Election(
//                    election_name.text.toString(),
//                    etDescription.text.toString(),
//                    //radioGroup.checkedRadioButtonId.toInt(),
//                    //TV_code.toString(),
//                    //startDate.text.toString().toInt(),
//                    endDate.text.toString().toInt()
//                )
//            )
//            electionRecyclerView.setList(electionList)
//        }


//{
//        "url": "http://127.0.0.1:8000/api/users/7/",
//        "email": "mazen@gmail.com",
//        "Fullname": "mazem mohamed ibrahim",
//        "profile": {
//            "Your_title": "1",
//            "ID_Number": 987654321,
//            "Phone_Number": 32659988,
//            "National_ID": 1265498777326
//        }