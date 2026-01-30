package Repositery;

import Domain.Costumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerRepository {
    private final Map<String, Costumer> costumersByID =
            new HashMap<String, Costumer>();

    public ArrayList<Costumer> findAll() {
        return new ArrayList<>(costumersByID.values());
    }

    public void save(Costumer c) {
        costumersByID.put(c.getId(), c);
    }
}
