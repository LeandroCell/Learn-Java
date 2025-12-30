public class ArrayAnalysis 
{
    // Attribute
    int[] arrayValues;
    
    // Konstruktor
    ArrayAnalysis(int[] arrayValues)
    {
        this.arrayValues = arrayValues;        
    }
    
    private String giveValues() 
    {
        String result = "Alle Werte im Array: ";
        
        for (int i = 0; i < arrayValues.length; i++) {
            result = result + arrayValues[i] + " ";
        }
        return result;
    }
    
    private int calculateSum() 
    {
        int result = 0;
        
        for(int i = 0; i < arrayValues.length; i++) {
            result += arrayValues[i];
        }
        return result;
    }
    
    private double calculateAverage() 
    {
        double average = (double) calculateSum() / arrayValues.length;
        
        return average;
    }
    
    private int findMaximum() 
    {
        int maxValue = arrayValues[0];
        
        for (int i = 1; i < arrayValues.length; i++) {
            if (maxValue < arrayValues[i]) {
                maxValue = arrayValues[i];
            }
        }
        return maxValue;
    }
    
    private int findMinimum() 
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
        
        ArrayAnalysis analysis = new ArrayAnalysis(arrayValues);
        
        System.out.println("" + analysis.giveValues());
        System.out.println("" + analysis.calculateSum());
        System.out.println("" + analysis.calculateAverage());
        System.out.println("" + analysis.findMaximum());
        System.out.println("" + analysis.findMinimum());
    }
}