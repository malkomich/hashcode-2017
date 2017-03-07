package com.malkomich.hashcode2017.data;

import com.malkomich.hashcode2017.logic.EndPointFinder;
import com.malkomich.hashcode2017.logic.Parseable;
import com.malkomich.hashcode2017.logic.VideoFinder;

public class RequestDesc implements Parseable {

  private int numRequests;
  private Video video;
  private EndPoint endPoint;

  private VideoFinder videoFinder;
  private EndPointFinder endPointFinder;

  public RequestDesc(VideoFinder videoFinder, EndPointFinder endPointFinder) {
    this.videoFinder = videoFinder;
    this.endPointFinder = endPointFinder;
  }

  @Override
  public RequestDesc parseLine(String line) {
    String[] stringParams = line.split(" ");

    video = videoFinder.getVideoById(Integer.parseInt(stringParams[0]));
    endPoint = endPointFinder.getEndPointById(Integer.parseInt(stringParams[1]));
    numRequests = Integer.parseInt(stringParams[2]);

    return this;
  }

  public int getNumRequests() {
    return numRequests;
  }

  public Video getVideo() {
    return video;
  }

  public EndPoint getEndPoint() {
    return endPoint;
  }

}
