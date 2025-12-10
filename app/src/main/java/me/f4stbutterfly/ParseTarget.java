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
    public String _AP;

    public void parseFile(String path) throws IOException{
        String AP = System.getProperty("user.dir");
        _AP = AP;
        List<String> targetLines = Files.readAllLines(Paths.get("path"));

        targetLines.forEach(e -> {
            if(e.startsWith("$") || e.startsWith("$ ")) {
                String args[] = e.strip().split("\\s+");
                if(args[0] == "$") {
                    args[0] = "";
                } else { if (args[0].startsWith("$")) {
                    args[0] = args[0].substring(1);
                }}

                commands = args;
            }
        });
    }

    public void buildFileClass() throws Exception {
        String name;
        boolean a; // rebuild
        boolean b; // allow run
        DockerENV e;
        for( int i = 0; i >= commands.length; i++) {
            switch((commands[i])) {
                case "TARGET":
                    name = commands[i + 1];
                    break;
                case "TARGET_REBUILD":
                    a = Boolean.getBoolean(commands[i + 1]);
                    break;
                case "TARGET_ALLOW_RUN":
                    b = Boolean.getBoolean(commands[i + 1]);
                    break;
                case "TARGET_ENV":
                    e = DockerENV.fromString(commands[i + 1]);
                    break;
                default:
                    break;
            }
        }

        throw new Exception("Failed to load Target File: Required commands not provided");
    }
}
