package mastercard;
import model.CardList;
import view.MainWindow;

public class Main {

	public static void main(String[] args) {
		// Main method
		CardList list = new CardList();
		MainWindow mv = new MainWindow(list);

	}
}
