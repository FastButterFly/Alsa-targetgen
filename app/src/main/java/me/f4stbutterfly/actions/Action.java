package me.f4stbutterfly.actions;

import java.io.IOException;
import java.util.List;

import me.f4stbutterfly.TargetFile;

public class Action {
    public final int ID;
    public final TargetFile File;

    protected Action(int _id, TargetFile _file) {
        this.ID = _id;
        this.File = _file;
    }

    public void _execute() throws IOException {
        Util.getInstance().handleCREPEnforcment();
        this.start(File.fileCommands);
    }

    public void start(List<String> a) throws IOException  {};
}
