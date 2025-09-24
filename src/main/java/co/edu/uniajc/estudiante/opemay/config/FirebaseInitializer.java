package co.edu.uniajc.estudiante.opemay.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Configuration
public class FirebaseInitializer {
    
    private boolean firebaseInitialized = false;

    @PostConstruct
    public void initFirestore() throws IOException {
        // 🔍 DEBUG: Ver todas las variables de entorno relacionadas con Firebase
        System.out.println("=== FIREBASE DEBUG INFO ===");
        System.out.println("FIREBASE_CONFIG_01 exists: " + (System.getenv("FIREBASE_CONFIG_01") != null));
        System.out.println("FIREBASE_CONFIG exists: " + (System.getenv("FIREBASE_CONFIG") != null));
        
        // Ver todas las variables que contengan "FIREBASE"
        System.out.println("All FIREBASE env vars:");
        System.getenv().entrySet().stream()
            .filter(entry -> entry.getKey().toUpperCase().contains("FIREBASE"))
            .forEach(entry -> System.out.println("  " + entry.getKey() + " = " + 
                (entry.getValue().length() > 50 ? entry.getValue().substring(0, 50) + "..." : entry.getValue())));
        
        // 🔑 Trae el secret como string
        String firebaseConfig = System.getenv("FIREBASE_CONFIG_01");
        
        System.out.println("firebaseConfig is null: " + (firebaseConfig == null));
        System.out.println("firebaseConfig is blank: " + (firebaseConfig != null ? firebaseConfig.isBlank() : "N/A"));
        if (firebaseConfig != null) {
            System.out.println("firebaseConfig length: " + firebaseConfig.length());
            System.out.println("firebaseConfig first 100 chars: " + 
                firebaseConfig.substring(0, Math.min(100, firebaseConfig.length())));
        }
        
        InputStream serviceAccount;
        if (firebaseConfig != null && !firebaseConfig.isBlank()) {
            System.out.println("✅ Using Firebase config from environment variable");
            serviceAccount = new ByteArrayInputStream(firebaseConfig.getBytes(StandardCharsets.UTF_8));
        } else {
            System.out.println("⚠️ Firebase config not found in env, trying local file");
            serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase-key.json");
        }
        
        if (serviceAccount == null) {
            System.out.println("❌ No se encontró configuración de Firebase. Saltando inicialización...");
            this.firebaseInitialized = false;
            return;
        }
        
        // ✅ Evita IllegalStateException en tests o múltiples contextos
        System.out.println("Current FirebaseApp instances: " + FirebaseApp.getApps().size());
        if (FirebaseApp.getApps().isEmpty()) {
            try {
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();
                FirebaseApp.initializeApp(options);
                System.out.println("✅ Firebase inicializado correctamente");
                this.firebaseInitialized = true;
            } catch (Exception e) {
                System.out.println("❌ Error inicializando Firebase: " + e.getMessage());
                e.printStackTrace();
                this.firebaseInitialized = false;
            }
        } else {
            System.out.println("ℹ️ Firebase ya estaba inicializado, se omite");
            this.firebaseInitialized = true;
        }
        
        System.out.println("=== END FIREBASE DEBUG ===");
    }

    @Bean
    public Firestore firestore() {
        if (!firebaseInitialized) {
            System.out.println("⚠️ Firebase no está inicializado. Retornando null para Firestore bean.");
            return null; // O puedes lanzar una excepción personalizada
        }
        
        try {
            return FirestoreClient.getFirestore();
        } catch (Exception e) {
            System.out.println("❌ Error obteniendo Firestore client: " + e.getMessage());
            return null;
        }
    }
}
