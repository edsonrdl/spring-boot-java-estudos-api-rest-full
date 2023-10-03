package com.api.restful.apirestfulspringbootjava.respositores;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.restful.apirestfulspringbootjava.models.Task;
import com.api.restful.apirestfulspringbootjava.models.projection.TaskProjection;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
// @Query(value="SELECT t FROM Task t WHERE t.user.id=:id")
//     List<Task> findByUser_Id(@Param ("id") Long id);
// @Query(value = "SELECT * FROM task t WHERE t.user_id=:id,nativeQuery=true")
// List<Task> findByUser_Id(@Param ("id") Long id);
List<TaskProjection> findByUser_Id(Long id); // Para buscar mais resumido pelo spring,não era necessário como feito no UserRepository
}
