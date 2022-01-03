package com.abdullaev.jobschedular

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startJob=findViewById<Button>(R.id.startJob)
        val endJob=findViewById<Button>(R.id.endJob)

        val endJobaw=findViewById<Button>(R.id.endJob)

        startJob.setOnClickListener {
            val componentName= ComponentName(this, ExampleJobService::class.java)
            val jobInfo=JobInfo.Builder(123, componentName).setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15*60*1000)
                .build()

                val jobScheduler=getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

            val resultCode:Int=jobScheduler.schedule(jobInfo)

            if (resultCode==JobScheduler.RESULT_SUCCESS){
                Log.i(TAG, "onCreate: Job Scheduled")
            }else{
                Log.i(TAG, "onCreate: Job failed")
            }
        }

        endJob.setOnClickListener {
            val jobScheduler=getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.cancel(123)
            Log.i(TAG, "onCreate: Job cancelled")
        }
    }
}