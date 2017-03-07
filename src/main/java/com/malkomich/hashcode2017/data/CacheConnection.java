package com.malkomich.hashcode2017.data;

import com.malkomich.hashcode2017.logic.CacheFinder;
import com.malkomich.hashcode2017.logic.Parseable;

public class CacheConnection implements Parseable {

  private EndPoint endPoint;
  private CacheServer cacheServer;
  private int latency;

  private CacheFinder cacheFinder;

  public CacheConnection(EndPoint endPoint, CacheFinder cacheFinder) {
    this.endPoint = endPoint;
    this.cacheFinder = cacheFinder;
  }

  @Override
  public CacheConnection parseLine(String line) {
    String[] stringParams = line.split(" ");

    int id = Integer.parseInt(stringParams[0]);
    cacheServer = cacheFinder.getCacheServerById(id);
    if(cacheServer == null) {
      cacheServer = new CacheServer(id);
      cacheFinder.addCacheServer(cacheServer);
    }
    latency = Integer.parseInt(stringParams[1]);

    return this;
  }

  public EndPoint getEndPoint() {
    return endPoint;
  }

  public CacheServer getCacheServer() {
    return cacheServer;
  }

  public int getLatency() {
    return latency;
  }

}
