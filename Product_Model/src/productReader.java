import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class productReader
{

    public static void main(String[] args) 
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        
       try
       {
       File workingDirectory = new File(System.getProperty("user.dir"));
       chooser.setCurrentDirectory(workingDirectory);

       if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
       {
          selectedFile = chooser.getSelectedFile(); 
          Path file = selectedFile.toPath();

           InputStream in =
                   new BufferedInputStream(Files.newInputStream(file, CREATE));
           BufferedReader reader = 
                   new BufferedReader(new InputStreamReader(in));

           System.out.println("ID#     Name        Description                 Cost");
           System.out.print("======================================================");

           int line = 0;

           while(reader.ready())
           {

               rec = reader.readLine();
               String[] splitString = rec.split(", ");

               System.out.printf("\n%-7s %-11s %-26s %-6s", splitString[0], splitString[1], splitString[2], splitString[3]);

               line++;

           }
           reader.close();
           System.out.println("\n\nData file read!");
            }
       }
       catch (FileNotFoundException e)
       {
           System.out.println("File not found!!!");
           e.printStackTrace();
       }
       catch (IOException e)
       {
           e.printStackTrace();
       }
    }
    
}
