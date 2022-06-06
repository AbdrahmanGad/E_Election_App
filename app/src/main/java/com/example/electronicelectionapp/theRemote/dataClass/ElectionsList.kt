package com.example.electronicelectionapp.theRemote.dataClass

import com.example.electronicelectionapp.theRemote.dataClass.addNewElection.Candidates
import com.example.electronicelectionapp.theRemote.dataClass.addNewElection.Voters

data class ElectionsList(

    val id: Int,

    val candidates: List<Candidates>,

    val voters: List<Voters>,

    val election_name: String,
    val start_date: String,
    val end_date: String,
    val election_description: String,
    val public_room: String,
    val private_room: String

)
