package me.f4stbutterfly.actions;

import java.io.IOException;

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
        this.start(File);
    }

    public void start(TargetFile f) throws IOException  {};
}
