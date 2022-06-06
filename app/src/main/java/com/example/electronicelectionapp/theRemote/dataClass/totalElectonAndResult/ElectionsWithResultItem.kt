package com.example.electronicelectionapp.theRemote.dataClass.totalElectonAndResult

data class ElectionsWithResultItem(

    val candidates: List<Candidatell>,
    val election_description: String,
    val election_name: String,
    val end_date: String,
    val id: Int,
    val total_voters: Int,
    val private_room: String,
    val public_room: String,
    val start_date: String,
    val voters: List<Voterll>,
    val cvs_forms_url: String

)