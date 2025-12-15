import java.util.Scanner;
/**
 * Dies ist die Hauptklasse eines Spiels. Sie enthält die Hauptmethode, die zum
 * Starten des Spiels aufgerufen werden muss.
 *
 * @author Leandro Paolicelli
 */
public class RandomNumber
{
    RandomNumber(){
        
    }
    
    public static int randomGuess(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }
    
    static void main()
    {
        Scanner scanner = new Scanner(System.in);
        
        int min = 1;
        int max = 1000;
        
        int guessedNumber = randomGuess(min, max);
        int playerGuess;
        int attempts = 0;
        
        boolean correctGuess = false;
        
        while (!correctGuess) {
            System.out.println("Gib eine Zahl zwischen " + min + " und " + max + " ein: ");
            playerGuess = scanner.nextInt();
            attempts ++;
            
            if (playerGuess < guessedNumber) {
                System.out.println("Zu klein");
            }
            else if (playerGuess > guessedNumber) {
                System.out.println("Zu groß");
            }
            
            else if (playerGuess == guessedNumber) {
                correctGuess = true;
                System.out.println("Richtig!");
                
                if (attempts >= 5) {
                    System.out.println("Naja. Du hast " + attempts + " Versuche gebraucht.");
                }
                else {
                    System.out.println("Gut, Du hast " + attempts + " Versuche gebraucht.");
                }
            }
        }
    }
}