package com.example.topcarhomework.repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.topcarhomework.model.TopCar;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.springframework.util.ResourceUtils;


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
            ObjectReader oReader = mapper.readerFor(TopCar.class).with(schema); // Cấu hình bộ đọc CSV phù hợp với kiểu
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
            TopCar lastBook = collections.get(collections.size() - 1);
            id = lastBook.getId() + 1;
        }
        topCar.setId(id);
        collections.add(topCar);
    }

    @Override
    public void update(TopCar topCar) {
        get(topCar.getId()).ifPresent(existtopcar -> {
            existtopcar.setManufacter(topCar.getManufacter());
            existtopcar.setModel(topCar.getModel());
            existtopcar.setPrice(topCar.getPrice());
        });
    }

    @Override
    public void deleteById(int id) {
        get(id).ifPresent(existtopcar -> collections.remove(existtopcar));
    }


    @Override
    public void delete(TopCar topCar) {
        deleteById(topCar.getId());
    }

    @Override
    public List<TopCar> searchByKeyword(String keyword) {
        return collections
                .stream()
                .filter(topCar -> topCar.matchWithKeyword(keyword))
                .collect(Collectors.toList());
    }


}