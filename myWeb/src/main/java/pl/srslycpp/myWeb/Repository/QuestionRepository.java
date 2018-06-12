package pl.srslycpp.myWeb.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.srslycpp.myWeb.Entity.Questions;

@Repository
public interface QuestionRepository extends CrudRepository<Questions, Long> {
}
