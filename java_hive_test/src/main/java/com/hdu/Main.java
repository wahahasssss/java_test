package com.hdu;

import java.util.*;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/5/11
 * @Time 上午11:23
 */
public class Main {
    public static void main(String[] args){
        List<String> strings = Arrays.asList("duobei0","duobei1","duobei2");
        HashMap<String,Integer> shards = new HashMap<>();
        int result = 1000 / 103;
        for (int i = 0;i< 1498;i++){
            String shardName = strings.get(i/500);
            if (!shards.containsKey(shardName)){
                shards.put(shardName,0);
            }
            int tmp = shards.get(shardName);
            tmp ++ ;
            shards.replace(shardName,tmp);
        }
        System.out.println(".......");
    }

    class Entity{
        private Integer id;
        private String shard;

        public Entity() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getShard() {
            return shard;
        }

        public void setShard(String shard) {
            this.shard = shard;
        }
    }
}
