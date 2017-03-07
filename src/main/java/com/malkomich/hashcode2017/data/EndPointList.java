package com.malkomich.hashcode2017.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.malkomich.hashcode2017.logic.EndPointFinder;

public class EndPointList implements EndPointFinder {

  private Map<Integer, EndPoint> endPointsMap;

  public EndPointList() {
    endPointsMap = new HashMap<>();
  }

  public void addEndPoint(EndPoint endpoint) {
    endPointsMap.put(endpoint.getId(), endpoint);
  }

  @Override
  public String toString() {
    StringBuilder stb = new StringBuilder();
    int cont = 1;
    stb.append("EndPoints: {");
    for(Entry<Integer,EndPoint> item: endPointsMap.entrySet()) {
      stb.append(String.format("%d: {\"lat\": %dms, \"caches\": %d}",
          item.getKey(),
          item.getValue().getLatencyToDatacenter(),
          item.getValue().getNumCaches()));
      stb.append(cont < endPointsMap.size() ? ", ": "");
      cont++;
    }
    stb.append("}");
    return stb.toString();
  }

  @Override
  public EndPoint getEndPointById(int id) {
    return endPointsMap.get(id);
  }
}
