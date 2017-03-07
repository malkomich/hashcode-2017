package com.malkomich.hashcode2017.data;

import java.util.ArrayList;
import java.util.List;

public class RequestDescList {

  private List<RequestDesc> requestDescriptions;

  public RequestDescList() {
    requestDescriptions = new ArrayList<>();
  }

  public void addRequestDesc(RequestDesc requestDesc) {
    requestDescriptions.add(requestDesc);
  }

  public List<RequestDesc> getRequestDescriptions() {
    return requestDescriptions;
  }

  @Override
  public String toString() {
    return String.format("%d Request descriptions", requestDescriptions.size());
  }

}
