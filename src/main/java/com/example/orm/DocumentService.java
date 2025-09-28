package com.example.orm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository repository;

    // Assume you have an EmbeddingModel (e.g., from Spring AI or OpenAI client) to generate vectors
    public Document saveDocument(String content, float[] embedding) {
        Document doc = new Document(content, embedding);
        return repository.save(doc);
    }

    public List<Document> findSimilarDocuments(float[] queryEmbedding, int topK) {
        return repository.findSimilar(queryEmbedding, topK);
    }

    public List<Document> all(){
        return repository.findAll();
    }

    public Document getById(UUID id){
        return repository.findById(id).orElse(null);
    }
}
