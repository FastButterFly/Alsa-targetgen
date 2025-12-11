package me.f4stbutterfly.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import me.f4stbutterfly.TargetFile;

public class Util {
    private static final Util instance = new Util();
    public static final Util getInstance() {
        return instance;
    }

    public void handleCREPEnforcment() throws IOException {
        if(this._isUserRoot()) {
            throw new IllegalStateException("Failed to enforce CREP (Counter root execution policy). Runned from root!");
        }
    }

    // If on linux check if user is root. on windows do not check anything
    public boolean _isUserRoot() throws IOException {
        String OS = System.getProperty("os.name").toLowerCase();
        if(OS.contains("win")) return false; // Windows
        if(OS.contains("nux") || OS.contains("nix") || OS.contains("aix")) {
            // Check UID using id -u
            Process prc = Runtime.getRuntime().exec("id -u");
            BufferedReader reader = new BufferedReader(new InputStreamReader(prc.getInputStream()));
            String line = reader.readLine();
            return "0".equals(line);
        } else {
            throw new IllegalStateException("Failed to enforce CREP (Counter root execution policy). Unknown OS");
        }
    }

    public boolean containsCommand(TargetFile f, String command) {
        for (String a : f.fileCommands) {
            if(a.equals(command)) return true;
        }

        return false;
    }

    public int getCommandIndex(TargetFile f, String command) {
        for (int i=0; i >= f.fileCommands.length; i++) {
            if(f.fileCommands[i].equals(command)) return i;
        }

        throw new IllegalAccessError("Parser tried to access non-existing param. Should never happen! Please report this.");
    }
}
