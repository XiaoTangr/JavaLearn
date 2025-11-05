package cn.javat.javaLearn.experiment4.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AppUtils {
    //    打印单行分割线
    public static void printLine() {
        System.out.println("———————————————————————————————————————————————————————————————————");

    }

    //    打印双行分割线
    public static void printDoubleLine() {
        System.out.println("===================================================================");

    }

    // 无参数版本 - 实现可选参数效果
    public static void print(String msg) {
        System.out.println(msg);
    }

    // 有参数版本
    public static void print(String msg, Object... args) {
        if (args == null || args.length == 0) {
            System.out.println(msg);
        } else {
            System.out.printf(msg, args);
            System.out.println();
        }
    }

    public static void saveJsonFile(String json, String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(json);
            bw.flush();
        } catch (IOException e) {
            AppUtils.print("保存文件失败：path：%s", e.toString());
        }
    }

    public static void createPath(String s) {
//        创建文件夹
        try {
            java.io.File file = new java.io.File(s);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            AppUtils.print("创建文件夹失败：path：%s", e.toString());
        }
    }
}
