package com.msh.solutions._355_Design_Twitter;

import java.util.*;

/**
 * Created by monkeysayhi on 2018/2/5.
 */
// 实际应用中，根据不同的读写比例与资源供给，实现方式和性能表现都不同
// 题目未明确，测试数据中的 tweetId 不是自增的，则需要另外根据发布时间排序；同时，时间粒度可能很小，用另外的 自增id 定序
public class Twitter {
  // TODO refactor
  private Map<Integer, List<Tweet>> tweets;
  private Map<Integer, Set<Integer>> relations;

  private static class Tweet {
    private final int contentId;
    private final long postId;

    private static long autoIncrementalPostId = -1;

    private Tweet(int contentId) {
      this.contentId = contentId;
      postId = ++autoIncrementalPostId;
    }
  }

  /**
   * Initialize your data structure here.
   */
  public Twitter() {
    // TODO opt
    tweets = new HashMap<>();
    relations = new HashMap<>();
  }

  /**
   * Compose a new tweet.
   */
  public void postTweet(int userId, int tweetId) {
    registerIfNotExist(userId);
    // TODO 放弃 post 已存在的 tweet
    tweets.get(userId).add(new Tweet(tweetId));
  }

  /**
   * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed
   * must be posted by users who the user followed or by the user herself. Tweets must be ordered
   * from most recent to least recent.
   */
  public List<Integer> getNewsFeed(int userId) {
    registerIfNotExist(userId);
    // TODO opt
    Set<Integer> users = new HashSet<>(relations.get(userId));
    users.add(userId);
    List<Tweet> wholeRecent = new ArrayList<>(10 * (users.size()));
    for (int tmpUserId : users) {
      List<Tweet> tweetsOfOne = tweets.get(tmpUserId);
      for (int i = tweetsOfOne.size() - 1; i >= 0; i--) {
        wholeRecent.add(tweetsOfOne.get(i));
      }
    }
    Collections.sort(wholeRecent, new Comparator<Tweet>() {
      public int compare(Tweet t1, Tweet t2) {
        if (t2.postId > t1.postId) {
          return 1;
        }
        if (t2.postId < t1.postId) {
          return -1;
        }
        return 0;
      }
    });

    // TODO refactor
    int tweetCnt = Math.min(10, wholeRecent.size());
    List<Integer> recent = new ArrayList<>(tweetCnt);
    for (int i = 0; i < tweetCnt; i++) {
      recent.add(wholeRecent.get(i).contentId);
    }
    return recent;
  }

  /**
   * Follower follows a followee. If the operation is invalid, it should be a no-op.
   */
  public void follow(int followerId, int followeeId) {
    registerIfNotExist(followerId);
    registerIfNotExist(followeeId);
    // TODO 放弃 follow 已存在的关系
    relations.get(followerId).add(followeeId);
  }

  /**
   * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
   */
  public void unfollow(int followerId, int followeeId) {
    registerIfNotExist(followerId);
    registerIfNotExist(followeeId);
    // TODO 放弃 unfollow 已不存在的关系
    relations.get(followerId).remove(followeeId);
  }

  private void registerIfNotExist(int userId) {
    // 题目没提供注册接口。这里假设用户第一次使用tweet时自动注册
    if (!tweets.containsKey(userId)) {
      // TODO opt
      tweets.put(userId, new ArrayList<Tweet>());
      relations.put(userId, new HashSet<Integer>());
    }
  }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */