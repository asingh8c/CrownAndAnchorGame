import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Testing Bug 004: where dice face are not unique in each turn of game
 */
/**
 * @author asmita
 * @version Bug004
 */
public class TestBug004 {

	/**
	 * Main class for simplified testing
	 */
	public static void main(String[] args) throws Exception {
		
		   BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

	        Dice d1 = new Dice();
	        Dice d2 = new Dice();
	        Dice d3 = new Dice();

	        Player player = new Player("Fred", 20);
	        Game game = new Game(d1, d2, d3);
	        List<DiceValue> cdv = game.getDiceValues();

	        int totalWins = 0;
	        int totalLosses = 0;

	        while (true)
	        {
	            int winCount = 0;
	            int loseCount = 0;
	            //Testing one game
	            	String name = "Fred";
	            	int balance = 20;
	            	int limit = 0;
	                player = new Player(name, balance);
	                player.setLimit(limit);
	                int bet = 5;

	                System.out.println(String.format("Start Game 1: "));
	                System.out.println(String.format("%s starts with balance %d, limit %d", 
	                		player.getName(), player.getBalance(), player.getLimit()));

	                int turn = 0;
	                while (player.balanceExceedsLimitBy(bet) && player.getBalance() < 100)
	                {
	                    turn++;                    
	                	DiceValue pick = DiceValue.getRandom();
						System.out.printf("Turn %d: %s bet %d on %s\n", turn, player.getName(), bet, pick);

	                	int winnings = game.playRound(player, pick, bet);
	                    cdv = game.getDiceValues();
						System.out.printf("Rolled %s, %s, %s\n", cdv.get(0), cdv.get(1), cdv.get(2));
						if (winnings > 0) {
							System.out.printf("%s won %d, balance now %d\n\n", player.getName(), winnings,
									player.getBalance());
							winCount++;
						} else {
							System.out.printf("%s lost, balance now %d\n\n", player.getName(), player.getBalance());
							loseCount++;
						}
  
	                    
	                } //while
	                
	            System.out.println("ENTER q to Exit");
	            String ans = console.readLine();
	            if (ans.equals("q")) break;
	        } //while true
	        
	        System.out.println(String.format("Overall win rate = %.1f%%", (float)(totalWins * 100) / (totalWins + totalLosses)));
		}


}
