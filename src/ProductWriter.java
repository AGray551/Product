import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args)
    {
        ArrayList<Product> products = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.csv");

        boolean done = false;

        do {
            String ID = SafeInput.getNonZeroLenString(in,"Enter the ID [6 digits]: ");
            String Name = SafeInput.getNonZeroLenString(in,"Enter the name: ");
            String Description = SafeInput.getNonZeroLenString(in,"Enter the description: ");
            double Cost = SafeInput.getDouble(in, "Enter the cost: ");

            Product product = new Product(ID, Name, Description, Cost);
            products.add(product);

            done = SafeInput.getYNConfirm(in, "Are you done?" );

        }while(!done);

        for(Product p: products)
            System.out.println(p);

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(Product product : products)
            {
                String csvRecord = product.toCSVRecord();
                writer.write(csvRecord, 0, csvRecord.length());
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}