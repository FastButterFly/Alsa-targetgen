
package me.f4stbutterfly;

import me.f4stbutterfly.actions.app.BuildAction;

public class App {
    // Builder entry point
    public static void main(String[] args) throws Exception {
        
        if(args.length > 2 || args.length < 2) {
            System.err.println("Invalid parameters! Check docks for usage!");
            throw new IllegalArgumentException();
        }

        switch(args[0]) {
            case "build":
                ParseTarget.getInstance().parseFile(args[1] + "/index.json");
                BuildAction action = new BuildAction(ParseTarget.getInstance().file);
                action._execute(ParseTarget.getInstance().type);
                break;
            /*case "compose":
                ParseTarget.getInstance().parseFile(args[1] + "/index.json");
                ComposeAction action2 = new ComposeAction(ParseTarget.getInstance().file);
                action2._execute(ParseTarget.getInstance().type);
                break; */
            default:
                System.err.println("Invalid parameters! Check docks for usage!");
                throw new IllegalArgumentException();
        }
    }
}
