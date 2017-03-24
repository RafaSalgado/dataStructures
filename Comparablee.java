import java.util.ArrayList;
import java.util.Collections;

public class Comparablee {

	public static class Person implements Comparable<Person> {
		String nombre;
		int semestre;
		int edad;

		public Person(String nomnbre, int edad, int semestre) {
			this.nombre = nomnbre;
			this.semestre = semestre;
			this.edad = edad;
		}

		@Override
		public int compareTo(Person o) {
			if (this.semestre < o.semestre)
				return -1;
			else if (semestre > o.semestre)
				return 1;
			else {
				if (edad < o.edad)
					return -1;
				else if (edad > o.edad)
					return 1;
			}
			return 0;
		}

	}

	public static void main(String args[]) {
		ArrayList<Person> persons = new ArrayList<Person>();
		persons.add(new Person("jose", 22, 7));
		persons.add(new Person("juan", 18, 6));
		persons.add(new Person("pedro", 25, 4));
		persons.add(new Person("Maria", 30, 9));
		persons.add(new Person("Andres", 17, 7));

		Collections.sort(persons);
		for (int i = 0; i < persons.size(); i++) {
			System.out.println(persons.get(i).nombre + " " + persons.get(i).semestre);
		}
	}

}
