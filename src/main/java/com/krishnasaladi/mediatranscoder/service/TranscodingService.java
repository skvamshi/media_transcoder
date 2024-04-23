package com.krishnasaladi.mediatranscoder.service;

public interface TranscodingService {
    void transcodeMedia(String inputFilePath, String outputFilePath, Integer resolution, Integer bitrate);
}
