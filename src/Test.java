import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String docNum = "+7896-dаP-7896-dаP-5r6М";
        String num = "+(56)7896532";
        String email = "mariamuss@gmail.com";
        Pattern docNumberPattern = Pattern.compile("[0-9]{4}[-][A-zЁёА-я]{3}[-][0-9]{4}[-][A-zЁёА-я]{3}[-][0-9][A-zЁёА-я][0-9][A-zЁёА-я]");
        Pattern phone = Pattern.compile("[+][(][0-9]{2}[)][0-9]{7}");
        Pattern emailPattern = Pattern.compile("^(([a-zа-я0-9_-]+\\.)*[a-zа-я0-9_-]+@[a-zа-я0-9-]+(\\.[a-zа-я0-9-]+)*\\.[a-zа-я]{2,6})?$");



        Matcher m = emailPattern.matcher(email);

        String str = "";

        if (m.find()){
            System.out.println(m.group());
        } else {
            System.out.println("no");
        }

    }
}
