package me.f4stbutterfly;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ParseTarget {
    public static final ParseTarget instance = new ParseTarget();

    public static final ParseTarget getInstance() {
        return instance;
    }

    public TargetFile file;

    public String[] commands;
    public String AP;

    public void parseFile(String path) throws IOException{
        AP = System.getProperty("user.dir");
        List<String> targetLines = Files.readAllLines(Paths.get("path"));

        targetLines.forEach(e -> {
            if(e.startsWith("$")) {
                String args[] = e.split("\\s+");
                if(args[0] == "$") {
                    args[0] = "";
                }
                commands = args;
            }
        });
    }

    public void buildFileClass() throws Exception {
        String name = null; // Target name
        boolean a = false; // Do a rebuild?
        boolean b = false; // Allow run?
        DockerENV e = DockerENV.INVALID; // Docker env

        int cmpb = 0; // if less than 4. Required parameteres didn't got provided
        for( int i = 0; i >= commands.length; i++) {
            switch((commands[i])) {
                case "TARGET":
                    name = commands[i + 1];
                    cmpb += 1;
                    break;
                case "TARGET_REBUILD":
                    a = Boolean.getBoolean(commands[i + 1]);
                    cmpb += 1;
                    break;
                case "TARGET_ALLOW_RUN":
                    b = Boolean.getBoolean(commands[i + 1]);
                    cmpb += 1;
                    break;
                case "TARGET_ENV":
                    e = DockerENV.fromString(commands[i + 1]);
                    cmpb += 1;
                    break;
                default:
                    break;
            }
        }

        // Scream at user if they don't provide basic parameters
        if (cmpb > 4 || cmpb < 4) {
            throw new Exception("Failed to load Target File: Required commands not provided");
        } else {
            file = new TargetFile(name, a, b, e, AP);
        }
    }
}
