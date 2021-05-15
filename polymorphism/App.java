package polymorphism;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        double panjang, lebar, tinggi, jarijari, sisi;
        Scanner s = new Scanner(System.in);

        BangunDatar bd;
        System.out.println("----Persegi panjang----");
        SegiEmpat persegi = new SegiEmpat();
        bd = persegi;
        double luas = bd.luas();
        bd.cetakLuas("Persegi panjang",luas);

        System.out.println("----Segitiga Siku-siku----");
        System.out.print("masukkan panjang : ");
        panjang = s.nextDouble();
        System.out.print("masukkan tinggi : ");
        tinggi = s.nextDouble();

        Segitiga sikusiku = new Segitiga();
        bd = sikusiku;
        double Luas = bd.luas(panjang,tinggi);
        bd.cetakLuas("Segitiga Siku-siku",Luas);

        System.out.println("----Lingkaran----");
        System.out.print("masukkan jari-jari : ");
        jarijari = s.nextDouble();

        Lingkaran lingkaran = new Lingkaran();
        bd = lingkaran;
        double luass = bd.luas(jarijari);
        bd.cetakLuas("Lingkaran",luass);
    }

}
