
package me.f4stbutterfly;

public class App {

    // Builder entry point
    public static void Main(String[] args) {
        if(args.length > 2 || args.length < 2) {
            System.err.println("Invalid parameters! Check docks for usage!");
            throw new IllegalArgumentException();
        }

        switch(args[0]) {
            case "build":
                break;
            default:
                System.err.println("Invalid parameters! Check docks for usage!");
                throw new IllegalArgumentException();
        }
    }
}
