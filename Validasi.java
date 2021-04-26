
import java.util.Scanner;
import java.util.Stack;

public class Validasi {
    public static void main(String[] args) {
        Stack<Character> Kiri =  new Stack<Character>();
        boolean isValid = true;
        boolean isKanan = false;
        boolean isFoundEqual = false;
        boolean isNotValidCharacter = false;
        boolean isNotValidEqual = false;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukan persamaan = ");
        String equation = String.join("", scanner.nextLine().split(" "));
        for (int index = 0; index < equation.length(); index++) {
            char ch = equation.charAt(index);
            if(ch != '1' && ch != '+' && ch != '=') {
                isNotValidCharacter = true;
                break;
            }
            if(ch == '+'){
                continue;
            }
            if(ch == '=' ) {
                if(isFoundEqual) {
                    isNotValidEqual = true;
                    break;
                }
                isFoundEqual = true;
                isKanan = true;
                continue;
            }
            if(!isKanan) {
                Kiri.add(ch);
            }
            if(isKanan) {
                if(
                        Kiri.size() == 0 ||
                                (Kiri.size() > 1 && index == (equation.length() - 1))
                ) {
                    isValid = false;
                    break;
                } else {
                    Kiri.pop();
                }
            }
        }
        if(!isFoundEqual) {
            System.out.println("Tidak valid karena tidak ada tanda =");
        }
        else if(isNotValidCharacter) {
            System.out.println("Tidak valid karena karakter yang dijinkan hanya 1, +, dan =");
        }
        else if (isNotValidEqual) {
            System.out.println("Tidak valid karena hanya satu tanda sama dengan yang diijinkan");
        }
        else if(isValid) {
            System.out.println("Valid");
        } else {
            System.out.println("Tidak valid");
        }
    }
}