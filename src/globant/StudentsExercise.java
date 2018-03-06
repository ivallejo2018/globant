package globant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StudentsExercise {

	public static void main(String[] args) {
		StudentsExercise ex = new StudentsExercise();
		
		List<List<Integer>> result = ex.orderArray(Arrays.asList(8,11,12,12,11,6,7,6,6,8,11,10,9));
		for(List<Integer> nextList : result) {
			nextList.forEach(age -> System.out.print(age + " "));
		}
	}

	public List<List<Integer>> orderArray(List<Integer> firstGradeList) {
		List<Integer> ages[] = new ArrayList[7];
		for(int i = 0; i < ages.length; i++) {
			ages[i] = new ArrayList<>();
		}
		
		for(Integer nextAgeStudent : firstGradeList) {
			ages[nextAgeStudent - 6].add(nextAgeStudent);
		}
		
		return Arrays.stream(ages).collect(Collectors.toList());
	}
	
}
