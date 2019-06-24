package com.siruiman.crosslogistics.util;



public class RallyPointIdUtil {

    public static int selectRallyPointId (String zipcode){
        int number =0 ;
        if(zipcode.trim().substring(0,1).equals(0)){
           number =  Integer.parseInt(zipcode.trim().substring(1,2));
        }else{
           number = Integer.parseInt(zipcode.trim().substring(0,2));
        }
        switch (number){
            case 1 : return 1;
            case 2 : return 1;
            case 3 : return 1;
            case 4 : return 1;
            case 5 : return 1;
            case 6 : return 1;
            case 7 : return 1;
            case 8 : return 1;
            case 9 : return 2;
            case 10 : return 2;
            case 11 : return 2;
            case 12 : return 2;
            case 13 : return 2;
            case 14 : return 2;
            case 15 : return 2;
            case 16 : return 2;
            case 17 : return 1;
            case 18 : return 1;
            case 19 : return 1;
            case 20 : return 4;
            case 21 : return 4;
            case 22 : return 1;
            case 23 : return 1;
            case 24 : return 3;
            case 25 : return 3;
            case 26 : return 3;
            case 27 : return 3;
            case 28 : return 3;
            case 29 : return 3;
            case 30 : return 3;
            case 31 : return 3;
            case 32 : return 3;
            case 33 : return 3;
            case 34 : return 4;
            case 35 : return 4;
            case 36 : return 4;
            case 37 : return 4;
            case 38 : return 4;
            case 39 : return 4;
            case 40 : return 4;
            case 41 : return 4;
            case 42 : return 4;
            case 43 : return 4;
            case 44 : return 4;
            case 45 : return 4;
            case 46 : return 4;
            case 47 : return 4;
            case 48 : return 4;
            case 49 : return 4;
            case 50 : return 4;
            case 51 : return 4;
            case 52 : return 4;
            case 53 : return 4;
            case 54 : return 4;
            case 55 : return 3;
            case 56 : return 3;
            case 57 : return 3;
            case 58 : return 5;
            case 59 : return 5;
            case 60 : return 2;
            case 61 : return 2;
            case 62 : return 2;
            case 63 : return 2;
            case 64 : return 2;
            case 65 : return 2;
            case 66 : return 2;
            case 67 : return 2;
            case 68 : return 2;
            case 69 : return 2;
            case 70 : return 2;
            case 71 : return 2;
            case 72 : return 5;
            case 73 : return 5;
            case 74 : return 5;
            case 75 : return 5;
            case 76 : return 5;
            case 77 : return 5;
            case 78 : return 5;
            case 79 : return 5;
            case 80 : return 5;
            case 81 : return 4;
            case 82 : return 4;
        }
     return 0;
    }
}
