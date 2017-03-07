package com.malkomich.hashcode2017.data;

import java.util.ArrayList;
import java.util.List;

import com.malkomich.hashcode2017.logic.Parseable;

public class EndPoint implements Parseable {

  private int id;
  private int latencyToDatacenter;
  private int numCaches;
  private List<CacheConnection> cacheConnections;

  public EndPoint(int id) {
    this.id = id;
    cacheConnections = new ArrayList<>();
  }

  @Override
  public EndPoint parseLine(String line) {
    String[] stringParams = line.split(" ");

    latencyToDatacenter = Integer.parseInt(stringParams[0]);
    numCaches = Integer.parseInt(stringParams[1]);
    return this;
  }

  public int getLatencySavedForVideoRequest(Video video, CacheServerList cacheServerList) {
    for(CacheConnection cacheConn: cacheConnections) {
      if(cacheServerList.getCacheServerById(cacheConn.getCacheServer().getId()).hasVideo(video)){
        return (latencyToDatacenter - cacheConn.getLatency());
      }
    }
    return 0;
  }

  public int getId() {
    return id;
  }

  public int getLatencyToDatacenter() {
    return latencyToDatacenter;
  }

  public void addCacheConnection(CacheConnection connection) {
    cacheConnections.add(connection);
  }

  public List<CacheConnection> getCacheConnections() {
    return cacheConnections;
  }

  public int getNumCaches() {
    return numCaches;
  }

  @Override
  public String toString() {
    return String.format("Endpoint with %dms latency to datacenter, and connected to %d cache servers",
        latencyToDatacenter, numCaches);
  }

}
