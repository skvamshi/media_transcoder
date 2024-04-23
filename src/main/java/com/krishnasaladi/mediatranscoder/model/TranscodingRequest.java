package com.krishnasaladi.mediatranscoder.model;

import lombok.Data;

@Data
public class TranscodingRequest {
    private String inputFilePath;
    private String outputFilePath;
    private int resolution;
    private int bitrate;

    // Getters and setters
}
