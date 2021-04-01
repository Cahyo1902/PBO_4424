import java.util.Scanner;
class MotherBoard {
// static nested class
static class USB{
    int getTotalPorts(int usb2,int usb3){
        return usb2 + usb3;
    }
}
}
class Main {
    public static void main(String[] args) {
        int usb2, usb3;
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Usb 2 : ");
        usb2 = keyboard.nextInt();

        System.out.print("Usb 3 : ");
        usb3 = keyboard.nextInt();

        MotherBoard.USB usb = new MotherBoard.USB();
        int totalUsb = usb.getTotalPorts(usb2, usb3);
        System.out.println("Total Ports = " + totalUsb);
    }
        // create an object of the static nested class
        // using the name of the outer class

    }
