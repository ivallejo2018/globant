package globant;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.partitioningBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.DoubleSupplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
		
		System.out.println();
		System.out.println(
				je.concatStrings(Arrays.asList("uno", "dos", "tres")) + " " +
				je.concatStringsOtherVersion(Arrays.asList("cuatro", "cinco", "seis"))  + " " +
				je.concatStringsWithCommas(Arrays.asList("siete", "ocho", "nueve"))  + " " +
				je.getTotalChars(Arrays.asList("hola", "como", "estas")) + " " +
				je.countWordsWithLetterH(Arrays.asList("hola", "como", "estas", "hoy", "que", "es", "ocho")));
	
		System.out.println();
		System.out.println();
		double numbers[] = new Random().doubles(10000000).toArray();
		long start = System.currentTimeMillis();
		System.out.println("Sequential process to get the sum of the square roots: " 
				+ je.sumSquareRoots(numbers, false) + " -- time (ms) -> " + (System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		System.out.println("Parallel process to get the sum of the square roots: "
				+ je.sumSquareRoots(numbers, true) + " -- time (ms) -> " + (System.currentTimeMillis() - start));
	
		System.out.println();
		je.printFiveRandomValues(DoubleStream.generate(() -> new Random().nextDouble() * 10).boxed());
	
		System.out.println();
		je.getListOfTenRandomValues(DoubleStream.generate(() -> new Random().nextDouble() * 10).boxed()).forEach(el -> System.out.println(el));
	
		System.out.println();
		System.out.println(je.getArrayOfTwentyRandomValues(DoubleStream.generate(() -> new Random().nextDouble() * 10).boxed()));
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
	
	//Produce una cadena concatenada en mayusculas de una lista de cadenas usando reduce
	public String concatStrings(List<String> strings) {
		return strings.stream().reduce("", String::concat).toUpperCase();
	}
	
	//Produce una cadena concatenada en mayusculas de una lista de cadenas usando map y luego reduce
	public String concatStringsOtherVersion(List<String> strings) {
		return strings.stream().map(s -> s.toUpperCase()).reduce("", String::concat);
	}	

	//Produce una lista de cadenas separadas por comas de una lista de cadenas
	public String concatStringsWithCommas(List<String> strings) {
		return strings.stream().collect(joining(","));
	}
	
	//Obtiene el numero total de caracteres de la lista de cadenas
	public int getTotalChars(List<String> strings) {
		return strings.stream().collect(Collectors.summingInt(s -> s.length()));
	}
	
	//Obtiene el numero de palabras que contienen la letra 'h'
	public long countWordsWithLetterH(List<String> strings) {
		return strings.stream().filter(s -> s.indexOf("h") != -1).count();
	}
	
	//Suma las raices cuadradas de los elementos
	public double sumSquareRoots(double numbers[], boolean inParallel) {
		if(!inParallel) return Arrays.stream(numbers).map(n -> Math.sqrt(n)).sum();
		return Arrays.stream(numbers).parallel().map(n -> Math.sqrt(n)).sum();
	}
	
	//Imprime los primeros cinco valores aleatorios
	public void printFiveRandomValues(Stream<Double> infinite) {
		infinite.limit(5).forEach(el -> System.out.println(el));
	}
	
	//Produce una lista con diez valores aleatorios
	public List<Double> getListOfTenRandomValues(Stream<Double> infinite) {
		return infinite.limit(10).collect(Collectors.toList());
	}
	
	public Double[] getArrayOfTwentyRandomValues(Stream<Double> infinite) {
		return infinite.limit(20).collect(Collectors.toList()).toArray(new Double[20]);
	}
}
