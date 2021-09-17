package ai;

public interface Type {
    String NOMINAL="NOMINAL";
    String NUMERIC="NUMERIC";
    String STRING="STRING";
    /**
     * @Param for example "yyyy-MM-dd HH:mm:ss"
     **/
    static String DATE(String format)
    {
        return "DATE "+format;
    }
}
