public class ArrayAnalysis 
{
    private static String giveValues(int[] arrayValues) 
    {
        String result = "Alle Werte im Array: ";
        
        for (int i = 0; i < arrayValues.length; i++) {
            result = result + arrayValues[i] + " ";
        }
        return result;
    }
    
    private static int calculateSum(int[] arrayValues) 
    {
        int result = 0;
        
        for(int i = 0; i < arrayValues.length; i++) {
            result += arrayValues[i];
        }
        return result;
    }
    
    private static double calculateAverage(int[] arrayValues) 
    {
        double average = (double) calculateSum(arrayValues) / arrayValues.length;
        
        return average;
    }
    
    private static int findMaximum(int[] arrayValues) 
    {
        int maxValue = arrayValues[0];
        
        for (int i = 1; i < arrayValues.length; i++) {
            if (maxValue < arrayValues[i]) {
                maxValue = arrayValues[i];
            }
        }
        return maxValue;
    }
    
    private static int findMinimum(int[] arrayValues) 
    {
        int minValue = arrayValues[0];
        
        for (int i = 1; i < arrayValues.length; i++) {
            if (minValue > arrayValues[i]) {
                minValue = arrayValues[i];
            }
        }
        return minValue;
    }
   
    public static void main()
    {
        int[] arrayValues = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        String result = giveValues(arrayValues);
        System.out.println(result);
        
        int sum = calculateSum(arrayValues);
        System.out.println("Die Summe aller Werte ist: " + sum);
        
        double avg = calculateAverage(arrayValues);
        System.out.println("Der Durchschnitt aller Werte ist: " + avg);
        
        int max = findMaximum(arrayValues);
        System.out.println("Der größte Wert ist: " + max);
        
        int min = findMinimum(arrayValues);
        System.out.println("Der kleinste Wert ist: " + min);
        
    }
}