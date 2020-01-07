package utill;


public final class Utill {

    public static String[] getBirth(String id_number){
        String[] birth = new String[3];
        birth[0] = id_number.substring(8,12);
        birth[1] = id_number.substring(12, 14);
        birth[2] = id_number.substring(14, 15);
        return birth;
    }

}