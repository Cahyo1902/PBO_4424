class Tugas1{
    public static void main(String[] args){
        /*int a1 = -5,a2 = 8, a3=6;
        int b1 = 55,b2 = 9, b3=9;
        int c1 = 20,c2 = -3, c3=5, c4=8 ;
        int d1 = 5,d2 = 15, d3=3, d4=2, d5=8;*/
        int a;
        int b;
        int c;
        int d;
        String soala = "-5 + 8 * 6 =";
        String soalb = "(55+9) % 9 =";
        String soalc = "20 + -3*5 / 8 =";
        String soald = "5 + 15 / 3 * 2 - 8 % 3 =";
        a = -5 + 8 * 6;
        b = (55+9) % 9;
        c = 20 + -3*5 / 8;
        d = 5 + 15 / 3 * 2 - 8 % 3;
        System.out.println(soala+" "+a);
        System.out.println(soalb+" "+b);
        System.out.println(soalc+" "+c);
        System.out.println(soald+" "+d);
    }
}