package com.example.LatteListBack.Config;

import com.example.LatteListBack.Enums.EstadoUsuario;
import com.example.LatteListBack.Enums.TipoDeUsuario;
import com.example.LatteListBack.Models.Usuario;
import com.example.LatteListBack.Repositorys.UserRepository;
import com.example.LatteListBack.Services.CafeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CafeService cafeService;

    @Value("${admin.email:admin@lattelist.com}")
    private String superAdminEmail;

    @Value("${admin.password:admin123}")
    private String superAdminPassword;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder, CafeService cafeService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cafeService = cafeService;
    }

    @Override
    public void run(String... args) {
        inicializarAdmin();
        inicializarCafes();
    }

    private void inicializarAdmin() {
        if (userRepository.findByEmail(superAdminEmail).isEmpty()) {
            System.out.println("--- Creando super admin ---");
            Usuario admin = new Usuario();
            admin.setNombre("Super");
            admin.setApellido("Admin");
            admin.setEmail(superAdminEmail);
            admin.setPassword(passwordEncoder.encode(superAdminPassword));
            admin.setTipoDeUsuario(TipoDeUsuario.ADMIN);
            admin.setEstado(EstadoUsuario.ACTIVO);
            userRepository.save(admin);
            System.out.println("--- Admin creado: " + admin.getEmail() + " ---");
        }
    }

    private void inicializarCafes() {
        try {
            System.out.println("--- Verificando sincronización de cafés ---");
            cafeService.actualizarCafesDesdeApi();
        } catch (Exception e) {
            System.err.println("--- Error al inicializar cafés: " + e.getMessage() + " ---");
        }
    }
}