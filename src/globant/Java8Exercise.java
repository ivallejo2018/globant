package globant;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.partitioningBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Person implements Comparator<Person>{
	
	private String name;
	private int age;
	private String nationality;
	
	public Person(String name, int age, String nationality) {
		this.name = name;
		this.age = age;
		this.nationality = nationality;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Override
	public int compare(Person p1, Person p2) {
		
		return Integer.valueOf(p1.getAge()).compareTo(Integer.valueOf(p2.getAge()));
	}
}

public class Java8Exercise {

	public static void main(String[] args) {
		Java8Exercise je = new Java8Exercise();
		List<Person> people = new ArrayList<>();
		people.add(new Person("Uno", 12, "Mexicana"));
		people.add(new Person("Dos", 42, "Inglesa"));
		people.add(new Person("Tres", 35, "Inglesa"));
		people.add(new Person("Cuatro", 5, "Alemana"));
		
		System.out.println();
		je.getPartition(people).forEach(
				(k, l) ->  { System.out.println(k + ": ") ;l.forEach(p -> System.out.println(p.getName() + " -> " + p.getAge())); }
		);
		
		System.out.println();
		je.groupByNationality(people).forEach(
				(k, l) -> { System.out.println(k + ": ") ;l.forEach(p -> System.out.println(p.getName() + " -> " + p.getNationality())); }
		);
		
		System.out.println();
		System.out.println(je.getNames(people));
	
		System.out.println();
		System.out.println(je.sumElements(Arrays.asList(1, 20, 13, 45)));
	}

	//Obtiene una particion de adultos y otra de niños
	public Map<Boolean, List<Person>> getPartition(List<Person> people) {
		
		return people.stream().collect(partitioningBy(p -> p.getAge() > 18));			
	}
	
	//Obtiene agrupaciones basadas en nacionalidad
	public Map<String, List<Person>> groupByNationality(List<Person> people) {
		
		return people.stream().collect(groupingBy(p -> p.getNationality()));
	}
	
	//Obtiene los nombres separados por coma
	public String getNames(List<Person> people) {
		return people.stream().map(p -> p.getName()).collect(joining(", "));
	}
	
	//Obtiene la suma de los elementos
	public int sumElements(List<Integer> elements) {
		return elements.stream().reduce(0, Integer::sum);
	}
}
