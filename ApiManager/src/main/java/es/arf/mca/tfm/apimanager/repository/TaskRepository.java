package es.arf.mca.tfm.apimanager.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import es.arf.mca.tfm.apimanager.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {

	public Optional<Task> findByIdAndSymbol(long id, String symbol);
}
