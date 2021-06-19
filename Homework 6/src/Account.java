import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Account class (Trader and Customer classes inherit Account)
 */
public class Account {
    String id;
    String name;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /***
     * Sets the name automatically
     * @throws FileNotFoundException
     */
    protected void setName() throws FileNotFoundException {
        File file = new File("users.txt");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            String line = scanner.nextLine();
            List<String> arr = Arrays.asList(line.split(","));
            if(arr.get(0).equals(id)){
                this.name = arr.get(2);
            }
        }
    }

}
