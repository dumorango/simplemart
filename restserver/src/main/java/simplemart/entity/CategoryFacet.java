package simplemart.entity;

/**
 * Created by dumorango on 27/10/14.
 */
public class CategoryFacet {
    private String name;
    private Long count;


    public CategoryFacet(String name, Long count) {
        this.name = name;
        this.count = count;
    }

    public String getName() { return name; }

    public CategoryFacet() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "CategoryFacet{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
