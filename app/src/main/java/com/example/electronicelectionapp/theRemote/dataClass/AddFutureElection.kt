package com.example.electronicelectionapp.theRemote.dataClass

data class AddFutureElection(

    val electionID: Int,
    val supervisor_id_number: String,
    val election_pre_name: String,
    val election_pre_description: String,
    val cv_form_url : String,
    val start_date: String,
    val end_date: String
)


//{
//    "supervisor_id_number": [
//        "This field is required."
//    ],
//    "election_pre_name": [
//        "This field is required."
//    ],
//    "election_pre_description": [
//        "This field is required."
//    ],
//    "cv_form_url": [
//        "This field is required."
//    ],
//    "start_date": [
//        "This field is required."
//    ],
//    "end_date": [
//        "This field is required."
//    ]
//}