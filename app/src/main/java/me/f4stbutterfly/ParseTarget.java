package me.f4stbutterfly;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import me.f4stbutterfly.pkg.BaseType;

public class ParseTarget {
    public static final ParseTarget instance = new ParseTarget();

    public static final ParseTarget getInstance() {
        return instance;
    }

    public TargetFile file;

    public List<BaseType> type = new ArrayList<>();
    public String AP;
    public Gson gson = new Gson();

    public void parseFile(String path) throws Exception {
        AP = Paths.get(path).toAbsolutePath().getParent().toString();
        Collection<BaseType> types = new ArrayList<>();
        List<String> content = new ArrayList<>();
        content.addAll(Files.readAllLines(Paths.get(path)));
        TypeToken<Collection<BaseType>> collectionType = new TypeToken<Collection<BaseType>>(){};
        String asdadsdas = content.toString().strip();
        if(asdadsdas.startsWith("[[") && asdadsdas.endsWith("]]")) {
            asdadsdas = asdadsdas.substring(1, asdadsdas.length() - 1);
        }
        System.out.println(asdadsdas);
        types = gson.fromJson(content.toString().strip(), collectionType);
        type.addAll(types);

        String name = ""; // Target name
        boolean a = false; // Do a rebuild?
        boolean b = false; // Allow run?
        DockerENV e = DockerENV.INVALID; // Docker env

        int cmpb = 0; // if less than 4. Required parameteres didn't got provided

        for(int i=0; i <= types.size(); i++) {
            switch(type.get(i).name) {
                case "TARGET":
                    name = type.get(i).args[0];
                    cmpb += 1;
                    break;
                case "TARGET_ENV":
                    e = DockerENV.fromString(type.get(i).args[0]);
                    cmpb += 1;
                    break;
                case "TARGET_REBUILD":
                    a = Boolean.parseBoolean(type.get(i).args[0]);
                    cmpb += 1;
                    break;
                case "TARGET_ALLOW_RUN":
                    a = Boolean.parseBoolean(type.get(i).args[0]);
                    cmpb += 1;
                    break;
                case "TARGET_ENV_SETWORKFOLDER":
                    b = Boolean.parseBoolean(type.get(i).args[0]);
                    cmpb += 1;
                    break;
                default:
                    break;
            }
        };

        // Scream at user if they don't provide basic parameters
        if (cmpb > 4 || cmpb < 4) {
            throw new Exception("Failed to load Target File: Required commands not provided");
        } else {
            file = new TargetFile(name, a, b, e, AP);
        }        
    }
}
