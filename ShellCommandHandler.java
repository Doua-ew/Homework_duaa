import java.io.File;
import java.io.IOException;

public class ShellCommandHandler {
    private File currentDirectory;

//==================== Initialize currentDirectory to the user's current working directory
    public ShellCommandHandler() {
        currentDirectory = new File(System.getProperty("user.dir"));
    }
//====================Return the current directory
    public File getCurrentDirectory() {
        return currentDirectory;
    }
//====================print(Print the absolute path of the current directory)
    public void printWorkingDirectory() {
        System.out.println(currentDirectory.getAbsolutePath());
    }
//====================listDirectory(List all files and directories in the current directory)
    public void listDirectory() {
        File[] files = currentDirectory.listFiles();

        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    //For directories, prefix with "[DIR] "
                    System.out.println("[DIR]  " + file.getName());
                } else if (file.isFile()) {
                    // For files, prefix with "[FILE]
                    System.out.println("[FILE] " + file.getName());
                }
            }
        } else {
            System.out.println("The folder is empty.");
        }
    }
//=====================changeDirectory(Implement the change directory command)
public void changeDirectory(String argument) {
    if (argument == null) {
        System.out.println("Usage: cd [directory_name]");
        return;
    }

    if (argument.equals("..")) {
        File parent = currentDirectory.getParentFile();
        //If name is null, show usage message: "Usage: cd [directory_name]"
        if (parent != null && parent.exists()) {
            currentDirectory = parent;
        } else {
            System.out.println("No parent directory");
        }
        //If name is "..", move to parent directory if possible
    } else {
        File newfile = new File(currentDirectory, argument);
        if (newfile.exists() && newfile.isDirectory()) {
            currentDirectory = newfile;

         // move to the specified directory
        } else {
            System.out.println("Directory not found: " + argument);
        }
    }
}
//=======================makeDirectory(Implement the make directory command)
public void makeDirectory(String argument) {
// If name is null, show usage message: "Usage: mkdir [directory_name]"
    if (argument == null) {
        System.out.println("Usage: mkdir [directory_name]");
        return;
    }

    File new_one = new File(currentDirectory, argument);
// If directory already exists, print: "Directory already exists."
    if (new_one.exists()) {
        System.out.println("Directory already exists.");
// If directory creation is successful, print: "Directory created: [name]"
    } else {
        if (new_one.mkdir()) {
            System.out.println("Directory created: " + argument);
// If directory creation fails, print: "Failed to create directory."
        } else {
            System.out.println("Failed to create directory.");
        }
    }
}
//=========================createFile( Implement the create file command)
public void createFile(String name) {
// If name is null, show usage message: "Usage: touch [file_name]"
    if (name == null) {
        System.out.println("Usage: touch [file_name]");
        return;
    }

    File newFile = new File(currentDirectory, name);

    if (newFile.exists()) {
        System.out.println("File already exists.");
    } else {
// If file creation is successful, print: "File created: [name]"
        try {
            if (newFile.createNewFile()) {
                System.out.println("File created: " + name);
            } else {
                System.out.println("Failed to create file.");
            }
        } catch (IOException e) {
// If file creation fails, print error message with exception details
            System.err.println("Error creating file: " + e.getMessage());
        }
    }
}
//===========================printHelp(help command to print information about all available commands
// List all commands with their descriptions)
public void printHelp() {
    System.out.println("you need to handle the following commands:");
    System.out.println("  pwd- Print working directory");
    System.out.println("  ls- List files in current directory");
    System.out.println("  cd - Change directory");
    System.out.println("  mkdir - Create new directory");
    System.out.println("  touch - Create a new file");
    System.out.println("  help - Show help information");
    System.out.println("  exit- Exit the program");
}
//==========================






































}
