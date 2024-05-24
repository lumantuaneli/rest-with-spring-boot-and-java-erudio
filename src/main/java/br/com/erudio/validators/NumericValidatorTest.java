package br.com.erudio.validators;

public class NumericValidatorTest {
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
        String[][] vNumberLists = new String[][] {
            new String[] {
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
            },
            vNumbers,
            new String[] {
                "10",
                "-,1A",
            },
        };
        
        System.out.println("LENIENT RUN");
        for (String vNumber : vNumbers) {
            System.out.println(String.format("%s: %s", vNumber, NumericValidator.isNumeric(vNumber)));
        }
        System.out.println();
        System.out.println();
        
        System.out.println("UNLENIENT RUN");
        for (String vNumber : vNumbers) {
            String vMsg;
            try {
                vMsg = String.valueOf(NumericValidator.isNumeric(vNumber, false));
            }
            catch (Exception vEx) {
//                vEx.printStackTrace();
                vMsg = vEx.getMessage();
            }
            System.out.println(String.format("%s: %s", vNumber, vMsg));
        }
        System.out.println();
        System.out.println();
        
        System.out.println("LENIENT LIST RUN");
        for (String[] vNumberList : vNumberLists) {
            System.out.println(String.format("%s: %s", vNumberList, NumericValidator.validateNumbers(true, vNumberList)));
        }
        System.out.println();
        System.out.println();
        
        System.out.println("UNLENIENT LIST RUN");
        for (String[] vNumberList : vNumberLists) {
            String vMsg;
            try {
                vMsg = String.valueOf(NumericValidator.validateNumbers(vNumberList));
            }
            catch (Exception vEx) {
//                vEx.printStackTrace();
                vMsg = vEx.getMessage();
            }
            System.out.println(String.format("%s: %s", vNumberList, vMsg));
        }
    }

}
