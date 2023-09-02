package com.jb.sensingapplication
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SensingAPI {

    @GET("users/{userid}")
    fun getUserDetails(@Path("userid") userId: Int): Call<UserDetails>

    @POST("appusage/")
    fun postAppUsage(@Body appUsage: AppUsage): Call<AppUsage>

    @POST("gpslog/")
    fun postGPSLog(@Body gpsLog: GPSLog): Call<GPSLog>

    @POST("reports/")
    fun postMoodReport(@Body moodReports: MoodReports): Call<MoodReports>
}