package cmd;

import src.App;
import user_interface.UserInteractorPrintException;

import java.util.ArrayList;
import java.util.Iterator;

public class DownloadInformation {
    private ArrayList<FileDownloader> currentDownloadFiles;

    public DownloadInformation(){
        this.currentDownloadFiles = new ArrayList<>();
    }

    public void getInfo() throws UserInteractorPrintException {
        for (FileDownloader currentDownloadFile : this.currentDownloadFiles) {
            System.out.println(currentDownloadFiles);
            FileDownloader fd = currentDownloadFile;
            int percentDownloaded = fd.getDownloadedShare();
            App.terminal.print(percentDownloaded + "%");
            String str = "";
            for (int i = 0; i < percentDownloaded/2; i++){str += "▮";}
            for (int i = 0; i < 50-percentDownloaded/2; i++){str += "▯";}
            App.terminal.print(str);
        }
    }

    public void addDownloadFile(FileDownloader fd){
        this.currentDownloadFiles.add(fd);
    }

    public void deleteDownloadFile(FileDownloader fd){
        this.currentDownloadFiles.remove(fd);
    }
}
