import pathDecorator.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
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
            String[] args = scanner.nextLine().split(" ");
            if(args.length<2)
                return false;

            //build decor object
            PathModifier decorObject = new PathInterpreter();

            //extract modifier
            for (int i = 1; i < args.length-1; i++) {
                decorObject = toDecorObject(decorObject, args[i]);
            }

            //interpret filePath and urlString
            String filePath = decorObject.getFinalPath(args[0]);
            String urlString = decorObject.getFinalPath(args[args.length - 1]);

            //downloadFile
            return FileDownloader.downloadFile(new URL(urlString), Path.of(filePath));
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
        else if (decArr[0].equals("Hold"))
            return new HolidayModifier(toDecor, decArr[1]);
        else if (decArr[0].equals("SDate"))
            return new StartDateModifier(toDecor, decArr[1]);
        else
            return toDecor;

    }

}
