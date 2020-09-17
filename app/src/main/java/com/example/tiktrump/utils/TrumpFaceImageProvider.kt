package com.example.tiktrump.utils

import com.example.tiktrump.R

class TrumpFaceImageProvider {
    private var trumpFacesList = intArrayOf(
        R.raw.trump1,
        R.raw.trump2,
        R.raw.trump3,
        R.raw.trump4,
        R.raw.trump5,
        R.raw.trump6,
        R.raw.trump7,
        R.raw.trump8,
        R.raw.trump9,
        R.raw.trump10
    )

    fun getRandomTrumpFace() : Int {
        return trumpFacesList.random()
    }
}
