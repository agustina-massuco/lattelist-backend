package com.example.LatteListBack.Config;

import com.example.LatteListBack.Enums.EstadoUsuario;
import com.example.LatteListBack.Enums.TipoDeUsuario;
import com.example.LatteListBack.Models.Usuario;
import com.example.LatteListBack.Repositorys.UserRepository;
import com.example.LatteListBack.Services.CafeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CafeService cafeService;
    public static final String SUPER_ADMIN_EMAIL = "admin@lattelist.com";


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
        if (userRepository.findByEmail(SUPER_ADMIN_EMAIL).isEmpty()) {
            System.out.println("--- Creando super admin ---");
            Usuario admin = new Usuario();
            admin.setNombre("Super");
            admin.setApellido("Admin");
            admin.setEmail(SUPER_ADMIN_EMAIL);
            admin.setPassword(passwordEncoder.encode("admin123"));
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