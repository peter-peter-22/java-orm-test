package com.example.orm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService service;

    @PostMapping
    public Document add(@RequestBody AddDocumentRequest request) {
        // Generate embedding via external service (e.g., OpenAI)
        float[] embedding = generateEmbedding(request.content()); // Implement this
        return service.saveDocument(request.content(), embedding);
    }

    @GetMapping("/similar")
    public List<Document> similar(@RequestParam String query, @RequestParam(defaultValue = "5") int topK) {
        float[] queryEmbedding = generateEmbedding(query);
        return service.findSimilarDocuments(queryEmbedding, topK);
    }

    @GetMapping("/all")
    public List<Document> all(){
        return service.all();
    }

    @GetMapping("/get/{id}")
    public Document get(@PathVariable String id){
        return service.getById(UUID.fromString(id));
    }

    private float[] generateEmbedding(String text) {
        int length = 5;
        float[] vector = new float[length];
        for (int i = 0; i < length; i++) {
            vector[i] = (float) Math.random();
        }
        return vector;
    }
}
