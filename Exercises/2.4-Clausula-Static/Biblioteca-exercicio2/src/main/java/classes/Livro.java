package classes;

/**
 *
 * @author guilh
 */
public class Livro {
    private String title;
    private String autor;
    private int pages;
    private static String libraryName = "Leitura";

    public Livro() {
    }
    public Livro(String title, String autor, int pages) {
        this.title = title;
        this.autor = autor;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }
    public String getAutor() {
        return autor;
    }
    public int getPages() {
        return pages;
    }
    public static String getLibraryName() {
        return libraryName;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }
    public static void setLibraryName(String newName) {
        libraryName = newName;
    }

    @Override
    public String toString() {
        return String.format("Livro: " + title + " - Biblioteca: " + libraryName);
    }
}
