package com.example.ComicReader.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="image")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;
    @Column(name = "name_image")
    private String name;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "size_image")
    private Long size;
    @Column(name = "content_type")
    private String contentType;
    @Column(name = "is_preview")
    private boolean isPreviewImage;
    @Column(name = "file_path")
    private String filePath;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Book book;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getFileName() {
        return this.fileName;
    }

    public Long getSize() {
        return this.size;
    }

    public String getContentType() {
        return this.contentType;
    }

    public boolean isPreviewImage() {
        return this.isPreviewImage;
    }



    public Book getBook() {
        return this.book;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
