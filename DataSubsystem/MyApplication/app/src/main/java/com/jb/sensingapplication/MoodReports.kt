package com.jb.sensingapplication

import android.os.Parcel
import android.os.Parcelable

data class MoodReports(
    val q1: Int,
    val q10: Int,
    val q11: Int,
    val q12: Int,
    val q13: Int,
    val q14: Int,
    val q15: Int,
    val q16: Int,
    val q17: Int,
    val q18: Int,
    val q2: Int,
    val q3: Int,
    val q4: Int,
    val q5: Int,
    val q6: Int,
    val q7: Int,
    val q8: Int,
    val q9: Int,
    // val reportid: Int,
    val time: String,
    val userid: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(q1)
        parcel.writeInt(q10)
        parcel.writeInt(q11)
        parcel.writeInt(q12)
        parcel.writeInt(q13)
        parcel.writeInt(q14)
        parcel.writeInt(q15)
        parcel.writeInt(q16)
        parcel.writeInt(q17)
        parcel.writeInt(q18)
        parcel.writeInt(q2)
        parcel.writeInt(q3)
        parcel.writeInt(q4)
        parcel.writeInt(q5)
        parcel.writeInt(q6)
        parcel.writeInt(q7)
        parcel.writeInt(q8)
        parcel.writeInt(q9)
        parcel.writeString(time)
        parcel.writeInt(userid)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MoodReports> {
        override fun createFromParcel(parcel: Parcel): MoodReports {
            return MoodReports(parcel)
        }

        override fun newArray(size: Int): Array<MoodReports?> {
            return arrayOfNulls(size)
        }
    }
}