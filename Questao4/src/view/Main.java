import model.Pagamento;
import model.CartaoCredito;
import model.Boleto;
import model.Pix;


void main( String[] args){
        Pagamento[] pagamentos = {
                new CartaoCredito(),
                new Boleto(),
                new Pix()
        };

        double valor = 200.00;

        for (Pagamento p : pagamentos) {
            p.processar(valor);
        }
        new Pix().processar(-50);
}

