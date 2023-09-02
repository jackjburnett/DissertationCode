package com.jb.sensingapplication

data class AppUsage(
    val appname: String,
    val packagename: String,
    val timeclosed: String,
    val timeopened: String,
    // val usageid: Int,
    val userid: Int
)