package kz.geekprtnrs.queueinterviewtask.repository;

import kz.geekprtnrs.queueinterviewtask.entity.UniqueCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniqueCodeRepository extends JpaRepository<UniqueCode, Long> {
}
