package com.maistruks.portfolio.model.gallery;

public enum Style {

    ROMANTICISM("Romanticism"), CUBISM("Cubism"), POST_IMPRESSIONISM("Post-Impressionism"),
    SELF_PORTRAIT("Self portrait"), RENAISSANE("Renaissance"), HIGH_RENAISSANCE("High Renaissance"),
    RELIGIOUS_PAINTING("Religious painting"), PORTRAIT("Portrait"), CHRISTIAN_ART("Christian art"), TRONIE("Tronie"),
    BAROQUE("Baroque"), STILL_LIFE("Still life"), PASTORAL("Pastoral"), SURREALISM("Surrealism"),
    HISTORY_PAINTING("History painting"), REALISM("Realism"), GENRE_ART("Genre art"), FAUVISM("Fauvism"),
    ABSTRACT_ART("Abstact art"), IMPRESSIONISM("Impressionism"), SUPREMATISM("Suprematism"),
    LANDSCAPE_ART("Landscape art");

    private String style;

    private Style(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

}
