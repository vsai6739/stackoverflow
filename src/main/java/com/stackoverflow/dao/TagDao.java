package com.stackoverflow.dao;

import com.stackoverflow.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagDao extends JpaRepository<Tag,Long> {
}
