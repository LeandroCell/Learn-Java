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
        int max = 100;
        
        int guessedNumber = randomGuess(min, max);
        int playerGuess = min;
        int attempts = 0;
        
        boolean correctGuess = false;
        
        
        System.out.println("Gib eine Zahl zwischen " + min + " und " + max + " ein: ");
        while (!correctGuess) {
            boolean validInput = false;
            
            while(!validInput) {
                if (scanner.hasNextInt()) {
                    playerGuess = scanner.nextInt();
                    
                    if (playerGuess < min || playerGuess > max) {
                        System.out.println("Zahl außerhalb des Bereiches.");
                        System.out.println("Gib eine Zahl, innerhalb des Bereiches ein: ");
                    }
                    else {
                        validInput = true;
                    }
                }
                else {
                    System.out.println("Bitte eine Zahl eingeben!");
                    scanner.nextLine();
                }
            }
            
            attempts ++;
            
            if (playerGuess < guessedNumber) {
                System.out.println("Zu klein, gib eine größere Zahl ein: ");
            }
            else if (playerGuess > guessedNumber) {
                System.out.println("Zu groß, gib eine kleinere Zahl ein: ");
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