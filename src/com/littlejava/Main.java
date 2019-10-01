package com.littlejava;

import com.littlejava.model.*;
import com.littlejava.view.ListViewer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {

    // 本地储存新闻内容，如何在终端显示

    // 1. 抽象出 对象
    // 2. 设计 对象应该具有的属性，状态和行为
    // 3. 思考 对象之间的交互
    // 4. 开始写代码

    public static void main(String[] args) throws Exception {

        // 广度优先搜索

        Queue<NewsWithRelated> newsQueue = new LinkedList<NewsWithRelated>();

        String startUrl = "https://readhub.cn/topic/7Qo4dv2vNre";

        NewsWithRelated startNews = UrlNewsReader.read(startUrl);

        newsQueue.add(startNews);

        while (!newsQueue.isEmpty()) {
            NewsWithRelated current = newsQueue.poll();
            current.display();
            for (Map.Entry<String, String> entry: current.getRelated().entrySet()) {
                String url = entry.getValue();
                NewsWithRelated next = UrlNewsReader.read(url);
                newsQueue.add(next);
            }
        }

        // 要求
        // 1. 实现在广度优先搜索过程中进行去重处理
        // 2. 实现把爬去的内容以json格式储存到本地

    }
}
