// Application package name
package com.jb.sensingapplication

// libraries required for UI and application environment
import android.annotation.SuppressLint
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.NameNotFoundException
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jb.sensingapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// Unsure why AppCompatActivity resolved the startActivity issue
class MainActivity : AppCompatActivity() {

    // Sets the variable types to the necessary  classes, used LateInit for safety (Cookbook and BNR recommendation), these are uninitialised
    private lateinit var binding: ActivityMainBinding
    private lateinit var usageStatsManager: UsageStatsManager
    private lateinit var locationManager: LocationManager

    // creates a mutable list for the GPS locations
    private val GPSList = mutableListOf<GPSLog>()

    // The function that runs when the application opens
    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        super.onCreate(savedInstanceState)

        // binds the xml elements, initialises binding, sets the view to the binding's root (xml view)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        val reportResult = intent.getStringExtra("result")



        // sets the content view to the binding's root
        setContentView(view)

        // initialises usageStatsManger as the necessary system service
        usageStatsManager = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

        // same as usageStatsManager
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // merged all interactions into one
        binding.buttonSendStats.setOnClickListener {
            val apiclient =  RetrofitClient.getClient().create(SensingAPI::class.java)
            val appKey = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
            val userID = sharedPreferences.getString("UserID", "No user ID").toString()

            if(appKey==getAppKey(userID.toString())){
                //sendUsageStatistics(apiclient, userID)
                //sendGPSLog(apiclient, userID)
                sendMoodReport(apiclient, reportResult.toString(), userID)
                binding.textViewStats.text="Usage Sent"
            }else{
                binding.textViewStats.text="Incorrect user ID for linked device"
            }
        }

        //adds an onclick that opens the Mood-Self Report activity
        binding.buttonMoodSelfReport.setOnClickListener {
            val intent = Intent(this, MoodSelfReport::class.java)
            startActivity(intent)
        }

