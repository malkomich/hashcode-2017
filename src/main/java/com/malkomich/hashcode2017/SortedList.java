package com.malkomich.hashcode2017;

import java.util.ArrayList;
import java.util.Collections;

public class SortedList<T> extends ArrayList<T> {

  /**
   * Generated UID
   */
  private static final long serialVersionUID = -5566424341030057431L;

  @SuppressWarnings("unchecked")
  public void insertSorted(T value) {
    add(value);
    Comparable<T> cmp = (Comparable<T>) value;
    for (int i = size()-1; i > 0 && cmp.compareTo(get(i-1)) < 0; i--) {
      Collections.swap(this, i, i-1);
    }
  }
}
