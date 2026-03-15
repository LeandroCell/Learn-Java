import java.util.List;
import static java.util.Arrays.asList;

/**
 * Diese Klasse definiert <Zusammenfassung ergänzen>
 *
 * @author Leandro Paolicelli
 */
class Exam
{
    int howManyOdds(int[] numbers) 
    {
        int result = 0;
        
        for (int number : numbers) {
            if ((number % 2) == 1) {
                result += 1;
            }
        }
        return result;
    }
    
    int findEven(int[] numbers)
    {
        int result = 0;
        
        for (int number: numbers) {
            if ((number % 2) == 0) {
                return number;
            }
        }
        return -1;
    }
    
    int[] findAllEven(int[][] numbers)
    {
        int[] result;
        
        for (int[] row : numbers) {
            boolean allEven = true;
            for (int number : row) {
                if ((number % 2) != 0) {
                    allEven = false;
                }
            } 
            if (allEven) {
                return row;
            }
        }
        return null;
    }
    
    boolean evenParity(int number) 
    {
       int counter = 0;
       
       while (number != 0) {
           if ((number & 1) == 1) {
               counter += 1;
           }
           number = number >> 1;
       }
       if ((counter % 2) == 0) {
           return true;
       }
       else {
           return false;
       }
    }
    
    void transpose(final int[][] matrix) 
    {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                int ogMatrix = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = ogMatrix;
            }
        }
        for (int[] row : matrix) {
            for (int number : row) {
                System.out.print(number + " ");
            }
            System.out.println();
        }
    }
    
    void transpose(final String[] matrix)
    {
       for (int j = 0; j < matrix[0].length(); j++) {
           for (int i = 0; i < matrix.length; i++) {
               System.out.print(matrix[i].charAt(j));
           }
           System.out.println();
       }
    }
    
    void transpose(final List<List<Integer>> matrix) 
    {
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = i + 1; j < matrix.get(i).size(); j++) {
                int ogMatrix = matrix.get(i).get(j);
                matrix.get(i).set(j, matrix.get(j).get(i));
                matrix.get(j).set(i, ogMatrix);
            }
        }
        for (List<Integer> row : matrix) {
            System.out.println(row);
        }
    }
    
    
}