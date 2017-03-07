package com.malkomich.hashcode2017;

import com.malkomich.hashcode2017.data.Video;

public class CachedVideo implements Comparable<CachedVideo> {

  private Video video;
  private int weight;

  public CachedVideo(Video video, int weight) {
    this.video = video;
    this.weight = weight;
  }

  public Video getVideo() {
    return video;
  }

  public int getWeight() {
    return weight;
  }

  @Override
  public int compareTo(CachedVideo o) {
    if(weight < o.getWeight()) {
      return -1;
    } else if(weight > o.getWeight()) {
      return 1;
    } else {
      return 0;
    }
  }
}
