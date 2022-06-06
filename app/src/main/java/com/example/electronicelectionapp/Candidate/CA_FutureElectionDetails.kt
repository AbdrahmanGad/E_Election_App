package com.example.electronicelectionapp.Candidate

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.FileUtils
import android.provider.OpenableColumns
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.electronicelectionapp.R
import com.example.electronicelectionapp.theRemote.RetrofitInstance
import kotlinx.android.synthetic.main.activity_ca_future_election.*
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import java.io.File
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Callback
import retrofit2.Response
import okhttp3.ResponseBody
import java.net.URLConnection
import okhttp3.RequestBody
import android.os.Environment
import android.provider.DocumentsContract
import androidx.core.net.toUri
import java.io.UnsupportedEncodingException
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import java.lang.RuntimeException
import java.net.URI
import java.util.*


class CA_FutureElectionDetails : AppCompatActivity() {

    private var electionID: Int = 0
    private var electionName: String = " "
    private lateinit var pdfUri: Uri
    var pdfName: String? = null


    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ca_future_election)

//        UploadUtility(this).uploadFile(filePath.toString()) // Either Uri, File or String file path

//        val mimeType: String? = returnIntent.data?.let { returnUri ->
//            contentResolver.getType(returnUri)
//        }
//        returnIntent.data?.let { returnUri ->
//            contentResolver.query(returnUri, null, null, null, null)
//        }?.use { cursor ->
//            /*
//             * Get the column indexes of the data in the Cursor,
//             * move to the first row in the Cursor, get the data,
//             * and display it.
//             */
//            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
//            val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
//            cursor.moveToFirst()
//            findViewById<TextView>(R.id.filename_text).text = cursor.getString(nameIndex)
//            findViewById<TextView>(R.id.filesize_text).text = cursor.getLong(sizeIndex).toString()
//            ...
//        }

        CA_back.setOnClickListener {
            onBackPressed()
        }

        CA_setting.setOnClickListener {
            val intent = Intent(this@CA_FutureElectionDetails, CA_Settings::class.java)
            startActivity(intent)
        }

        val bundel: Bundle? = intent.extras

        val eID = bundel!!.getInt("electionID")
        val eName = bundel.getString("electionPreName")
        val eDescription = bundel.getString("electionPreDescription")
        val eFormURL = bundel.getString("electionFormURL")
        val eStartDate = bundel.getString("electionPreStartDate")
        val eEndDate = bundel.getString("electionPreEndDate")

        election_future_name.text = eName
        TV_election_future_description.text = eDescription
        startDate.text = eStartDate
        endDate.text = eEndDate
        electionID = eID
        electionName = eName!!

//        var fromScanner: SingleMediaScanner

//        endDate.setOnClickListener {
////            val mmFile = File(filePath.toString())
////            fromScanner = SingleMediaScanner(this, mmFile)
////            pdfFileName = getFileName(filePath!!)
//
//        }
//
//        startDate.setOnClickListener {
//            Log.d("MainActivity", "file path     =>   $filePath    ")
////            Log.d("MainActivity", "from Scanner     =>   $fromScanner    ")
//            Log.d("MainActivity", "pdf file name     =>   $pdfFileName    ")
//        }


//        sendBroadcast(
//            Intent(
//                Intent.ACTION_MEDIA_MOUNTED, Uri.parse(
//                    "content://"
//                            + Environment.getExternalStorageDirectory()
//                )
//            )
//        )

//        BTN_Select_CV.setOnClickListener {
////            if (Build.VERSION.SDK_INT >= 23) {
////                if (checkPermission()) {
////                    filePicker()
////                } else {
////                    requestPermission()
////                }
////            } else {
////                filePicker()
////            }
//        }


        BTN_uploadCV.isEnabled = true

        BTN_uploadCV.setOnClickListener {
            //https://www.surveymonkey.com/r/MTZF9NV
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(eFormURL))
            startActivity(intent)
            BTN_uploadCV.isEnabled = false
//            selectPdf()
        }

//        BTN_uploadCV.setOnClickListener {
//            CoroutineScope(Dispatchers.Main).launch {
//                uploadCandidateCV()
//            }
//        }

    }


//    private fun selectPdf() {
//        val pdfIntent = Intent(Intent.ACTION_GET_CONTENT)
//        pdfIntent.type = "application/pdf"
//        pdfIntent.addCategory(Intent.CATEGORY_OPENABLE)
//        startActivityForResult(pdfIntent, 12)
//    }

//    @SuppressLint("Range")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        // For loading PDF
//        when (requestCode) {
//            12 -> if (resultCode == RESULT_OK) {
//
//                pdfUri = data?.data!!
//                val uri: Uri = data?.data!!
//                val uriString: String = uri.toString()
//                if (uriString.startsWith("content://")) {
//                    var myCursor: Cursor? = null
//                    try {
//                        // Setting the PDF to the TextView
//                        myCursor = applicationContext!!.contentResolver.query(uri, null, null, null, null)
//                        if (myCursor != null && myCursor.moveToFirst()) {
//                            pdfName = myCursor.getString(myCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
//                            pdfTextView.text = pdfName
//                            pdfTextView2.text = pdfUri.toString()
//                        }
//                    } finally {
//                        myCursor?.close()
//                    }
//                }
//            }
//        }
//    }

