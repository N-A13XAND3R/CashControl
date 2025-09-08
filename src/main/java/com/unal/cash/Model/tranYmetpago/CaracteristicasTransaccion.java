package com.unal.cash.Model.tranYmetpago;
/*
Esta clase simplemente tiene un metodo el cual hace posible el registro de toda la información numérica y la retorna en una lista.
*/
import java.util.Scanner;
import java.util.InputMismatchException;

public class CaracteristicasTransaccion {
    
    public CaracteristicasTransaccion() {
    }
    
    public double[] registroCaracteristicas(){
        double monto;
        int tipoTransaccion;
        double intereses;
        double cashback;
        double descuento;
        double costoTransaccion;
        Scanner sc = new Scanner(System.in);
        
        while (true){
            System.out.print("1. Tarjeta débito\n2. Tarjeta crédito\n3. Efectivo\n4. Transferencia por aplicación\nPor favor ingrese la opción de su tipo de transacción: ");
            try{
                tipoTransaccion = sc.nextInt();
                if (tipoTransaccion < 1 || tipoTransaccion > 4){
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e){
                System.out.println("\nError: Debe ingresar un número válido. Vuelva a ingresarlo.");
                sc.nextLine(); // Limpiar el buffer del Scanner para evitar bucles infinitos
            }
        }
        
        while (true){
            System.out.print("Por favor ingrese el monto de la transacción (sin contar el decuento si tiene): ");
            try{
                monto = sc.nextDouble();
                if (monto < 0){
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e){
                System.out.println("\nError: Debe ingresar un número válido. Vuelva a ingresarlo.");
                sc.nextLine(); // Limpiar el buffer del Scanner para evitar bucles infinitos
            }
        }
        
        while (true){
            System.out.print("Por favor ingrese el porcentaje de intereses de la transacción (si tiene y sin el %): ");
            try{
                intereses = sc.nextDouble();
                if (intereses < 0){
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e){
                System.out.println("\nError: Debe ingresar un número válido. Vuelva a ingresarlo.");
                sc.nextLine(); // Limpiar el buffer del Scanner para evitar bucles infinitos
            }
        }
        
        while (true){
            System.out.print("Por favor ingrese el porcentaje de cashback de la transacción (si tiene y sin el %): ");
            try{
                cashback = sc.nextDouble();
                if (cashback < 0){
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e){
                System.out.println("\nError: Debe ingresar un número válido. Vuelva a ingresarlo.");
                sc.nextLine(); // Limpiar el buffer del Scanner para evitar bucles infinitos
            }
        }
        
        while (true){
            System.out.print("Por favor ingrese el porcentaje de descuento de la transacción (si tiene y sin el %): ");
            try{
                descuento = sc.nextDouble();
                if (descuento < 0){
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e){
                System.out.println("\nError: Debe ingresar un número válido. Vuelva a ingresarlo.");
                sc.nextLine(); // Limpiar el buffer del Scanner para evitar bucles infinitos
            }
        }
        
        while (true){
            System.out.print("Por favor ingrese el costo de la transacción: ");
            try{
                costoTransaccion = sc.nextDouble();
                if (costoTransaccion < 0){
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e){
                System.out.println("\nError: Debe ingresar un número válido. Vuelva a ingresarlo.");
                sc.nextLine(); // Limpiar el buffer del Scanner para evitar bucles infinitos
            }
        }
        
        double[] caracteristicasTransaccion = {tipoTransaccion, monto, intereses, cashback, descuento, costoTransaccion};
        
        return caracteristicasTransaccion;
    } 
}
