import java.util.Scanner;
import java.util.Stack;

public class Makanan {
    Stack<String> menumenu;
    Makanan(){
        menumenu = new Stack<String>();
    }
    public String tambahMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukan nama makanan = ");
        String nama_makanan = scanner.nextLine();
        if(nama_makanan.equals("selesai")) {
            return nama_makanan;
        }
        this.menumenu.push(nama_makanan);
        return  nama_makanan;
    }
    public void deletemenu() {
        String menuhabis = this.menumenu.pop();
        System.out.println(menuhabis);
    }
    public void cariMenu(String nama_makanan) {
        Integer pos = (Integer) menumenu.search(nama_makanan);
        if(pos == -1) {
            System.out.println(nama_makanan );
            return;
        }
        System.out.println(nama_makanan );
    }
    public boolean daftarmenu() {
        if(this.menumenu.empty()) {
            System.out.println("menu masih kosong (EMPTY)");
            return false;
        }
        System.out.println(this.menumenu.size());
        return true;
    }

}
