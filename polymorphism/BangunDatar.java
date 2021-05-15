package polymorphism;

import java.util.Scanner;

public class BangunDatar {
    protected double panjang;
    protected double lebar;
    protected double jarijari;
    protected double tinggi;
    protected double sisi;

    public double luas()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("masukkan panjang : ");
        panjang = sc.nextDouble();
        System.out.print("masukkan lebar : ");
        lebar = sc.nextDouble();
        double luas;
        luas = panjang*lebar;
        return luas;
    }

    public double luas(double panjang, double tinggi)
    {
        return (panjang*tinggi)/2;
    }

    public double luas(double jarijari)
    {
        return (3.14 * jarijari * jarijari);
    }

    public void cetakLuas(String tipe, double luas)
    {
        System.out.println("Luas "+tipe+ " adalah "+luas);
    }

    //overload
    public void cetakLuas(String tipe, int luas)
    {
        System.out.println("Luas "+tipe+ " adalah "+luas);
    }
}