import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductGenerator {
    public static void main(String[] args) {
        ArrayList<String> folks = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\productTestData.txt");

        boolean done = false;

        String personRec = "";
        String ID = "";
        String Name = "";
        String Description = "";

        double Cost = 0;

        do{
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]");
            Name = SafeInput.getNonZeroLenString(in, "Enter the name");
            Description = SafeInput.getNonZeroLenString(in, "Enter the description");

            Cost = SafeInput.getRangedDouble(in, "Enter the cost", 0,99999);

            personRec = ID + ", " + Name + ", " + Description + ", " + Cost;
            folks.add(personRec);

            done = SafeInput.getYNConfirm(in, "Are you done?");

        } while (!done);

        for(String p: folks)
            System.out.println(p);

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for(String rec : folks)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }

            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}