package com.krishnasaladi.mediatranscoder.controller;

import com.krishnasaladi.mediatranscoder.model.TranscodingRequest;
import com.krishnasaladi.mediatranscoder.service.TranscodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TranscoderJobController {

  private final TranscodingService transcodingService;

  @Autowired
  public TranscoderJobController(TranscodingService transcodingService) {
    this.transcodingService = transcodingService;
  }

  @PostMapping("/transcode")
  public String transcodeMedia(@RequestBody TranscodingRequest transcodingRequest) {
    transcodingService.transcodeMedia(
            transcodingRequest.getInputFilePath(),
            transcodingRequest.getOutputFilePath(),
            transcodingRequest.getResolution(),
            transcodingRequest.getBitrate()
    );
    return "Transcoding job submitted successfully.";
  }


}
