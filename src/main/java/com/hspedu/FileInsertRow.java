// package com.hspedu;
//
// import java.io.*;
//
// /**
//  * 给文件增加一行数据。
//  *
//  * @
//  */
//
// public class FileInsertRow {
//
//     public static void main(String args[]) {
//
//         try {
//
//             FileInsertRow j = new FileInsertRow();
//
//             j.insertStringInFile(new File(args[0]), Integer.parseInt(args[1]), args[2]);
//
//         } catch (Exception e) {
//
//             e.printStackTrace();
//
//         }
//
//     }
//
//     /**
//      * 在文件里面的指定行插入一行数据
//      *
//      * @param inFile           　　　　　文件
//      * @param lineno           　　　　　行号
//      * @param lineToBeInserted 　　　　　要插入的数据
//      * @throws Exception 　　　　　 IO操作引发的异常
//      */
//
//     public void insertStringInFile(File inFile, int lineno, String lineToBeInserted)
//
//             throws Exception {
//
// // 临时文件
//
//         File utFile = File.createTempFile("name", ".tmp");
//
// // 输入
//
//         FileInputStream fis = new FileInputStream(inFile);
//
//         BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//
// // 输出
//
//         FileOutputStream fos = new FileOutputStream(outFile);
//
//         PrintWriter ut = new PrintWriter(fos);
//
// // 保存一行数据
//
//         String thisLine;
//
// // 行号从1开始
//
//         int i = 1;
//
//         while ((thisLine = in.readLine()) != null) {
//
// // 如果行号等于目标行，则输出要插入的数据
//
//             if (i == lineno) {
//
//                 out.println(lineToBeInserted);
//
//             }
//
// // 输出读取到的数据
//
//             out.println(thisLine);
//
// // 行号增加
//
//             i++;
//
//         }
//
//         out.flush();
//
//         out.close();
//
//         in.close();
//
// // 删除原始文件
//
//         inFile.delete();
//
// // 把临时文件改名为原文件名
//
//         outFile.renameTo(inFile);
//
//     }
//
// }