package com.example.electronicelectionapp.Supervisor.model

import java.io.Serializable
import java.util.*

data class Election (
                var electionName: String,
                var electionDescription: String,
                //var roomType: Int,
                //var roomCode: String,
                //var startDate: Int,
                var endDate: Int

                ):Serializable {


}