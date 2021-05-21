package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public interface PersonDao {

    /**
     * this method allows to insert a person with a given id
     * @param id
     * @param name
     * @return
     */
    int insertPerson(int id, String name);

    /**
     * thia method allows to add a person without an id and the id is generated random
     * @param person
     * @return
     */
    default int insertPerson(Person person){
        Random rand=new Random();
        int upperbound = 1000;
        int id=rand.nextInt(upperbound);
        return insertPerson(id, person.getName());
    }

    List<Person> selectAllPeople();
    Optional<Person> selectPersonById(int id);

    /**
     * this method allows to delete a person knowing the id
     * @param id
     * @return
     */
    int deletePersonById(int id);

    /**
     * this method allows to update a person knowing the id
     * @param id
     * @param person
     * @return
     */
    int updatePersonById(int id, Person person);
}
