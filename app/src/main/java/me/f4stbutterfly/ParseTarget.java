package me.f4stbutterfly;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParseTarget {
    public static final ParseTarget instance = new ParseTarget();

    public static final ParseTarget getInstance() {
        return instance;
    }

    public TargetFile file;

    public List<String> commands = new ArrayList<>();
    public String AP;

    public void parseFile(String path) throws IOException{
        AP = Paths.get(path).toAbsolutePath().getParent().toString();
        List<String> targetLines = Files.readAllLines(Paths.get(path));

        targetLines.forEach(e -> {
            String args[] = e.split("\\s+");
            for (String b : args) {
                commands.add(b);
            }
        });
    }

    public void buildFileClass() throws Exception {
        String name = ""; // Target name
        boolean a = false; // Do a rebuild?
        boolean b = false; // Allow run?
        DockerENV e = DockerENV.INVALID; // Docker env

        int cmpb = 0; // if less than 4. Required parameteres didn't got provided
        for( int i = 0; i < commands.size(); i++) {
            String cmd = commands.get(i);
            switch(cmd) {
                case "TARGET":
                    if (i + 1 < commands.size()) {
                        name = commands.get(i + 1);
                        cmpb++;
                    }
                    i++;
                    break;
                case "TARGET_REBUILD":
                    if (i + 1 < commands.size()) {
                        a = Boolean.parseBoolean(commands.get(i + 1));
                        cmpb++;
                    }
                    i++;
                    break;
                case "TARGET_ALLOW_RUN":
                    if (i + 1 < commands.size()) {
                        b = Boolean.parseBoolean(commands.get(i + 1));
                        cmpb++;
                    }
                    i++;
                    break;
                case "TARGET_ENV":
                    if (i + 1 < commands.size()) {
                        e = DockerENV.fromString(commands.get(i + 1));
                        cmpb++;
                    }
                    i++;
                    break;
                default:
                    break;
            }
        }
        //commands.forEach(str -> {
        //    switch(str) {
        //       case "TARGET":
        //            name = commands.get(commands.indexOf(str) + 1);
        //            break;
        //        case "TARGET_REBUILD":
        //            a = Boolean.getBoolean(commands.get(commands.indexOf(str) + 1));
        //            break;
        //        case "TARGET_ALLOW_RUN":
        //            b = Boolean.getBoolean(commands.get(commands.indexOf(str) + 1));
        //            break;
        //        case "TARGET_ENV":
        //            e = DockerENV.fromString(commands.get(commands.indexOf(str) + 1));
        //           break;
        //        default:
        //            break;
        //    }
        //});

        // Scream at user if they don't provide basic parameters
        if (cmpb > 4 || cmpb < 4) {
            throw new Exception("Failed to load Target File: Required commands not provided");
        } else {
            file = new TargetFile(name, a, b, e, AP, commands);
        }
    }
}
