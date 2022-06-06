package com.example.electronicelectionapp.theRemote.dataClass.addNewElection

import org.json.JSONObject

data class AddNewElection(

    val id: Int,
    val candidates: ArrayList<Candidates>,
    val voters: List<Voters>,
    val election_name: String,
    val start_date: String,
    val end_date: String,
    val election_description: String,
    val public_room: String,
    val private_room: String,
    val cvs_forms_url: String

)
