package me.f4stbutterfly.actions.app;

import java.io.IOException;
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
        
        /* for(int i=0;i>=f.fileCommands.size();i++) {
            String cmd = f.fileCommands.get(i);
            switch(cmd) {
                case "TARGET_ENV_APT":
                    if (i + 1 < f.fileCommands.size()) {
                        instru.add("RUN apt-get install " + f.fileCommands.get(i + 1) + " -y");
                    }
                    //i++;
                    break;
                case "TARGET_ENV_COPY":
                    if (i + 1 < f.fileCommands.size() && i + 2 < f.fileCommands.size()) {
                        instru.add("COPY " + f.fileCommands.get(i + 1) + " " +f.fileCommands.get(i + 2) );
                    }
                    //i++;
                    break;
                case "TARGET_ENV_INITFILE":
                    if (i + 1 < f.fileCommands.size()) {
                        instru.add("CMD [\" " + f.fileCommands.get(i + 1) + " \" ]" );
                    }
                    //i++;
                    //instru.add("CMD [\" " + f.fileCommands[i+1] + " \"] ");
                    break;
                case "TARGET_ENV_SETWORKFOLDER":
                    if (i + 1 < f.fileCommands.size()) {
                        instru.add("WORKDIR" + f.fileCommands.get(i + 1) );
                    }
                    //i++;
                default:
                    break;
            }
        }

        Files.write(Paths.get(f.absolutePath + "/dockerfile"), instru);

        super.start(f); */
    }
}
