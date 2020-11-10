package br.com.torrentz.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * 
 * @author marcos
 *
 */
public class Data {

    /* Atributos */
    Calendar calendar = null;

    /* Métodos */
    /**
     * @param ano
     * @param mes
     * @param dia
     */
    public Data(int ano, int mes, int dia) {

            validaData(false, ano,mes,dia);
            calendar = new GregorianCalendar(ano, mes-1, dia);
    }
    /**
     * @param data
     */
    public Data(String data) {
            this(
                     Integer.parseInt(data.split("-")[0]),
                     Integer.parseInt(data.split("-")[1]),
                     Integer.parseInt(data.split("-")[2])
                    );		
    }
    /**
     * Data atual
     */
    public Data() {
            calendar = new GregorianCalendar();
    }
    /**
     * @param anoAtual
     * @param ano
     * @param mes
     * @param dia
     */
    public Data(boolean anoAtual, int ano, int mes, int dia) {

            validaData(anoAtual,ano,mes,dia);
            calendar = new GregorianCalendar(ano, mes-1, dia);
    }

    /**
     * @return
     */
    public String get() {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");	
            Date d = calendar.getTime();
            return df.format(d);		
    }
	
    /**
     * @param mes
     * @return
     */
    private static int quantidadeDias(int ano ,int mes){
        Calendar datas = new GregorianCalendar();
        datas.set(Calendar.YEAR, ano);
        datas.set(Calendar.MONTH, mes-1);//2
        int quantidadeDias = datas.getActualMaximum (Calendar.DAY_OF_MONTH);  
        return quantidadeDias ;
    }
    
    /**
     * 
     * @param ano
     * @param mes
     * @param dia
     * @throws RuntimException
     */
    private static void validaData(boolean anoAtual ,int ano, int mes, int dia) {
    	if(ano < 1) throw new RuntimeException("Ano inválido! o ano deve ser maior que 0!");
    	if(anoAtual) {
    		if(ano > Calendar.getInstance().get(Calendar.YEAR)) throw new RuntimeException("Ano inválido! o ano deve ser menor que "+ Calendar.getInstance().get(Calendar.YEAR) +"!");}	
		if(mes < 1) throw new RuntimeException("Mês inválido! o mês deve ser maior que 0!");
		if(mes > 12) throw new RuntimeException("Mês inválido! o mês deve ser menor que 12!");
		if(dia > quantidadeDias(ano, mes)) throw new RuntimeException("Dia inválido! este mês contêm "+ quantidadeDias(ano, mes) +" dias!");		
		if(dia < 1) throw new RuntimeException("Dia inválido! o mês inicia no dia 1!");
    }
}
