package com.msh.solutions._71_Simplify_Path;

import java.util.Stack;

/**
 * Created by monkeysayhi on 2018/4/3.
 */
public class Solution {
  // 边界条件多
  public String simplifyPath(String path) {
    if (path == null) {
      return null;
    }

    String[] orgDirs = path.split("\\/+");
    Stack<String> slfDirs = new Stack<>();
    for (String dir : orgDirs) {
      if (dir.equals("")) {
        continue;
      }
      if (dir.equals(".")) {
        continue;
      }
      if (dir.equals("..")) {
        if (slfDirs.size() > 0) {
          slfDirs.pop();
        }
        continue;
      }
      slfDirs.push(dir);
    }

    if (slfDirs.size() == 0) {
      return "/";
    }
    StringBuilder slfPath = new StringBuilder();
    for (String dir : slfDirs) {
      slfPath.append("/").append(dir);
    }
    return slfPath.toString();
  }
}