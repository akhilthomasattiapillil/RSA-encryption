package com.advAlgo.assgn1;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
import com.advAlgo.assgn1.MillerRabin;

public class RSA_New {
    public static void main (String [] args){
        //System.out.println("ASCII Value of a is : "+(int)'a');

        //encrypt("way" , 39423, 27, new BigInteger("46927"));
        //decrypt("bcib" , 26767, 27, new BigInteger("46927"));

        /*BigInteger p = millerRabin(); //returns a probable prime number p
        BigInteger q = millerRabin(); //returns a probable prime number q */ //to be uncommented later
        BigInteger p = new BigInteger("281");
        BigInteger q = new BigInteger("167");


        BigInteger o=BigInteger.ONE; //for calculating p-1 and q-1 for phi

        System.out.println("Prime numbers generated (p,q) are "+p.toString()+" "+q.toString());

        //int r = p.intValue();
        //int s = q.intValue();
        //int ph = (r-1)*(s-1);

        //calculating phi in the code below
        BigInteger p1=p.subtract(o); //p-1
        BigInteger q1=q.subtract(o); //q-1
        BigInteger phi=p1.multiply(q1); //phi = (p-1)*(q-1)
        BigInteger n=p.multiply(q); //n = p*q

        System.out.println("n is "+n.toString()+" phi is "+phi.toString());


        Scanner sc = new Scanner(System.in);
        BigInteger e;

        while (true) {
            System.out.println("Enter a number: ");
            e = sc.nextBigInteger();
            BigInteger gcd = e.gcd(phi); //this library call need to be converted to euclidean gcd call
            if (gcd.compareTo(o)==0) {
                System.out.println("GCD is 1");
                break;
            }
            else
                //if(gcd.compareTo(o)>0)
                System.out.println("Try again");
        }

        BigInteger d=e.modInverse(phi); // d = (e^-1) mod (phi)
        System.out.println("e is: " +e);
        System.out.println("d is: " +d);
        System.out.print("Enter the string to be encrypted in small letters:");
        String input = sc.next();

        int e1 = e.intValue();
        String encrypted_str = encrypt(input , e1, 27, n);
        String decrypted_str = decrypt(encrypted_str,d.intValue(),27,n);

        System.out.println("Encrypted string is "+encrypted_str+".\n Decrypted String is "+decrypted_str);

    }

    public static int alphaToNum(String input,int base){

        int l = input.length();
        int num, dec = 0, cnt = 0;

        for (int i = l - 1; i >= 0; i--) {
            //num = input.charAt(i) - 'a' + 1; //use if a is considered 1
            num = input.charAt(i) - 'a' ; //use if a is considered 0
            dec += num * Math.pow(base, cnt);//for int
            //bi2 = base.pow(cnt); //power for biginteger

            cnt++;
        }

        //BigInteger number = BigInteger.valueOf(dec);
        //System.out.println("Encrypted value is " + dec); //encrypted string value successfully obtained
        return dec;
    }



    public static String encrypt(String input, int e, int base, BigInteger n) {

        int dec = alphaToNum(input,base);

        BigInteger number = BigInteger.valueOf(dec); //encrypted string value successfully obtained
        //System.out.println("Encrypted value is " + dec);

        if (n.compareTo(number) < 0){
            System.out.println("converted text value is greater than n. Exiting the program");
            java.lang.System.exit(0);
        }

        //System.out.println((int)'t'-(int)'a');
        BigInteger enc1 = number.pow(e); //encode stem
        BigInteger enc2 = enc1.mod(n);

        //System.out.println("After c power e mod  n is " + enc2);


        int enc3=enc2.intValue();
        //System.out.println(input);

        String dec2 = convertToAlpha(enc3,27); //converting encoded string to alphabets
        //System.out.println(dec2);

        return dec2;

    }

    public static String decrypt(String input, int d, int base, BigInteger n) {
        int dec = alphaToNum(input, 27);
        //System.out.println(dec);

        BigInteger number = BigInteger.valueOf(dec);
        BigInteger enc1 = number.pow(d); //encode stem
        BigInteger enc2 = enc1.mod(n);

        //System.out.println("After c power e mod  n is " + enc2);

        int enc3=enc2.intValue();
        String dec2 = convertToAlpha(enc3,27); //converting encoded string to alphabets
        //System.out.println(dec2);

        return dec2;
    }

    public static String convertToAlpha(int dec,int e) {

        //System.out.print(dec +" "+e +" ");
        int letterAsci = 0;
        String result="";

        while(dec > 0)
        {
            int c=dec/e;
            int d=dec%e;

            letterAsci = d + (int)'a'; //use if a is considered 0
            //System.out.print((char)letterAsci);
            result = (char)letterAsci + result;

            dec=c;
        }

        return result;
    }

    /*public static BigInteger millerRabin_library(){
        Random r = new Random();
        BigInteger n=new BigInteger(Integer.toString(r.nextInt(Integer.SIZE - 1)));
        while(!n.isProbablePrime(-1))
        {
            n = new BigInteger(Integer.toString(r.nextInt(Integer.SIZE - 1)));
        }
        return n;
    }*/

    public static BigInteger millerRabin_implemented(){
        Random r = new Random();
        BigInteger n=new BigInteger(Integer.toString(r.nextInt(Integer.SIZE - 1)));
        while(!MillerRabin.isProbablePrime(n.longValue(),5))
        while(true)
        {
            n = new BigInteger(Integer.toString(r.nextInt(Integer.SIZE - 1)));
        }
        return n;
    }

}

