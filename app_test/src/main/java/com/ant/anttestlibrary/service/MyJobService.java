package com.ant.anttestlibrary.service;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.ComponentName;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/11/15.
 * describe：
 */
public class MyJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {

        JobInfo.Builder builder = new JobInfo.Builder( 1,
                new ComponentName( getPackageName(),
                        MyJobService.class.getName() ) );
        builder.setPeriodic(3000);
        builder.setPersisted(true);

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
