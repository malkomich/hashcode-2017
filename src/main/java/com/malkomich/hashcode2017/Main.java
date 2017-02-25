package com.malkomich.hashcode2017;

import java.lang.ClassLoader;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class Main {

  private int numVideos,
    numEndpoints,
    numRequests,
    numCaches,
    cacheSize;

  private static final String[] INPUT_FILES = {
    "input/me_at_the_zoo.in",
    "input/videos_worth_spreading.in",
    "input/trending_today.in",
    "input/kittens.in",
  };

  public static void main(String[] args) {
    Main main = new Main();

    main.init();
  }

  public void init() {
    Scanner fileScanner = getFileScanner(INPUT_FILES[0]);

    // Parse first line
    parseValues(fileScanner.nextLine(), " ", numVideos, numEndpoints, numRequests, numCaches, cacheSize);
    System.out.printf("There are %d videos, $d endpoints, %d request descriptions, %d cache servers," +
      " and the size for each cache is %d MB", numVideos, numEndpoints, numRequests, numCaches, cacheSize);
  }

  private Scanner getFileScanner(String fileName) {

    //Get file from resources folder
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource(fileName).getFile());

    try (Scanner scanner = new Scanner(file)) {
      return scanner;

    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("File can't be opened");
      return null;
    }
  }

  private void parseValues(String line, String separator, int ...vars) {
    String[] stringParams = line.split(separator);
    if(vars.length != stringParams.length) {
      return;
    }

    for(int i = 0; i < stringParams.length; i++) {
      vars[i] = Integer.parseInt(stringParams[i]);
    }
  }

}
