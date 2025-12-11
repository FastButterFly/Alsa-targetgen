package me.f4stbutterfly;

public enum DockerENV {

    ubuntu_22("ubuntu:22.04"),
    ubuntu_24("ubuntu:24.04"),
    INVALID("INVALID_ENV");

    private final String dockerName;

    private DockerENV(String a) {
        this.dockerName = a;
    }

    public final String getDockerString() {
        return this.dockerName;
 
    }

    public final static DockerENV fromString(String env) {
        for (DockerENV e : DockerENV.values()) {
            if(e.name().equalsIgnoreCase(env)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Invalid docker ENV");
    }
}
