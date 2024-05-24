package br.com.erudio.util;

public class MathUtilsTest {
    public static void main(String[] args) {
        String[] vNumbers = new String[] {
            "10",
            "10.1",
            "10,1",
            "+10.1",
            "+10,1",
            "-10.1",
            "-10,1",
            ".1",
            ",1",
            "+.1",
            "+,1",
            "-.1",
            "-,1",
            null,
            "A",
            "1.1.1",
            "1,1.1",
            "1,1,1",
            "1.1,1",
        };
        
        System.out.println("LENIENT RUN");
        for (String vNumber : vNumbers) {
            System.out.println(String.format("%s: %s", vNumber, MathUtils.convertToDouble(vNumber)));
        }
        System.out.println();
        System.out.println();
        
        System.out.println("UNLENIENT RUN");
        for (String vNumber : vNumbers) {
            String vMsg;
            try {
                vMsg = String.valueOf(MathUtils.convertToDouble(vNumber, false));
            }
            catch (Exception vEx) {
//                vEx.printStackTrace();
                vMsg = vEx.getMessage();
            }
            System.out.println(String.format("%s: %s", vNumber, vMsg));
        }
    }

}
