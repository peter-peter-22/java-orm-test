package com.example.orm;

import jakarta.persistence.*;
import org.hibernate.annotations.Array;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "documents")
public class Document {

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float[] getEmbedding() {
        return embedding;
    }

    public void setEmbedding(float[] embedding) {
        this.embedding = embedding;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.VECTOR)
    @Array(length = 5)
    private float[] embedding;

    public Document() {}

    public Document(String content, float[] embedding) {
        this.content = content;
        this.embedding = embedding;
    }
}