package com.example.electronicelectionapp.theRemote.dataClass

import android.net.Uri

data class UploadCv(
    val election_id: String,
    val candidate_username: String,
    val candidate_cv: Uri?
)
