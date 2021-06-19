public class Driver {

    public static void main(String[] args) {
        try{
            MainSystem mainsystem = new MainSystem();
            mainsystem.mainMenu();
        }
        catch (Exception exc){
            System.out.println(exc.getMessage());
        }
    }
}
