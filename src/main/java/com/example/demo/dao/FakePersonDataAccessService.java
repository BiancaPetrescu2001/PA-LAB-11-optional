package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(@Param("id") int id, @Param("name") String name) {
        DB.add(new Person(id, name));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(int id) {
        return DB.stream()
                .filter(person->person.getId()==id)
                .findFirst();
    }

    @Override
    public int deletePersonById(@Param("int") int id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if(personMaybe.isPresent()){
            DB.remove(personMaybe.get());
            return 1;
        }
        return 0;
    }


    @Override
    public int updatePersonById(@Param("id") int id, Person update) {
        return selectPersonById(id)
                .map(person->{
                    int indexOfPersonUpdate = DB.indexOf(person);
                    if(indexOfPersonUpdate>=0){
                        DB.set(indexOfPersonUpdate, new Person(id,update.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

}
