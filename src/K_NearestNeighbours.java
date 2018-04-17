import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class K_NearestNeighbours {
	
	private static String[] makeUnique(String[] array){

		List<String> list = Arrays.asList(array);  
		Set<String> original = new HashSet<String>(list);
		String[] unique = original.toArray(new String[0]);
		
		return unique;
	}
	
	private static int getMax(int[] array){
		int max = array[0];
		
		for(int i = 1; i < array.length;i++)
			if(array[i] > max)
				max = array[i];
		
		return max;
	}
	
	private static int getFrequencyAmount(int[] array, int value){
		int frequentValue = 0;
		
		for(int i = 0; i < array.length;i++)
			if(array[i] == value)
				frequentValue++;
		
		return frequentValue;
	}
	
	
	private static int findIndex(int[] array, int value){
		int index = -1;
		
		for(int i = 0; i < array.length;i++)
			if(array[i] == value){
				 index = i;
				 break;
			}
		return index;
	}
	
	private static int createRandomValue(int x){
		Random ran = new Random();
		int ranNum = ran.nextInt(x);
		return ranNum;
	}
	
	
	public static String getMostCommon(String[] array){
		// створюю стрічковий масив з унікальними значеннями
		String[] unique = makeUnique(array);
		
		//стврюю масив для збереження індексів з nique 
		int[] counts = new int[unique.length];
		
		//шукаю максимальне значення
		int maxValue = getMax(counts);
		
		//шукаю частоту максимального значення
		int frequentValue = getFrequencyAmount(counts, maxValue);
		
		if(frequentValue == 1){
			int index = findIndex(counts, maxValue);
			
			return unique[index];
		}
		
		else {
			
			int[] frequentSize = new int[frequentValue];
			int N = frequentSize.length;
			
			int count = 0;
			for(int i = 0; i < counts.length; i++)
				if(counts[i] == maxValue){
					frequentSize[count] = i;
					count++;
				}
			
			int randomValue = createRandomValue(N);
			int newIndex = frequentSize[randomValue];
			
			return unique[newIndex];
		}
	}

	
}
