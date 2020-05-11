package com.ant.lib;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * copyright：
 *
 * @author：anthui Version：1.0
 * creation date： 2020/4/20.
 * describe：
 */
public class JavaFileTest {


    public static void main(String[] args) {


        try {
            new FileTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static class FileTest {


        private void readApkFile(File file) throws IOException {
            long cu = System.currentTimeMillis();

            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[100];

            int len = 0;

            float position = 0;
            int available = fileInputStream.available();

            OutputStream outputStream = new FileOutputStream(createFile("lib/file/cop1.apk"));
            while ((len = fileInputStream.read(bytes)) != -1) {

                position += len;
                outputStream.write(bytes, 0, len);

//                int progress = (int) (position / available * 100);
//                System.out.println("progress==  " + progress);
            }

            fileInputStream.close();

            System.out.println("time ==  " + (System.currentTimeMillis() - cu) + "  size== " + file.length() / 1024);


        }


        private File createFile(String filePath) {

            File file = new File(filePath);
            if (file.exists() && file.isFile()) {

                file.delete();
            }

            return file;
        }

        private void readApkBufferFile(File file) throws IOException {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[100];

            int len = 0;

            float position = 0;
            int available = fileInputStream.available();
            long cu = System.currentTimeMillis();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            OutputStream outputStream = new FileOutputStream(createFile("lib/file/copy2.apk"));

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

            while ((len = bufferedInputStream.read(bytes)) != -1) {

                position += len;
                bufferedOutputStream.write(bytes, 0, len);
                int progress = (int) (position / available * 100);
//                System.out.println("progress==  " + progress);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            outputStream.close();
            bufferedInputStream.close();
            fileInputStream.close();
            System.out.println("time  2==  " + (System.currentTimeMillis() - cu) + "  size== " + file.length() / 1024);


        }


        public FileTest() throws IOException {


            File file = getFile("lib/file/bbz.apk");
            if (file == null) {
                return;
            }

//            redChar(file);
//            redByte(file);
//            readByte2(file);
//            readApkFile(file);
//            readApkBufferFile(file);

//            fileReader(file);
            streamToByte(file);
        }

        private void fileReader(File file) throws IOException {
            long currentTimeMillis = System.currentTimeMillis();

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String len = null;

//            bufferedReader.re
            while ((len = bufferedReader.readLine()) != null) {

                System.out.println("message== " + len);
            }

            long time = System.currentTimeMillis() - currentTimeMillis;
//            System.Out.println(" ");
            System.out.println(time);
//            int read = fileReader.read();

        }

        private void streamToByte(File file) throws IOException {
            long currentTimeMillis = System.currentTimeMillis();

            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(fileInputStream.available());

            BufferedInputStream inputStream = new BufferedInputStream(fileInputStream);
            byte[] bytes = new byte[1024 * 1024 * 8];
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, len);
            }
            byteArrayOutputStream.flush();

            byte[] bytes1 = byteArrayOutputStream.toByteArray();

            byteArrayOutputStream.close();
            inputStream.close();
            fileInputStream.close();

            String s = new String(bytes1);
            System.out.println("message====  " + "  cotent== \n" + s.toString() + "\n" + s.length() + "\n time-- " + (System.currentTimeMillis() - currentTimeMillis));
            //BufferedInputStream
        }


        private void readByte2(File file) throws IOException {


            FileInputStream fileInputStream = new FileInputStream(file);

            int len = 0;
            byte[] bytes = new byte[10];
            float progress = 0;
            int available = fileInputStream.available();
            while ((len = fileInputStream.read(bytes, 0, 10)) != -1) {
                progress += len;
                int pre = (int) (progress / available * 100);
                System.out.println("message===  " + new String(bytes, 0, len) + "   len == " + len + "  progress==  " + pre);

            }
        }

        private void redByte(File file) throws IOException {

            InputStream inputStream = new FileInputStream(file);

            byte[] bytes = new byte[3];

            int len = 0;
            long length = file.length();
            int available = inputStream.available();
            System.out.println("message===  " + "   length == " + length + "  available== " + available);
            float progress = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                progress += len;
                int pre = (int) (progress / available * 100);
                System.out.println("message===  " + new String(bytes, 0, len) + "   len == " + len + "  progress==  " + pre);
            }
        }


        private void redChar(File file) {

            try {
                InputStream inputStream = new FileInputStream(file);

                int len = 0;
                int available = inputStream.available();
                float progress = 0.0f;
                long cu = System.currentTimeMillis();
                while ((len = inputStream.read()) != -1) {
                    char message = (char) len;
                    progress += 1;
                    float v = progress / available * 100;
                    System.out.println("messag======" + v);
                }
                System.out.println("message==============  close" + (System.currentTimeMillis() - cu));
                inputStream.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        File getFile(String path) {
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                return file;
            }
            return null;
        }
    }


}
