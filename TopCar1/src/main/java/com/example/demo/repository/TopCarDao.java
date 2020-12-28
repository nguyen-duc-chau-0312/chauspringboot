package com.example.topcar.repository;

import com.example.topcar.model.TopCar;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.awt.print.Book;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;


public class TopCarDao extends Dao<TopCar> {
    public TopCarDao(String csvFile) {
        this.readCSV(csvFile);
    }

    @Override
    public void readCSV(String csvFile) {
        try {
            File file = ResourceUtils.getFile("classpath:static/" + csvFile);
            CsvMapper mapper = new CsvMapper(); // Dùng để ánh xạ cột trong CSV với từng trường trong POJO
            CsvSchema schema = CsvSchema.emptySchema().withHeader(); // Dòng đầu tiên sử dụng làm Header
            ObjectReader oReader = mapper.readerFor(Book.class).with(schema); // Cấu hình bộ đọc CSV phù hợp với kiểu
            Reader reader = new FileReader(file);
            MappingIterator<TopCar> mi = oReader.readValues(reader); // Iterator đọc từng dòng trong file
            while (mi.hasNext()) {
                TopCar topCar = mi.next();
                this.add(topCar);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<TopCar> getAll() {
        return collections;
    }

    @Override
    public Optional<TopCar> get(int id) {
        return collections.stream().filter(u -> u.getId() == id).findFirst();
    }

    @Override
    public void add(TopCar topCar) {
        //Cơ chế tự tăng
        int id;
        if (collections.isEmpty()) {
            id = 1;
        } else {
            TopCar lastCar = collections.get(collections.size() - 1);
            id = lastCar.getId() + 1;
        }
        topCar.setId(id);
        collections.add(topCar);
    }

    @Override
    public void update(TopCar topCar) {
        get(topCar.getId()).ifPresent(existcar -> {
            existcar.setModel(topCar.getModel());
            existcar.setManufacter(topCar.getManufacter());
            existcar.setPrice(topCar.getPrice());
            existcar.setSales(topCar.getSales());
        });
    }

    @Override
    public void deleteById(int id) {
        get(id).ifPresent(existcar -> collections.remove(existcar));
    }

    @Override
    public void delete(TopCar topCar) {
       deleteById(topCar.getId());
    }

    @Override
    public List<TopCar> searchByKeyword(String keyword) {
        //Tham khảo chi tiết ở đây nhé. Đây là Lambda Expression có từ Java 8.
        //https://www.baeldung.com/java-stream-filter-lambda
        return collections
                .stream()
                .filter(topCar -> topCar.matchWithKeyword(keyword))
                .collect(Collectors.toList());
    }
}
