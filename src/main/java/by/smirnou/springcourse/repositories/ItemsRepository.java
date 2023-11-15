package by.smirnou.springcourse.repositories;

import by.smirnou.springcourse.models.Item;
import by.smirnou.springcourse.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item , Integer> {
    List<Item> findByItemName(String itemName); //ОБЯЗАТЕЛЬНО СОБЛЮДАТЬ НЭЙМИНГ

    // person.getItems()
    List<Item> findByOwner(Person owner);
}
