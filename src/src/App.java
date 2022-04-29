package src;

import cmd.DownloadInformation;
import cmd.FileDownloader;
import user_interface.*;

import java.io.IOException;
import java.util.ArrayList;


public class App extends Application{

    public static final String DOWNLOAD_PATH = "C:\\Users\\User\\Downloads\\simple-downloader";
    public static UserTerminal terminal;
    public static DownloadInformation downloadInfo;

    public static void main(String[] args) throws UserInteractorReadException, UserInteractorPrintException, IOException, InterruptedException {
        App app = new App(args);
    }

    public App(String[] args) throws UserInteractorReadException, UserInteractorPrintException, IOException, InterruptedException {
        super(args);
    }

    @Override
    public void init() {
        terminal = new UserTerminal();
        downloadInfo = new DownloadInformation();
    }

    @Override
    public void start() throws UserInteractorReadException, UserInteractorPrintException, IOException, InterruptedException {
        String[] res = terminal.whileRead();
    }

}