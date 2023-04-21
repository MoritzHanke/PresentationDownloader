import pathDecorator.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ConfigInterpreter {

    /* Format:
       1: Filename modifier1 modifier2 modifier3 .... URL
     */
    public final static File CONFIG_FILE = new File(".\\downloadConfig.cfg");
    public Scanner scanner;
    private int iteration = 0;

    public ConfigInterpreter() throws IOException {
        if (!CONFIG_FILE.exists()) {
            CONFIG_FILE.createNewFile();
        }
        scanner = new Scanner(CONFIG_FILE);
    }

    public boolean hasNext() {
        return scanner.hasNextLine();
    }

    public boolean readNext() {
        try {
            String line = scanner.nextLine();

            //get path
            int a = line.indexOf('"', 0);
            int b = line.indexOf('"', a+1);
            String path = line.substring(a+1, b);

            //split
            String[] args = line.substring(b+2).split(" ");
            if(args.length<1){
                System.out.println("missing URL");
                return false;
            }

            //build decor object
            PathModifier decorObject = new PathInterpreter();

            //extract modifier
            for (int i = 0; i < args.length-1; i++) {
                decorObject = toDecorObject(decorObject, args[i]);
            }

            //interpret filePath and urlString
            String filePath = decorObject.getFinalPath(path);
            String urlString = decorObject.getFinalPath(args[args.length - 1]);

            //downloadFile
            return FileDownloader.downloadFile(new URL(urlString), Paths.get(filePath));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void close() {
        scanner.close();
    }

    private PathModifier toDecorObject(PathModifier toDecor, String decorString){
        String[] decArr = decorString.split("~", 1);
        if(decArr[0].equals("DFor1"))
            return new DateFormat1Modifier(toDecor, decArr[1]);
        else if (decArr[0].equals("DFor2"))
            return new DateFormat2Modifier(toDecor, decArr[1]);
        else if (decArr[0].equals("DOffset"))
            return new DayOffsetModifier(toDecor, decArr[1]);
        else if (decArr[0].equals("WOffset"))
            return new WeekOffsetModifier(toDecor, decArr[1]);
        else if (decArr[0].equals("Holiday"))
            return new HolidayModifier(toDecor, decArr[1]);
        else if (decArr[0].equals("SDate"))
            return new StartDateModifier(toDecor, decArr[1]);
        else{
            System.out.println("couldn't interpret modifier: \"" + decorString + "\"");
            return toDecor;
        }

    }

}
