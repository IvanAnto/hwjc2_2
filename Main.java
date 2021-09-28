import java.util.*;

class Main {
  public static void main(String[] args) {

    List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");

    List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

    Collection<Person> persons = new ArrayList<>();

    for (int i = 0; i < 1000; i++) {
      persons.add(new Person(
        names.get(new Random().nextInt(names.size())),
        families.get(new Random().nextInt(families.size())),
        new Random().nextInt(100),
        Sex.values()[new Random().nextInt(Sex.values().length)],
        Education.values()[new Random().nextInt(Education.values().length)])
      );
    }

    //System.out.println(persons); 
    
    // Поиса несовершеннолетних
    long noAge = persons.stream() // 
      .filter(p -> p.getAge() < 18)
      .count();
    System.out.println(noAge);  

    // Получение списка призывников
    Collection<Person> calledMan = persons.stream()
      .filter(p -> p.getAge() > 18 && p.getAge() < 27 && p.getSex() == Sex.MAN)
      .map(Person::getFamily)
      .collect(Collectors.toList());
    System.out.println(calledMan);

    // Получение отсортированного по фамилии списка потенциально работоспособных людей с высшим образованием
    Collection<Person> workPeople = persons.stream()
      .filter(p -> p.getAge() > 18 && p.getEducation() == Education.HIGHER)
      .sorted(Comparator.comparing(Person::getFamily))
      .collect(Collectors.toList());
    System.out.println(workPeople);  
  }
}