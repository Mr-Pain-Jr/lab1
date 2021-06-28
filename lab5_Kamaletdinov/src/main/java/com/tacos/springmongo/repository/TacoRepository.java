package com.tacos.springmongo.repository;
import com.tacos.springmongo.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, String> {
    @Override
    public void delete(Taco taco);
}
