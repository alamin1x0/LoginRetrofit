package com.developeralamin.valleysoftapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.developeralamin.valleysoftapp.R
import com.ironsource.mediationsdk.IronSource
import com.ironsource.mediationsdk.logger.IronSourceError
import com.ironsource.mediationsdk.sdk.InterstitialListener

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        IronSource.init(this, "1ca85ba85")

        IronSource.init(this, "1ca85ba85",
            IronSource.AD_UNIT.INTERSTITIAL,
            IronSource.AD_UNIT.REWARDED_VIDEO,
            IronSource.AD_UNIT.BANNER)

        IronSource.loadInterstitial()


        IronSource.setInterstitialListener(object : InterstitialListener {
            override fun onInterstitialAdReady() {
                // Implement your logic here
            }

            override fun onInterstitialAdLoadFailed(ironSourceError: IronSourceError?) {
                // Implement your logic here
            }

            override fun onInterstitialAdOpened() {
                // Implement your logic here
            }

            override fun onInterstitialAdClosed() {
                // Implement your logic here
            }

            override fun onInterstitialAdShowSucceeded() {
                // Implement your logic here
            }

            override fun onInterstitialAdShowFailed(ironSourceError: IronSourceError?) {
                // Implement your logic here
            }

            override fun onInterstitialAdClicked() {
                // Implement your logic here
            }
        })


        findViewById<View>(R.id.interstaildId).setOnClickListener {
            IronSource.showInterstitial()
        }
    }

    override fun onResume() {
        super.onResume()
        IronSource.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        IronSource.onPause(this)
    }
}
