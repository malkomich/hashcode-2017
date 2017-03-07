package com.malkomich.hashcode2017.data;

import com.malkomich.hashcode2017.logic.Parseable;

public class MainData implements Parseable {

  private int numVideos;
  private int numEndpoints;
  private int numRequests;
  private int numCaches;
  private int cacheSize;

  @Override
  public MainData parseLine(String line) {
    String[] stringParams = line.split(" ");

    numVideos = Integer.parseInt(stringParams[0]);
    numEndpoints = Integer.parseInt(stringParams[1]);
    numRequests = Integer.parseInt(stringParams[2]);
    numCaches = Integer.parseInt(stringParams[3]);
    cacheSize = Integer.parseInt(stringParams[4]);

    return this;
  }

  public int getNumEndPoints() {
    return numEndpoints;
  }

  public int getNumRequests() {
    return numRequests;
  }

  public int getCacheSize() {
    return cacheSize;
  }

  @Override
  public String toString() {
    return String.format(
        "There are %d videos, %d endpoints, %d request descriptions and %d cache servers with %d MB each one",
        numVideos, numEndpoints, numRequests, numCaches, cacheSize);
  }
}