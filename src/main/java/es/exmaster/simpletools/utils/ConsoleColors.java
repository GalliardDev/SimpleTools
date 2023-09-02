package es.exmaster.simpletools.utils;

public enum ConsoleColors {
    // Reset
    RESET("\033[0m"),

    // Colores de texto
    BLACK("\033[0;30m"),    
    RED("\033[0;31m"),      
    GREEN("\033[0;32m"),    
    YELLOW("\033[0;33m"),   
    BLUE("\033[0;34m"),     
    PURPLE("\033[0;35m"),   
    CYAN("\033[0;36m"),     
    WHITE("\033[0;37m"),

    // Colores de texto en negrita
    BOLD_BLACK("\033[1;30m"),    
    BOLD_RED("\033[1;31m"),      
    BOLD_GREEN("\033[1;32m"),    
    BOLD_YELLOW("\033[1;33m"),   
    BOLD_BLUE("\033[1;34m"),     
    BOLD_PURPLE("\033[1;35m"),   
    BOLD_CYAN("\033[1;36m"),     
    BOLD_WHITE("\033[1;37m"),

    // Colores de fondo
    BACKGROUND_BLACK("\033[40m"),    
    BACKGROUND_RED("\033[41m"),      
    BACKGROUND_GREEN("\033[42m"),    
    BACKGROUND_YELLOW("\033[43m"),   
    BACKGROUND_BLUE("\033[44m"),     
    BACKGROUND_PURPLE("\033[45m"),   
    BACKGROUND_CYAN("\033[46m"),     
    BACKGROUND_WHITE("\033[47m");

    private final String code;

    ConsoleColors(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
