import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Makanan food = new Makanan();
        while (true) {
            String nama_makanan = food.tambahMenu();
            if(nama_makanan.equals("selesai")) {
                break;
            }
        }
        if(!food.daftarmenu()){
            return;
        }
        food.deletemenu();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Masukan nama makanan yang habis : ");
            String nama_makanan = scanner.nextLine();
            if(nama_makanan.equals("selesai")) {
                break;
            }
            food.cariMenu(nama_makanan);
        }
    }
}