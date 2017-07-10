			
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
 
public class SetCoverMax2Elem
{
    interface Filter<T>
    {
        boolean matches(T t);
    }
 
    private static <T> Set<T> shortestCombination(Filter<Set<T>> filter,
            List<T> listOfSets)
    {
        final int size = listOfSets.size();
        if (size > 20)
            throw new IllegalArgumentException("Too many combinations");
        int combinations = 1 << size;
        List<Set<T>> possibleSolutions = new ArrayList<Set<T>>();
        System.out.println("All possible combinations are" );
        for (int l = 0; l < combinations; l++)
        {
        	//int[] intArray1 = new int[]{ 1, 2,5} ;
            //int[] intArray2 = new int[]{2,1,3,4};
            //int[] intArray3 = new int[]{3,2,5,4};
            //int[] intArray4 = new int[]{4,3,5,2};
            //int[] intArray5 = new int[]{1,5,4,3};
            
            Set<T> combination = new LinkedHashSet<T>();
            //List<Set<T>> combination = new ArrayList<Set<T>>();
            for (int j = 0; j < size; j++)
            {
                if (((l >> j) & 1) != 0)
                {
                    combination.add(listOfSets.get(j));
                    
                    if(filter.matches(combination))
            		{
                    	//if(combination.contains(intArray1))
                	//System.out.println("The combination was " + combination.contains(intArray1)+  " ^^^^^^^ " );
                	//System.out.println("  " );
                    possibleSolutions.add(combination);
                	}
                    
                }
            }
            //possibleSolutions.add(combination);
        }
        // the possible solutions in order of size.
        Collections.sort(possibleSolutions, new Comparator<Set<T>>()
        {
            public int compare(Set<T> o1, Set<T> o2)
            {
                return o1.size() - o2.size();
            }
        });
        for(int i=0;i<10;i++)
        {
        	
        	System.out.println("The combination was " + possibleSolutions.get(i)+"#####");
        }
        
        
        for (Set<T> possibleSolution : possibleSolutions)
        {
        	//System.out.println("The combination was " + possibleSolutions+"#####");
        	
        	//for (int i=0;i<size;i++)
        	//{	
        	//	if(filter.matches(possibleSolution))
        	//	{
            //	System.out.println("The combination was " + possibleSolutions +  " _____ " );
            //	}
        	//}
            if (filter.matches(possibleSolution))
            {
            //	System.out.println("The shortest combination was " + possibleSolution +  "***" );
            //	System.out.println("The shortest combination was  " + filter +  " &&&" );
                return possibleSolution;
            }
        }
        return null;
    }
 
    public static void main(String[] args)
    {
        Integer[][] arrayOfSets = { { 1, 2,5},{2,1,3,4},{3,2,5,4},{4,3,5,2},{1,5,4,3} };
        Integer[] solution = { 1, 2, 3, 4, 5};
        List<Set<Integer>> listOfSets = new ArrayList<Set<Integer>>();
        for (Integer[] array : arrayOfSets)
            listOfSets.add(new LinkedHashSet<Integer>(Arrays.asList(array)));
        final Set<Integer> solutionSet = new LinkedHashSet<Integer>(
                Arrays.asList(solution));
        Filter<Set<Set<Integer>>> filter = new Filter<Set<Set<Integer>>>()
        {
            public boolean matches(Set<Set<Integer>> integers)
            {
                Set<Integer> union = new LinkedHashSet<Integer>();
                for (Set<Integer> ints : integers)
                    union.addAll(ints);
                return union.equals(solutionSet);
            }
        };
        
        Set<Set<Integer>> firstSolution = shortestCombination(filter,
                listOfSets);
        System.out.println("The shortest combination was " + firstSolution);
    }
}
