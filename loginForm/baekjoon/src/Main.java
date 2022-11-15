import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        for(int i = 0; i < sc.nextInt(); i++) {
            list.add(sc.next());
        }
    }
}