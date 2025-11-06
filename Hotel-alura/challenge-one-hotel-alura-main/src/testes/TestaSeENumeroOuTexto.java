package testes;

public class TestaSeENumeroOuTexto {
    public static void main(String[] args) {
        String texto1 = "Texto";
        String texto2 = "1";
        String texto3 = "123.45";
        String texto4 = "123abc";
        String texto5 = "";
        String texto6 = null;

        // ✅ Array com todos os textos para testar
        String[] textos = {texto1, texto2, texto3, texto4, texto5, texto6};

        System.out.println("=== 🔢 TESTANDO SE É NÚMERO OU TEXTO ===\n");

        for (int i = 0; i < textos.length; i++) {
            String texto = textos[i];
            System.out.print("Texto " + (i + 1) + " '" + texto + "': ");

            if (texto == null) {
                System.out.println("❌ É nulo");
                continue;
            }

            if (texto.trim().isEmpty()) {
                System.out.println("❌ É vazio");
                continue;
            }

            try {
                int numeroInteiro = Integer.parseInt(texto);
                System.out.println("✅ É número inteiro: " + numeroInteiro);
            } catch (NumberFormatException e1) {
                try {
                    double numeroDecimal = Double.parseDouble(texto);
                    System.out.println("✅ É número decimal: " + numeroDecimal);
                } catch (NumberFormatException e2) {
                    System.out.println("❌ Não é número - É texto puro");
                }
            }
        }

        // ✅ Método alternativo usando expressão regular
        System.out.println("\n=== 🔍 MÉTODO ALTERNATIVO (REGEX) ===");
        for (String texto : textos) {
            System.out.print("Texto '" + texto + "': ");
            
            if (texto == null) {
                System.out.println("❌ É nulo");
            } else if (texto.matches("-?\\d+")) { // ✅ Apenas dígitos (inteiros)
                System.out.println("✅ É número inteiro");
            } else if (texto.matches("-?\\d+(\\.\\d+)?")) { // ✅ Números decimais
                System.out.println("✅ É número decimal");
            } else {
                System.out.println("❌ Não é número - É texto puro");
            }
        }
    }

    // ✅ Método utilitário para reutilização
    public static boolean ehNumeroInteiro(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // ✅ Método utilitário para números decimais
    public static boolean ehNumeroDecimal(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // ✅ Método que retorna o tipo do texto
    public static String verificarTipo(String texto) {
        if (texto == null) return "NULO";
        if (texto.trim().isEmpty()) return "VAZIO";
        if (texto.matches("-?\\d+")) return "INTEIRO";
        if (texto.matches("-?\\d+(\\.\\d+)?")) return "DECIMAL";
        return "TEXTO";
    }
}
