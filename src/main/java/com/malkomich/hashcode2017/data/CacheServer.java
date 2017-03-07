package com.malkomich.hashcode2017.data;

import java.util.ArrayList;
import java.util.List;

import com.malkomich.hashcode2017.CachedVideo;
import com.malkomich.hashcode2017.NoEnoughSpaceError;
import com.malkomich.hashcode2017.SortedList;

public class CacheServer {

  private int id;
  private int availableCacheSize;
  private List<CachedVideo> storedVideos;
  private List<Integer> videoIds;

  public CacheServer(int id) {
    this.id = id;
    storedVideos = new SortedList<>();
    videoIds = new ArrayList<>();
  }

  public int getId() {
    return id;
  }

  public void setCacheSize(int cacheSize) {
    this.availableCacheSize = cacheSize;
  }

  public int getAvailableCacheSize() {
    return availableCacheSize;
  }

  public void addVideo(Video video, int weight) throws NoEnoughSpaceError {
    if(!videoIds.contains(video.getId())) {
      if(video.getSize() > availableCacheSize) {
        throw new NoEnoughSpaceError();
      }
      videoIds.add(video.getId());
      CachedVideo cachedVideo = new CachedVideo(video, weight);
      storedVideos.add(cachedVideo);
      availableCacheSize -= video.getSize();
    }
  }

  public void replaceVideo(Video video, int weight) {
    if(storedVideos.get(0).getWeight() < weight) {
      storedVideos.remove(0);
      CachedVideo cachedVideo = new CachedVideo(video, weight);
      storedVideos.add(cachedVideo);
    }
  }

  public List<CachedVideo> getVideos() {
    return storedVideos;
  }

  public boolean hasVideo(Video video) {
    return storedVideos.contains(video);
  }
}
