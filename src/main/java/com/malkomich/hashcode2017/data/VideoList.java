package com.malkomich.hashcode2017.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.malkomich.hashcode2017.logic.Parseable;
import com.malkomich.hashcode2017.logic.VideoFinder;

public class VideoList implements Parseable, VideoFinder {

  private Map<Integer, Video> videosMap;

  public VideoList() {
    videosMap = new HashMap<>();
  }

  @Override
  public VideoList parseLine(String line) {
    String[] stringParams = line.split(" ");

    for(int i = 0; i < stringParams.length; i++) {
      videosMap.put(i, new Video(i, Integer.parseInt(stringParams[i])));
    }

    return this;
  }

  @Override
  public String toString() {
    StringBuilder stb = new StringBuilder();
    int cont = 1;
    stb.append("Videos: {");
    for(Entry<Integer,Video> item: videosMap.entrySet()) {
      stb.append(String.format("%d: \"%dMB\"", item.getKey(), item.getValue().getSize()));
      stb.append(cont < videosMap.size() ? ", ": "");
      cont++;
    }
    stb.append("}");
    return stb.toString();
  }

  @Override
  public Video getVideoById(int id) {
    return videosMap.get(id);
  }
}
