@RestController
@Autowired
private UserService userService;

@PostMapping("/forgot-password")
public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
        return ResponseEntity.notFound().build();
        }

        // GERAR LINK PARA RECUPERAÇÃO DO EMAIL

        return ResponseEntity.ok().build();
        }

@PostMapping("/reset-password")
public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        // Lógica para validar o token e redefinir a senha do usuário

        return ResponseEntity.ok().build();
        }
}
