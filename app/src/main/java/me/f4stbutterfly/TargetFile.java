package me.f4stbutterfly;

public class TargetFile {
    public final String name;
    public final boolean doRebuild;
    public final boolean allowRun;
    public final DockerENV env;
    public final String absolutePath;

    public TargetFile(String n, boolean dRb, boolean aR, DockerENV e, String aP) {
        this.name = n;
        this.doRebuild = dRb;
        this.allowRun = aR;
        this.env = e;
        this.absolutePath = aP;
    }
}