        if(sharedPreferences.getString("UserID", "No user ID")=="No user ID") {
            binding.editText.visibility=android.view.View.VISIBLE
            binding.buttonSendStats.visibility=android.view.View.GONE
            binding.buttonCheckUserID.visibility=android.view.View.GONE
            binding.buttonMoodSelfReport.visibility=android.view.View.GONE
            binding.buttonSetUserID.visibility=android.view.View.VISIBLE
            binding.buttonSetUserID.setOnClickListener {
                val editor = sharedPreferences.edit()
                editor.putString("UserID", binding.editText.text.toString())
                editor.apply()
                binding.editText.visibility=android.view.View.GONE
                binding.buttonSendStats.visibility=android.view.View.VISIBLE
                binding.buttonMoodSelfReport.visibility=android.view.View.VISIBLE
                binding.buttonSetUserID.visibility=android.view.View.GONE
                binding.buttonCheckUserID.visibility=android.view.View.VISIBLE
            }
        }
        binding.buttonCheckUserID.setOnClickListener {
            binding.textViewStats.text=sharedPreferences.getString("UserID", "No user ID")
        }
    }

    private fun getAppKey(userID: String): String {
        return Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
    }

    private fun sendMoodReport(apiclient: SensingAPI, reportResult: String,  userID: String) {
        if(reportResult!="null"){
            val reportList=reportResult.split("\n")
            Log.i("ReportResult", reportList.toString())
            val reportJson=MoodReports(
                q1=reportList[0].split(": ")[1].drop(6).toInt(),
                q10=reportList[9].split(": ")[1].drop(6).toInt(),
                q11=reportList[10].split(": ")[1].drop(6).toInt(),
                q12=reportList[11].split(": ")[1].drop(6).toInt(),
                q13=reportList[12].split(": ")[1].drop(6).toInt(),
                q14=reportList[13].split(": ")[1].drop(6).toInt(),
                q15=reportList[14].split(": ")[1].drop(6).toInt(),
                q16=reportList[15].split(": ")[1].drop(6).toInt(),
                q17=reportList[16].split(": ")[1].split(",")[0].drop(6).toInt(),
                q18=reportList[17].split(": ")[1].drop(6).toInt(),
                q2=reportList[1].split(": ")[1].drop(6).toInt(),
                q3=reportList[2].split(": ")[1].drop(6).toInt(),
                q4=reportList[3].split(": ")[1].drop(6).toInt(),
                q5=reportList[4].split(": ")[1].drop(6).toInt(),
                q6=reportList[5].split(": ")[1].drop(6).toInt(),
                q7=reportList[6].split(": ")[1].drop(6).toInt(),
                q8=reportList[7].split(": ")[1].drop(6).toInt(),
                q9=reportList[8].split(": ")[1].drop(6).toInt(),
                userid = userID.toInt(),
                time = reportList[18]
            )
            apiclient.postMoodReport(reportJson).enqueue(object : Callback<MoodReports> {
                override fun onResponse(call: Call<MoodReports>, response: Response<MoodReports>) {
                    // handle the response
                    Log.i("Log",response.body().toString())
                }
                override fun onFailure(call: Call<MoodReports>, t: Throwable) {
                    // handle the failure
                    Log.i("Log","Failure")
                }
            })
        }
    }

    private fun sendGPSLog(apiclient: SensingAPI, userID: String) {
        retrieveLastKnownLocation(userID)
        for(i in GPSList){
            Log.i("Log", i.toString())
            apiclient.postGPSLog(i).enqueue(object : Callback<GPSLog> {
                override fun onResponse(call: Call<GPSLog>, response: Response<GPSLog>) {
                    // handle the response
                    Log.i("Log",response.code().toString())
                }
                override fun onFailure(call: Call<GPSLog>, t: Throwable) {
                    // handle the failure
                }
            })
        }
    }

    private fun sendUsageStatistics(apiclient: SensingAPI, userID: String) {
        val endTime = System.currentTimeMillis()
        val startTime = endTime - 86400000 // 24 hours in milliseconds (24*60*60*1000)

        // runs the getUsageStatistics function
        for(i in getUsageStatistics(startTime, endTime, userID)){
            Log.i("Log", i.toString())
            apiclient.postAppUsage(i).enqueue(object : Callback<AppUsage> {
                override fun onResponse(call: Call<AppUsage>, response: Response<AppUsage>) {
                    // handle the response
                    Log.i("Log",response.code().toString())
                }
                override fun onFailure(call: Call<AppUsage>, t: Throwable) {
                    // handle the failure
                }
            })
        }
    }

    // Function to get usage statistics, takes the start and end time and returns a list of usage statistics
    private fun getUsageStatistics(startTime: Long, endTime: Long, userID: String): List<AppUsage> {
        // creates a mutable list to add stats to, and sets the default interval to daily, this may be changed if backlogs are created
        val usageStatsList = mutableListOf<AppUsage>()
        val interval = UsageStatsManager.INTERVAL_DAILY

        // Queries the usage stats manager using the start and end time, with a daily interval
        val stats = usageStatsManager.queryUsageStats(interval, startTime, endTime)
        val pm = getApplicationContext().getPackageManager();
        // if stats is not empty, add all the stats to the list, if it is null then usageStatsList stays uninitialised
        if (stats != null) {
            for(i in stats){
                var appName = try {
                    val x = pm.getApplicationLabel(pm.getApplicationInfo(i.packageName, 0)).toString()
                    if (x.length>64){
                        "App Name Too Long"
                    }else{
                        if(checkAppName(x)){
                            x
                        }else{
                            "App Name Incompatible"
                        }
                    }
                } catch (e: NameNotFoundException) {
                    "App Name Not Found"
                }
                usageStatsList.add(AppUsage(appName, i.packageName, java.sql.Timestamp(i.lastTimeStamp).toString(), java.sql.Timestamp(i.firstTimeStamp).toString(), userID.toInt()))
            }
        }

        // Returns the usageStatsList
        return usageStatsList
    }

    // Suppressed until permission manager implemented
    @SuppressLint("MissingPermission")
    private fun retrieveLastKnownLocation(userID: String) {
        // sets the value of lastKnownLocation to the result of querying said class using the location manager
        // if location permissions are not enabled, app will crash
        val lastKnownLocation: Location? = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        if (lastKnownLocation != null) {
            // creates latitude and longitude variables, then combines them into a single string before outputting string
            // output is solely for testing purposes
            val latitude = lastKnownLocation.latitude
            val longitude = lastKnownLocation.longitude
            // uses java.sql.Timestamp to convert to timestamp, if reused will move to import
            val time = java.sql.Timestamp(System.currentTimeMillis())

            // adds current location to list, this will become automated in final app
            GPSList.add(GPSLog(latitude.toString(), longitude.toString(), time.toString(), userID.toInt()))

        }
    }

    fun checkAppName(input: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9 ]+\$")
        return regex.matches(input)
    }
}
