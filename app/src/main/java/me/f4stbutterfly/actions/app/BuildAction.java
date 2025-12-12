package me.f4stbutterfly.actions.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import me.f4stbutterfly.TargetFile;
import me.f4stbutterfly.actions.Action;
import me.f4stbutterfly.pkg.BaseType;

public class BuildAction extends Action {

    public BuildAction(TargetFile f) {
        super(0, f);
    }

    private List<String> instru = new ArrayList<String>();

    @Override
    public void start(List<BaseType> a) throws IOException {

        instru.add("FROM " + this.File.env);
        instru.add("RUN apt-get update && apt-get upgrade" + this.File.env);

        for(int i=0; i < a.size(); i++) {
            switch(a.get(i).name) {
                case "TARGET_ENV_APT":
                    instru.add("RUN apt-get install " + a.get(i).args[0] + " -y");
                    break;
                case "TARGET_ENV_COPY":
                    instru.add("COPY " + a.get(i).args[0] + " " + a.get(i).args[1]);
                    break;
                case "TARGET_ENV_INITFILE":
                    instru.add("CMD [ \" " + a.get(i).args[0] + " \" ]");
                    break;
                default:
                    break;
            }
        };

        Files.write(Paths.get(File.absolutePath + "/dockerfile"), instru);

        super.start(a);
    }
}
