package com.malkomich.hashcode2017.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.malkomich.hashcode2017.logic.CacheFinder;

public class CacheServerList implements CacheFinder {

  private Map<Integer, CacheServer> cacheServers;
  private int cacheSize;

  public CacheServerList(int cacheSize) {
    cacheServers = new HashMap<>();
    this.cacheSize = cacheSize;
  }

  @Override
  public CacheServer getCacheServerById(int id) {
    return cacheServers.get(id);
  }

  @Override
  public void addCacheServer(CacheServer cacheServer) {
    cacheServer.setCacheSize(cacheSize);
    cacheServers.put(cacheServer.getId(), cacheServer);
  }

  public Collection<CacheServer> getCacheServerCollection() {
    return cacheServers.values();
  }

  @Override
  public String toString() {
    return String.format("%d Cache Servers with %dMB each one", cacheServers.size(), cacheSize);
  }
}
