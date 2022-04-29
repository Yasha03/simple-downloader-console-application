package cmd;

import src.App;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDownloader implements Runnable{
    private URL fileURL;
    private String newName;
    private long fileSize;
    private long fileBytesDownloaded;

    public FileDownloader(String stringURL) throws IOException {
        this(stringURL, null);
    }

    public FileDownloader(String stringURL, String newName) throws IOException {
        this.fileURL = new URL(stringURL);
        this.newName = newName;
        this.fileSize = discoverSizeFile();
        this.fileBytesDownloaded = 0;
    }

    public boolean download() throws IOException {
        App.downloadInfo.addDownloadFile(this);
        String saveFileName = this.newName;
        if(this.newName == null){
            saveFileName = new File(String.valueOf(this.fileURL)).getName();
        }
        BufferedInputStream bis = new BufferedInputStream(this.fileURL.openStream());
        FileOutputStream fis = new FileOutputStream(App.DOWNLOAD_PATH + "\\" + saveFileName );

        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
            this.fileBytesDownloaded+= 1024;
        }
        this.fileBytesDownloaded = this.fileSize;
        App.downloadInfo.deleteDownloadFile(this);

        fis.close();
        bis.close();
        return true;
    }

    private long discoverSizeFile() throws IOException {
        URLConnection urlConnection = this.fileURL.openConnection();
        urlConnection.connect();
        return urlConnection.getContentLength();
    }

    public int getDownloadedShare(){
        int result = (int)(((double) this.fileBytesDownloaded / (double) this.fileSize) * 100);
        return result;
    }

    public long getFileSize(){
        return this.fileSize;
    }


    @Override
    public void run() {
        try {
            download();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
