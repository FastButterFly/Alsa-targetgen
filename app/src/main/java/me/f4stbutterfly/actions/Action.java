package me.f4stbutterfly.actions;

import java.io.IOException;
import java.util.List;

import me.f4stbutterfly.TargetFile;
import me.f4stbutterfly.pkg.BaseType;

public class Action {
    public final int ID;
    public final TargetFile File;

    protected Action(int _id, TargetFile _file) {
        this.ID = _id;
        this.File = _file;
    }

    public void _execute(List<BaseType> bt) throws IOException {
        Util.getInstance().handleCREPEnforcment();
        this.start(bt);
    }

    public void start(List<BaseType> a) throws IOException  {};
}
