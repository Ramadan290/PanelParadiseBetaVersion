package com.example.panelparadisebetaversion;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.constraints.controllers.BatteryChargingController;

import java.util.concurrent.TimeUnit;

public class Workman extends Worker{
     public Workman(@NonNull Context context , @NonNull WorkerParameters workerParams){
         super(context , workerParams);
     }
     @NonNull
     @Override
    public Result doWork(){
         Log.d("work","doWork : ");
         return null;
     }

     //Task will run one time only
    public static void OnofRequest(){
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(Workman.class)
                .setInitialDelay(2, TimeUnit.SECONDS) // the time the task will take to run
                .setConstraints( getConst()) //The condition for the task to run
                .build();
        WorkManager.getInstance().enqueue(oneTimeWorkRequest); // Schedule
    }


    public static void Periodic_x(){
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(Workman.class,2000,TimeUnit.MILLISECONDS)
                .setConstraints(getConst())
                .addTag("Xy") // unique name to task
                .build();
        WorkManager.getInstance().enqueueUniquePeriodicWork("Xy" ,
                ExistingPeriodicWorkPolicy.REPLACE,periodicWorkRequest);
    }

    public static Constraints getConst(){
         Constraints constraints = new Constraints.Builder()
                 .setRequiredNetworkType(NetworkType.CONNECTED)
                 .build();
         return constraints ;
    }
}
