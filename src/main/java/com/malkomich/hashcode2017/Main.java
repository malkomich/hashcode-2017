package com.malkomich.hashcode2017;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

import com.malkomich.hashcode2017.data.CacheConnection;
import com.malkomich.hashcode2017.data.CacheServer;
import com.malkomich.hashcode2017.data.CacheServerList;
import com.malkomich.hashcode2017.data.EndPoint;
import com.malkomich.hashcode2017.data.EndPointList;
import com.malkomich.hashcode2017.data.MainData;
import com.malkomich.hashcode2017.data.RequestDesc;
import com.malkomich.hashcode2017.data.RequestDescList;
import com.malkomich.hashcode2017.data.VideoList;

public class Main {

  private MainData mainData;
  private VideoList videoList;
  private EndPointList endPointList;
  private CacheServerList cacheServerList;
  private RequestDescList requestDescList;

  private static final String[] INPUT_FILES = {
    "input/me_at_the_zoo.in",
    "input/videos_worth_spreading.in",
    "input/trending_today.in",
    "input/kittens.in",
  };

  public static void main(String[] args) {
    Main main = new Main();

    String inputFile = INPUT_FILES[3];

    /* Parse input file data */
    main.init(inputFile);

    /* Execute optimization algorithm */
    main.execute();

    /* Calculate optimization score results */
    main.calculateScore();

    /* Print output in the required format */
    main.writeOutput(inputFile);
  }

  private void init(String filePath) {
    Scanner fileScanner;
    //Get file from resources folder
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource(filePath).getFile());
    try {
      fileScanner = new Scanner(file);
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("File can't be opened");
      return;
    }

    // Parse first line with main data
    mainData = new MainData().parseLine(fileScanner.nextLine());
    System.out.println(mainData);

    // Parse second line with video sizes
    videoList = new VideoList().parseLine(fileScanner.nextLine());
    System.out.println(videoList);

    // Parse next line for datacenter & cache's latencies
    endPointList = new EndPointList();
    cacheServerList = new CacheServerList(mainData.getCacheSize());
    for(int i = 0; i < mainData.getNumEndPoints(); i++) {
      EndPoint endpoint = new EndPoint(i).parseLine(fileScanner.nextLine());
      for(int j = 0; j < endpoint.getNumCaches(); j++) {
        endpoint.addCacheConnection(new CacheConnection(endpoint, cacheServerList).parseLine(fileScanner.nextLine()));
      }
      endPointList.addEndPoint(endpoint);
    }
    System.out.println(endPointList);
    System.out.println(cacheServerList);

    // Parse next lines for requests descriptions
    requestDescList = new RequestDescList();
    for(int i = 0; i < mainData.getNumRequests(); i++) {
      requestDescList.addRequestDesc(new RequestDesc(videoList, endPointList).parseLine(fileScanner.nextLine()));
    }
    System.out.println(requestDescList);

    // Parsing FINISHED
    fileScanner.close();
  }

  private void execute() {
    for(RequestDesc requestDesc: requestDescList.getRequestDescriptions()) {
      EndPoint reqEndPoint = requestDesc.getEndPoint();
      for(CacheConnection connection: reqEndPoint.getCacheConnections()) {
        CacheServer cacheServ = connection.getCacheServer();
        int weight = requestDesc.getNumRequests() * (reqEndPoint.getLatencyToDatacenter() - connection.getLatency());
        try {
          cacheServ.addVideo(requestDesc.getVideo(), weight);
        } catch (NoEnoughSpaceError e) {
          // TODO: Check if the video is preferable to be replace by another
          cacheServ.replaceVideo(requestDesc.getVideo(), weight);
        }
      }
    }
  }

  private void calculateScore() {
//    int savingPerRequests = 0;
//    int totalRequests = 0;
//
//    for (RequestDesc requestDesc: requestDescList.getRequestDescriptions()) {
//      int savingPerVideo = requestDesc.getEndPoint()
//        .getLatencySavedForVideoRequest(requestDesc.getVideo(), cacheServerList);
//
//      savingPerRequests += savingPerVideo * requestDesc.getNumRequests();
//      totalRequests += requestDesc.getNumRequests();
//    }
//
//    int score = savingPerRequests / totalRequests;
//    System.out.format("%d ms\n", score);
//    System.out.format("%d final score\n", score * 1000);
  }

  private void writeOutput(String filePath) {

    StringBuilder stb = new StringBuilder();

    Collection<CacheServer> cacheServers = cacheServerList.getCacheServerCollection();
    stb.append(String.format("%d\n", cacheServers.size()));

    for(CacheServer cacheServer: cacheServers) {
      stb.append(String.format("%d ", cacheServer.getId()));
      for(CachedVideo cachedVideo: cacheServer.getVideos()) {
        stb.append(String.format("%d ", cachedVideo.getVideo().getId()));
      }
      stb.append("\n");
    }

    File file = getOutputFile(filePath);
    FileWriter writer = null;

    try {
      writer = new FileWriter(file);

      writer.write(stb.toString());

      writer.close();

    } catch (IOException e1) {
      e1.printStackTrace();

    } finally {

      if(writer != null) {
        try {
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    System.out.format("\nOutput file created on %s\n", file.getAbsolutePath());

  }

  private File getOutputFile(String inputFileName) {
    ClassLoader classLoader = getClass().getClassLoader();
    File inputFile = new File(classLoader.getResource(inputFileName).getFile());

    String outputFilePath = inputFile.getAbsolutePath().replace(".in", ".out");
    File outputFile = new File(outputFilePath);

    try {
      outputFile.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return outputFile;
  }

}
