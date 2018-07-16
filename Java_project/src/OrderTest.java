import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class OrderTest {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		String filename = "C:\\Users\\DELL\\git\\Java_project\\Java_project\\src\\order.txt";
		Order order = new Order();
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String str=null;
		String[] str1 = new String[4];
		while((str=br.readLine()) != null) {
			str1 = str.split(",");
			order.setOrderedMenu(str1[1]);
			System.out.println(order.getOrderedMenu());
		}
		br.close();
		fr.close();
	}

}
