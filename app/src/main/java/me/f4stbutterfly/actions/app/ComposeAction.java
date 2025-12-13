package me.f4stbutterfly.actions.app;

import java.io.IOException;
import java.util.List;

import me.f4stbutterfly.TargetFile;
import me.f4stbutterfly.actions.Action;
import me.f4stbutterfly.pkg.BaseType;

public class ComposeAction extends Action {

    public ComposeAction(TargetFile f) {
        super(1, f);
    }

    @Override
    public void start(List<BaseType> a) throws IOException {
        String dockerImageName = "alsa-targetgen-target-";

        for(int i=0;i<a.size();i++) {
            switch(a.get(i).name) {
                case "TARGET":
                    dockerImageName = dockerImageName + a.get(i).args[0];
                    break;
                default:
                    break;
            }
        }

        String command = "docker build -t " + dockerImageName + ":latest" + File.absolutePath;

        Runtime.getRuntime().exec(command);

        super.start(a);
    }

}
