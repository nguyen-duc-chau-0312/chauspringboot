package vn.techmaster.learncollection.repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import vn.techmaster.learncollection.model.Person;

@Repository
public class PersonRepositoryCSV implements PersonRepositoryInterface {
    private ArrayList<Person> people;

    @Autowired
    public PersonRepositoryCSV(@Value("${csvFile}") String csvFile) {
        people = new ArrayList<>();
        loadData(csvFile);
    }

    private void loadData(String csvFile) {
        try {
            File file = ResourceUtils.getFile("classpath:static/" + csvFile);
            CsvMapper mapper = new CsvMapper(); // Dùng để ánh xạ cột trong CSV với từng trường trong POJO
            CsvSchema schema = CsvSchema.emptySchema().withHeader(); // Dòng đầu tiên sử dụng làm Header
            ObjectReader oReader = mapper.readerFor(Person.class).with(schema); // Cấu hình bộ đọc CSV phù hợp với kiểu
            Reader reader = new FileReader(file);
            MappingIterator<Person> mi = oReader.readValues(reader); // Iterator đọc từng dòng trong file
            while (mi.hasNext()) {
                Person person = mi.next();
                people.add(person);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Person> getAll() {
        return people;
    }

    @Override
    public List<Person> sortPeopleByFullNameReversed() {
        return people.stream().sorted(Comparator.comparing(Person::getFullname).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<String> getSortedCities() {
        /*
         * return people.stream(). sorted(Comparator.comparing(Person::getCity)).
         * map(Person::getCity).collect(Collectors.toList());
         */
        return people.stream().map(Person::getCity).sorted().collect(Collectors.toList());
    }

    @Override
    public List<String> getSortedJobs() {
        return people.stream().map(Person::getJob).sorted().collect(Collectors.toList());
    }

    //Tìm 5 thành phố có số người thuộc danh sách sinh sống đông nhất từ vị trí thứ 5 đến vị trí thứ 1
    @Override
    public Map<String, Long> findTop5Citis() {

        //dem
        Map<String, Long> count = people.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.counting()));
        //sap xep
        Map<String, Long> arrangement = count.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue((o1, o2) -> (int) (o2 - o1)))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        //lay 5 phan tu
        int temp = 0;
        Map<String, Long> resultTemp = new HashMap<>();
        Set<String> keySet = arrangement.keySet();
        for (String key : keySet) {
            resultTemp.put(key, arrangement.get(key));
            temp++;
            if (temp == 5) {
                break;
            }
        }
        Map<String, Long> result = resultTemp.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue((o1, o2) -> (int) (o2 - o1)))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return result;
    }

    //Tìm 5 nghề có số lượng người làm nhiều nhất sắp xếp từ cao xuống thấp
    @Override
    public Map<String, Long> findTop5Jobs() {
        //dem
        Map<String, Long> count = people.stream().collect(Collectors.groupingBy(Person::getJob, Collectors.counting()));
        //sap xep
        Map<String, Long> arrangement = count.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue((o1, o2) -> (int) (o2.compareTo(o1))))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        //lay 5 phan tu
        int temp = 0;
        Map<String, Long> resultTemp = new HashMap<>();
        Set<String> keySet = arrangement.keySet();
        for (String key : keySet) {
            resultTemp.put(key, arrangement.get(key));
            temp++;
            if (temp == 5) {
                break;
            }
        }
        Map<String, Long> result = resultTemp.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue((o1, o2) -> (int) (o2.compareTo(o1))))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return result;
    }

    //Ở mỗi thành phố, tìm nghề nào có nhiều người làm nhất
    @Override
    public Map<String, String> findTopJobInCity() {
        Map<String, String> topJobByCities = people.stream().collect(Collectors.groupingBy(
                Person::getCity, Collectors.collectingAndThen(Collectors.groupingBy(Person::getJob,
                        Collectors.counting()),
                        map -> map.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey())));
        return topJobByCities;
    }

    //Nhóm các nghề nghiệp và đếm số người làm mỗi nghề
    @Override
    public Map<String, Long> groupJobByCount() {

        //Nhom so va dem
        Map<String, Long> countJob = people.stream().collect(Collectors.groupingBy(Person::getJob, Collectors.counting()));

        //Sap xep
        Map<String, Long> resultJob = countJob.entrySet().stream()
                .sorted(Map.Entry.comparingByValue((o1, o2) -> (o2.compareTo(o1))))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new
                ));
        return resultJob;
    }


    //Gom tất cả những người trong cùng một thành phố lại
    @Override
    public Map<String, List<Person>> groupPeopleByCity() {

        Map<String, List<Person>> result = people.stream().collect(Collectors.groupingBy
                (Person::getCity, Collectors.toList()));
        return result;
    }

    //Ứng với mỗi thành phố hãy tính độ tuổi trung bình
    @Override
    public Map<String, Double> averageCityAge() {
        //Nhom va dem
        Map<String, Double> resultAverageCity = people.stream().collect((Collectors.groupingBy(Person::getCity,
                Collectors.averagingInt(Person::getAge))));
        return resultAverageCity;
    }

    //Ứng với mỗi loại job hãy tính độ tuổi trung bình
    @Override
    public Map<String, Double> averageJobAge() {
//        //Nhom va tinh so luong
        Map<String, Double> resultAverage = people.stream().collect(
                Collectors.groupingBy(Person::getJob, Collectors.averagingInt(Person::getAge)));
        return resultAverage;
    }

    //Ứng với mỗi nghề nghiệp (job - String), tính mức lương trung bình (float)
    @Override
    public Map<String, Double> averageJobSalary() {
        //Nhom va tinh so luong
        Map<String, Double> count = people.stream().collect(Collectors.groupingBy(Person::getJob, Collectors.averagingLong(Person::getSalary)));

        //Sap xep
        Map<String, Double> resultAverageJob = count.entrySet()
                .stream().sorted(Map.Entry.comparingByValue((o1, o2) -> (o2.compareTo(o1))))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        return resultAverageJob;
    }

    @Override
    public List<String> find5CitiesHaveMostSpecificJob(String job) {
        // TODO Auto-generated method stub
        return null;
    }

    //Tìm 5 thành phố có mức lương trung bình cao nhất, sắp xếp từ cao xuống thấp
    @Override
    public Map<String, Double> top5HighestSalaryCities() {
        Map<String, Double> count = people.stream().collect(Collectors.groupingBy(Person::getCity,
                Collectors.averagingDouble(Person::getSalary)));

        Map<String, Double> arrangement = count.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue((o1, o2) -> (int) (o2.compareTo(o1))))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        //lay 5 phan tu
        int temp = 0;
        Map<String, Double> resultTemp = new HashMap<>();
        Set<String> keySet = arrangement.keySet();
        for (String key : keySet) {
            resultTemp.put(key, arrangement.get(key));
            temp++;
            if (temp == 5) {
                break;
            }
        }
        Map<String, Double> resultTop5 = resultTemp.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue((o1, o2) -> (int) (o2.compareTo(o1))))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        return resultTop5;
    }


}
