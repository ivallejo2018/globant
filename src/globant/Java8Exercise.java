package globant;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;

import java.util.ArrayList;

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
	}

	//Obtiene una particion de adultos y otra de niños
	public Map<Boolean, List<Person>> getPartition(List<Person> people) {
		
		return people.stream().collect(partitioningBy(p -> p.getAge() > 18));			
	}
	
	//Obtiene agrupaciones basadas en nacionalidad
	public Map<String, List<Person>> groupByNationality(List<Person> people) {
		
		return people.stream().collect(groupingBy(p -> p.getNationality()));
	}
}
