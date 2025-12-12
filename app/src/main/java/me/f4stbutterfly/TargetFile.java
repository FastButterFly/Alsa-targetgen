package me.f4stbutterfly;

import java.util.List;

public class TargetFile {
    public final String name;
    public final boolean doRebuild;
    public final boolean allowRun;
    public final DockerENV env;
    public final String absolutePath;
    public final List<String> fileCommands;

    public TargetFile(String n, boolean dRb, boolean aR, DockerENV e, String aP, List<String> commmands) {
        this.name = n;
        this.doRebuild = dRb;
        this.allowRun = aR;
        this.env = e;
        this.absolutePath = aP;
        this.fileCommands = commmands;
    }
}
