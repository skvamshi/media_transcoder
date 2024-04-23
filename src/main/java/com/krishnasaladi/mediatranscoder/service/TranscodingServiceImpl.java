package com.krishnasaladi.mediatranscoder.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TranscodingServiceImpl implements TranscodingService {

    @Override
    public void transcodeMedia(String inputFilePath, String outputFilePath, Integer resolution, Integer bitrate) {
        // Command to execute FFmpeg
        StringBuilder commandBuilder = new StringBuilder();
        commandBuilder.append("ffmpeg")
                .append(" -i ").append(inputFilePath)
                .append(" -s ").append(resolution).append("x").append(resolution);

        if (bitrate != null) {
            commandBuilder.append(" -b:v ").append(bitrate).append("k");
        }

        commandBuilder.append(" ").append(outputFilePath);

        try {
            // Execute FFmpeg command
            ProcessBuilder processBuilder = new ProcessBuilder(commandBuilder.toString().split("\\s"));
            Process process = processBuilder.start();

            // Wait for the process to finish
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Transcoding successful.");
            } else {
                System.err.println("Transcoding failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error during transcoding: " + e.getMessage());
        }
    }
}
