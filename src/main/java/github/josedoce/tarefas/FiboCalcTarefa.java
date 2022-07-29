package github.josedoce.tarefas;

import github.josedoce.PrimaryController;
import javafx.concurrent.Task;

public class FiboCalcTarefa extends Task<Long>{
  private int process = 0;
  private final int n;
  public FiboCalcTarefa(int n){
    this.n = n;
  }

  @Override
  protected Long call() throws Exception {
    
    updateMessage("     Processando...");
    long resultado = fibonacci(n);
    updateMessage("     Finalizado!");
    System.out.println("oussdkfskd");
    return resultado;
  }
  
  public long fibonacci(long numero){
    if(numero == 0 || numero == 1){
      return numero;
    }else{
      long result = fibonacci(numero - 1) + fibonacci(numero - 2);
      String value = PrimaryController.reference.get();
      PrimaryController.reference.setPlain(value+","+result);
      return result;
    }
  }
}
