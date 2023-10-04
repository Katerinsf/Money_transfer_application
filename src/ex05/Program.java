package ex05;

import java.util.UUID;

public class  Program {
    public static void main(String[] args) {
        if(args.length != 1 || !args[0].startsWith("--profile=")) {
            System.err.println("--profile=dev or --profile=production");
            System.exit(-1);
        } else {
            String mode = args[0].replace("--profile=", "");
            if (mode.equals("dev") || mode.equals("production")) {
                Menu menu = new Menu(mode);
                menu.launch();
            } else {
                System.err.println("--profile=dev or --profile=production");
                System.exit(-1);
            }
        }

    }

}
