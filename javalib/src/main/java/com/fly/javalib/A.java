package com.fly.javalib;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Fj on 2018/4/16.
 */

public class A {

    public static void main(String[] args) {
        try {
            //http://192.168.1.155    smart.test.com
            URL url = new URL("http://192.168.1.155/api/charts/wall_data");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Host","smart.test.com");
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write("wall=6636".getBytes());
            outputStream.flush();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream = httpURLConnection.getInputStream();
                String s = streamToString(inputStream);
                System.out.println("args = [" + s + "]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String streamToString(InputStream is) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            int availableLength = is.available();
            int count;
            if(availableLength < 32)
                availableLength = 32;
            final byte[] data = new byte[availableLength];
            while ((count = is.read(data)) > 0) {
                os.write(data, 0, count);
            }
            return new String(os.toByteArray(),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } finally {
            close(os);
            close(is);
        }
        return "";
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}
