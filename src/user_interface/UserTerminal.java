package user_interface;


import cmd.FileDownloader;
import src.App;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class UserTerminal implements UserInteractor{
    private Scanner sc;

    public UserTerminal(){
        this.sc = new Scanner(System.in);
    }

    @Override
    public void print(String value) throws UserInteractorPrintException{
        System.out.println(">> " + value);
    }

    @Override
    public String[] readCommand() throws UserInteractorReadException, UserInteractorPrintException {
        System.out.print("<< ");
        String command = sc.nextLine();
        print("You entered the command: \u001B[31m" + command + "\u001B[0m");

        return command.split(" ");
    }

    public int readInt() throws UserInteractorPrintException {
        System.out.print("<< ");
        int command = sc.nextInt();
        App.terminal.getScanner().nextLine();
        print("You entered the number: \u001B[31m" + command + "\u001B[0m");

        return command;
    }

    public String[] whileRead() throws UserInteractorReadException, UserInteractorPrintException, IOException, InterruptedException {
        App.terminal.print("Enter command and parameters: ");
        String[] command = readCommand();

        if(command.length < 1){
            return null;
        }
        String cmd = command[0];
        if(command[0].equals("exit")){
            return null;
        }
        switch (cmd){
            case "exit":
                return null;
            case "download":
                FileDownloader fd;
                if(command.length > 2){
                    fd = new FileDownloader(command[1], command[2]);
                }else {
                    fd = new FileDownloader(command[1]);
                }
                Thread downloadThread = new Thread(fd);

                downloadThread.start();

                break;
            case "info":
                App.downloadInfo.getInfo();
                break;
            default:
                break;
        }

        whileRead();


        return command;
    }

    public Scanner getScanner(){
        return this.sc;
    }
}
