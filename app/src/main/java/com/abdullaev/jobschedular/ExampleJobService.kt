package com.abdullaev.jobschedular

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import java.lang.Exception
import kotlin.concurrent.thread

class ExampleJobService: JobService() {

    private final val TAG:String="ExampleJobService"

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.i(TAG, "onStartJob:")
        doBackgroudWork(params)
        return true
    }

    private fun doBackgroudWork(params: JobParameters?) {
        Thread(object : Runnable{
            override fun run() {
                for (i in 0..10){
                    Log.i(TAG, "run: $i")
                    if (jobCancelled){
                        return
                    }
                    try {
                        Thread.sleep(1000)
                    }catch (e:Exception){
                    }
                }
                Log.i(TAG, "run: job finished")
                jobFinished(params, false)
            }
        }).start()
    }
    private var jobCancelled=false
    override fun onStopJob(params: JobParameters?): Boolean {


        Log.i(TAG, "onStopJob: job cancelled before ")
        jobCancelled=true
        return true

    }
}