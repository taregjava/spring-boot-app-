package com.tareg.repo;

import com.tareg.entity.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinRepository extends JpaRepository<Checkin,Long> {
}
