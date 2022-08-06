package com.tareg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tareg.entity.CardDetails;

public interface CardDetailsRepos extends JpaRepository<CardDetails, String> {

}
