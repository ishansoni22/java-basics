package com.ishan.javabasics.uuids;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

public class UniqueIDGenerator {

  private static final SecureRandom random = new SecureRandom();
  private static final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();

  public static String generate() {
    long startTime = System.nanoTime();
    String key = "ABC"; //All ASCII characters -> 3 bytes (Base64 will convert it into 4 bytes)
    //vs
    //String key = "•AB"; //The first is a non-ASCII characters and needs 3 bytes,
    //Therefore produced Base64 output -> (5 * 8)/6 characters = 7
    key = UUID.randomUUID().toString().substring(0, 5);

    /**
     Get the specific UUID (eg a 3 byte UUID(3 character - if ASCII/Unicode encoded)
     will give a 4 byte or 4 character Base64 Key)
     i.e 64*64*64*64 different combinations

     Remember the system design interview questions?
     **/
    byte[] arr = key.getBytes();
    String encodedKey = encoder.encodeToString(arr);
    long estimatedTime = System.nanoTime() - startTime;
    System.out.println(estimatedTime);
    return encodedKey;
  }

}
