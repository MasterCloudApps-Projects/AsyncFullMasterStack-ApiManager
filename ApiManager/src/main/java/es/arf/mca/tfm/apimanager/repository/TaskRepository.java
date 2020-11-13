package es.arf.mca.tfm.apimanager.repository;

import org.springframework.data.repository.CrudRepository;

import es.arf.mca.tfm.apimanager.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {

}
