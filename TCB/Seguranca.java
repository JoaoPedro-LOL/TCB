import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Seguranca {
    public static String sha256(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(texto.getBytes(StandardCharsets.UTF_8));
            StringBuilder resultado = new StringBuilder();
            for (byte b : bytes) resultado.append(String.format("%02x", b));
            return resultado.toString();
        } catch (Exception e) {
            throw new RuntimeException("Falha ao proteger a senha.", e);
        }
    }
}
