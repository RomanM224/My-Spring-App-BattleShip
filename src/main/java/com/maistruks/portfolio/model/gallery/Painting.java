package com.maistruks.portfolio.model.gallery;

public class Painting {

    private Integer id;
    private Integer year;
    private String name;
    private Style style;
    private byte[] image;
    private String imageBase64Encoded;
    
    public Painting() {
    }

    public Painting(Integer id, Integer year, String name, Style style, byte[] image) {
        this.id = id;
        this.year = year;
        this.name = name;
        this.style = style;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageBase64Encoded() {
        return imageBase64Encoded;
    }

    public void setImageBase64Encoded(String imageBase64Encoded) {
        this.imageBase64Encoded = imageBase64Encoded;
    }

    @Override
    public String toString() {
        return "Painting [id=" + id + ", year=" + year + ", name=" + name + ", style=" + style + "]";
    }
    
    
}