//    fun prepareFilePart(path: String, partName: String): MultipartBody.Part {
//        val file = File(path)
//        val mediaType = "application/pdf".toMediaType()
//        val requestBody = RequestBody.toString().toRequestBody(mediaType)
//        return MultipartBody.Part.createFormData(path, file.name, requestBody)
//    }




//    private fun launchPicker() {
//        MaterialFilePicker()
//            .withActivity(this)
//            .withRequestCode(FILE_PICKER_REQUEST_CODE)
//            .withHiddenFiles(true)
//            .withFilter(Pattern.compile(".*\\.pdf$"))
//            .withTitle("Select PDF file")
//            .start()
//    }
//
//    fun getMimeType(file: File): String? {
//        var type: String? = null
//        val extension = MimeTypeMap.getFileExtensionFromUrl(file.path)
//        if (extension != null) {
//            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
//        }
//        return type
//    }
//

//    @SuppressLint("Range")
//    fun getFileName(uri: Uri): String? {
//        var result: String? = null
//        if (uri.scheme == "content://") {
//            val cursor = contentResolver.query(uri, null, null, null, null)
//            try {
//                if (cursor != null && cursor.moveToFirst()) {
//                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
//                }
//            } finally {
//                cursor?.close()
//            }
//        }
//        if (result == null) {
//            result = uri.lastPathSegment
//        }
//        return result
//    }



//    fun filePicker() {
//        Toast.makeText(this@CA_FutureElectionDetails, "File Picker Call", Toast.LENGTH_SHORT).show()
//        val openFile = Intent()
//        openFile.action = Intent.ACTION_GET_CONTENT
//        openFile.addCategory(Intent.CATEGORY_OPENABLE)
//        openFile.type = "application/pdf"
//        openFile.putExtra(DocumentsContract.EXTRA_INITIAL_URI, filePath)
//        startActivityIfNeeded(openFile, PICK_PDF_FILE)
////        startActivityForResult(openFile, FILE_PICKER_REQUEST_CODE)
//    }

//    fun requestPermission() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(
//                this@CA_FutureElectionDetails,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE
//            )
//        ) {
//            Toast.makeText(
//                this@CA_FutureElectionDetails,
//                "Please Give Permission to Upload File",
//                Toast.LENGTH_SHORT
//            ).show()
//        } else {
//            ActivityCompat.requestPermissions(
//                this@CA_FutureElectionDetails,
//                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
//                PICK_PDF_FILE
//            )
//        }
//    }

//    fun checkPermission(): Boolean {
//        val result = ContextCompat.checkSelfPermission(
//            this@CA_FutureElectionDetails,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//        )
//        return result == PackageManager.PERMISSION_GRANTED
//    }




//    suspend fun uploadCandidateCV() = withContext(Dispatchers.IO) {
////        val id :String = DocumentsContract.getDocumentId(filePath);
////        val inputStream = getContentResolver().openInputStream(filePath!!)
////        val mfile = File(cacheDir.absolutePath + "/" + id)
////        val filePath = mfile.absolutePath
////            val  path:String = "file:///Users/PC/Desktop/new.pdf" // "file:///mnt/sdcard/FileName.mp3"
////            val files: File = File(filePath.toString())
////        SingleMediaScanner(this@CA_FutureElectionDetails, files)
////        val mimeType: String = URLConnection.guessContentTypeFromName(filePath.toString())
////        val originalFile: File = FileUtils.(this@CA_FutureElectionDetails, filePath)
////        val filePathToUse = uriPathHelper.getPath(this@CA_FutureElectionDetails, file)
////        val uriPathHelper = URIPathHelper()
////        val filePathToUse = uriPathHelper.getPath(this@CA_FutureElectionDetails, filePath!!)
////        pdfFileName = getFileName(filePath!!)
////        val file = prepareImagePart(filePath.toString(),)
////        Log.d("MainActivity", "pdf file =>   $pdfFileName   ")
////        pdfFileName = getFileName(filePath!!)
//        val mimeType: String = "application/pdf"
//        val requestBody = RequestBody.toString().toRequestBody(mimeType.toMediaType())
//        val files: File = File(filePath.toString())




//
//        val filePart = MultipartBody.Part.createFormData("candidate_cv",pdfUri.toString())
//
//        val fileFromPart = prepareFilePart(pdfUri.toString(), pdfName!!)
//
//        val retrofitInstance = RetrofitInstance
//        val requestCall = retrofitInstance.api.uploadCandidateCV(
//            electionID.toString().toRequestBody(),
//            electionName.toRequestBody(),
//            filePart
//        )
//
//
//        requestCall?.enqueue(object : Callback<ResponseBody?> {
//            override fun onResponse(
//                call: retrofit2.Call<ResponseBody?>,
//                response: Response<ResponseBody?>
//            ) {
//                if (response.code() == 400) {
//                    Log.d("MainActivity", "BadRequest : 400")
//                }
//                if (response.code() == 201) {
//                    Log.d("MainActivity", "GoodRequest: 201")
//                }
//            }
//
//            override fun onFailure(call: retrofit2.Call<ResponseBody?>, t: Throwable) {
//                Log.d("MainActivity", "Successfully")
//            }
//        })
//    }





}
