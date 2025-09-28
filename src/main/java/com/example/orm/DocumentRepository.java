package com.example.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {

    @Query(value = "SELECT * FROM documents ORDER BY embedding <=> (:queryEmbedding)::vector LIMIT :topK", nativeQuery = true)
    List<Document> findSimilar(@Param("queryEmbedding") float[] queryEmbedding, @Param("topK") int topK);
}