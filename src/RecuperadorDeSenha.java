public class RecuperadorDeSenha {
    private NotificationService notificationService;
    public RecuperadorDeSenha(NotificationService servicoEmail) {
        this.notificationService = notificationService ;
    }
    public void recuperar() {
        String link = "http: /techstore.com/reset?token=123";
        notificationService.enviar("Clique no link para resetar sua senha: " +
                link);
    }
    }
