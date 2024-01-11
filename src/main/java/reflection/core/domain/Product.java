package reflection.core.domain;

import reflection.core.annotations.TagXML;

@TagXML("product")
public class Product {

    @TagXML("id")
    private final String id;
    @TagXML("name")
    private final String name;
    private final Double price;
    @TagXML("brand")
    private final String label;

    public Product(String id, String name, Double price, String label) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.label = label;
    }

    public String getId() {
        return id;
    }
}
