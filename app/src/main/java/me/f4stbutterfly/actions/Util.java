package me.f4stbutterfly.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
}
