package com.google.resting.async.listener;

import com.google.resting.async.request.RequestProgress;


public interface RequestProgressListener {

    public RequestProgress onRequestProgressUpdate();
}