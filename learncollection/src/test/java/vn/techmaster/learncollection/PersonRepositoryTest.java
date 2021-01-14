package vn.techmaster.learncollection;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.list;

import vn.techmaster.learncollection.model.Person;
import vn.techmaster.learncollection.repository.PersonRepositoryInterface;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    PersonRepositoryInterface personRepository;

    @Test
    public void getAll() {
        List<Person> people = personRepository.getAll();
        people.forEach(System.out::println);
//        assertThat(people).hasSize(20);
    }

    @Test
    public void getSortedCities() {
        List<String> sortedCities = personRepository.getSortedCities();
        sortedCities.forEach(System.out::println);  //In theo tất các thành phố ra để kiểm tra xem có sắp xếp không
	/*
		Cách này viết dài
		assertThat(sortedCities).contains("Paris", "Dubai");
		assertThat(sortedCities).isSortedAccordingTo(Comparator.naturalOrder());*/

        //Cách này chain các điều kiện test với nhau ngắn gọn và đẹp hơn
        assertThat(sortedCities).isSortedAccordingTo(Comparator.naturalOrder())
                .contains("Berlin", "Budapest", "Buenos Aires", "Copenhagen", "Hanoi", "Jakarta", "Mexico City", "Zagreb");
    }

    @Test
    public void getSortedJobs() {
        List<String> sortedJobs = personRepository.getSortedJobs();
        sortedJobs.forEach(System.out::println);

        assertThat(sortedJobs).isSortedAccordingTo(Comparator.naturalOrder())
                .contains("Pole Dancer", "Bartender", "Developer", "Personal Trainer", "Soldier", "Teacher", "Taxi Driver", "Nurse", "Musician");

    }

    @Test
    public void sortPeopleByFullNameReversed() {
        List<Person> sortedPeople = personRepository.sortPeopleByFullNameReversed();
        sortedPeople.forEach(person -> System.out.println(person.getFullname()));
        assertThat(sortedPeople).isSortedAccordingTo(Comparator.comparing(Person::getFullname).reversed());
        sortedPeople.forEach((person) -> System.out.println("FullName: " + person.getFullname()));

    }

    @Test
    public void findTop5Citis() {
        Map<String, Long> findTop5 = personRepository.findTop5Citis();
        findTop5.forEach((k, v) -> System.out.println("city: " + k + " count: " + v));
    }

    @Test
    public void findTop5Jobs() {
        Map<String, Long> findTop5Jobs = personRepository.findTop5Jobs();
        findTop5Jobs.forEach((k, v) -> System.out.println("jobs: " + k + " value: " + v));
    }

    @Test
    public void groupJobByCout() {
        Map<String, Long> groupJob = personRepository.groupJobByCount();
        groupJob.forEach((k, v) -> System.out.println("Job: " + k + " value:" + v));
    }

    @Test
    public void groupPeopleByCity() {
        Map<String, List<Person>> listPeopleinCity = personRepository.groupPeopleByCity();
        listPeopleinCity.forEach((k, v) -> System.out.println("City: " + k + " person: " + v));
    }

    @Test
    public void averageJobSalary() {
        Map<String, Double> averageJob = personRepository.averageJobSalary();
        averageJob.forEach((k, v) -> System.out.println("City: " + k + " -- " + "AverageJob: " + v));
    }

    @Test
    public void top5HighestSalaryCities() {
        Map<String, Double> top5HightSalary = personRepository.top5HighestSalaryCities();
        top5HightSalary.forEach((k, v) -> System.out.println("City: " + k + " - " + " Salary: " + v));
    }

    @Test
    public void findTopJobInCities() {
        Map<String, String> findTopJob = personRepository.findTopJobInCity();
        findTopJob.forEach((k, v) -> System.out.println("City: " + k + " - " + " Job: " + v));

    }

    @Test
    public void averageJobAge() {
        Map<String, Double> averageJob = personRepository.averageJobAge();
        averageJob.forEach((k, v) -> System.out.println("Job: " + k + " - " + " Age: " + v));
    }

    @Test
    public void averageCityAge() {
        Map<String, Double> averageJobCities = personRepository.averageJobAge();
        averageJobCities.forEach((k, v) -> System.out.println("City: " + k + " - " + " Age: " + v));
    }

}
