package pl.srslycpp.myWeb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.srslycpp.myWeb.Entity.Questions;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long> {


}
