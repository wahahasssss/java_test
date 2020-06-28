package com.hdu.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/11
 * @Time 下午2:39
 */
public class HBaseTest {
    public static Configuration configuration ;
    static {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "master");
        configuration.set("hbase.master", "master:9000");

    }

    public static void createTable(TableName tableName){
        System.out.println("begin create table ..........");
        try {
            HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);
            if (hBaseAdmin.tableExists(tableName)){
                hBaseAdmin.disableTable(tableName);
                hBaseAdmin.deleteTable(tableName);
               System.out.println(String.format("table %s is deleted",tableName.toString()));
            }
            HTableDescriptor descriptor = new HTableDescriptor(tableName);
            descriptor.addFamily(new HColumnDescriptor("column1"));
            descriptor.addFamily(new HColumnDescriptor("column2"));
            descriptor.addFamily(new HColumnDescriptor("column3"));
            hBaseAdmin.createTable(descriptor);

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("end create table ......");
    }


    public static void insertData(TableName tableName){
        System.out.println("begin insert into tale ......");
        HTablePool pool = new HTablePool(configuration,1000);
        Table table = pool.getTable(tableName.getNameAsString());
        Put put = new Put("111222333aaabbbccc".getBytes());
        put.addColumn("column1".getBytes(),null,"one".getBytes());
        put.addColumn("column2".getBytes(),null,"two".getBytes());
        put.addColumn("column3".getBytes(),null,"three".getBytes());
        try {
            table.put(put);
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("end insert table data...");
    }



    public static void dropTable(TableName tableName){
      try {
          HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);
          hBaseAdmin.disableTable(tableName);
          hBaseAdmin.deleteTable(tableName);
      }catch (Exception e){
          e.printStackTrace();
      }
    }

    public static void main(String[] args){
        TableName tableName = TableName.valueOf("test_ssf");
        createTable(tableName);
//        insertData(tableName);
    }
}
