package com.example.electronicelectionapp.theRemote

import com.example.electronicelectionapp.theRemote.dataClass.*
import com.example.electronicelectionapp.theRemote.dataClass.addNewElection.AddNewElection
import com.example.electronicelectionapp.theRemote.dataClass.addNewElection.Candidates
import com.example.electronicelectionapp.theRemote.dataClass.totalElectonAndResult.ElectionsWithResultItem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ServiceApi {

    @POST("/api/account/register/")
    suspend fun addAPIUser(@Body signupUser: SignupUser): Response<SignupUser>

    @POST("/api/account/login/")
    suspend fun loginUser(@Body loginUser: LoginUser): Response<LoginUser>

    @POST("/api/polls/elections/")
    suspend fun addNewElection(@Body addNewElection: AddNewElection): Response<AddNewElection>

    @GET("/api/polls/elections/")
    fun getElectionsList(): Call<List<ElectionsWithResultItem>>

    @POST("/api/polls/futureelection/")
    suspend fun addFutureElection(@Body addFutureElection: AddFutureElection): Response<AddFutureElection>

    @GET("/api/polls/futureelection/")
    fun getFutureElectionsList(): Call<List<AddFutureElection>>

    @Multipart
    @POST("/api/polls/uploadcv/")
    fun uploadCandidateCV(
        @Part("election_id") electionId: RequestBody?,
        @Part("candidate_username") candidateUsername: RequestBody?,
        @Part candidate_cv: MultipartBody.Part //candidate cv
    ): Call<ResponseBody?>?

    @PATCH("/api/polls/elections/{id}/vote/")
    fun vote(@Path("id") id: Int, @Body candidateID: CandidateID): Call<ResponseBody>

}