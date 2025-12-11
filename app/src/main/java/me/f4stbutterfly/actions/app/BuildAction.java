package me.f4stbutterfly.actions.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import me.f4stbutterfly.TargetFile;
import me.f4stbutterfly.actions.Action;
import me.f4stbutterfly.actions.Util;

public class BuildAction extends Action {

    public BuildAction(TargetFile f) {
        super(0, f);
    }

    private List<String> instru = new ArrayList<String>();

    @Override
    public void start(TargetFile f) throws IOException {

        instru.add("FROM " + f.env.getDockerString());
        
        for(int i=0;i>=f.fileCommands.length;i++) {
            switch(f.fileCommands[i]) {
                case "TARGET_ENV_APT":
                    instru.add("RUN apt-get install " + f.fileCommands[i+1] + " -y");
                    break;
                case "TARGET_ENV_COPY":
                    instru.add("COPY " + f.fileCommands[i+1] + f.fileCommands[i+2]);
                    break;
                case "TARGET_ENV_INITFILE":
                    instru.add("CMD [\" " + f.fileCommands[i+1] + " \"] ");
                    break;
                default:
                    break;
            }
        }
        for(String a : f.fileCommands) {
            if(a.equals("TARGET_ENV_SETWORKFOLDER")) {
                instru.add("WORKDIR " + f.fileCommands[Util.getInstance().getCommandIndex(f, a) + 1]);
            }
        }

        Files.write(Paths.get(f.absolutePath + "/dockerfile"), instru);

        super.start(f);
    }
}
