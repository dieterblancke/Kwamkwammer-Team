package be.dieterblancke.kwamkwammer.other.paginator;

import lombok.Data;

@Data
public class TextPage implements Page {

    private final int page;
    private String message = "";

    public TextPage(final int page) {
        this.page = page;
    }

    public TextPage append( final String message) {
        this.message += message;
        return this;
    }

    public TextPage nl() {
        this.message += "\n";
        return this;
    }

    @Override
    public String toString() {
        return message;
    }
}
