package com.malkomich.hashcode2017.logic;

import com.malkomich.hashcode2017.data.CacheServer;

public interface CacheFinder {

  CacheServer getCacheServerById(int id);

  void addCacheServer(CacheServer cacheServer);
}
