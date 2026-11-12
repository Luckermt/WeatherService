package com.luckermt.service;

import jakarta.json.Json;
import jakarta.json.JsonStructure;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.StorageLevels;
import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.SparkLauncher;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.stereotype.Service;
import jakarta.json.JsonReader;
import scala.Tuple2;

import java.io.IOException;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

import static org.apache.hadoop.shaded.org.apache.commons.lang3.StringUtils.SPACE;

@Service
@SuppressWarnings("deprecation")
public class WeatherService {

    // Create a local StreamingContext with two working thread and batch interval of 1 second
//    SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount");
//    JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(1000));
//    JavaReceiverInputDStream<String> lines = jssc.socketTextStream("spark.yaml-master", 7077);
    // Create a DStream that will connect to hostname:port, like localhost:9999

    public static void sendWithInterval() throws IOException {
        SparkSession spark = SparkSession.builder()
                .appName("WeatherService")
                .master("spark://spark-master:7077")
                .getOrCreate();
        spark.addArtifact("/build/data");

    }


    static File file = new File("src/main/resources/data.json");


    public static String parseJson() {

        JsonStructure jsonStructure = null;
        try{

            JsonReader reader = Json.createReader(new FileReader(file));
            jsonStructure = reader.read();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
        return  jsonStructure.toString();
    }


    public static String sendJson() throws FileNotFoundException {

        return parseJson();
    }

}
