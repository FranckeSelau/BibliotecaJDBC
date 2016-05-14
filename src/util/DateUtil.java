package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe Utilitária para manipular Datas do Sistema
 *
 * @author lhries / Francke
 * @since JDK 1.0
 */
public class DateUtil {
    
    public static Date stringToDate(String data) throws ParseException
    {
        return(new SimpleDateFormat("dd/MM/yyyy").parse(data));
    }
    
     public static Date stringToYear(String data) throws ParseException // string para ano
    {
        return(new SimpleDateFormat("yyyy").parse(data));
    }

    public static Date stringToDateHour(String data) throws ParseException
    {
        return(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(data));
    }
    
    public static String dateToString(Date data){
        return(new SimpleDateFormat("dd/MM/yyyy").format(data));
    }
    
    public static String yearToString(Date data){
        return(new SimpleDateFormat("yyyy").format(data)); // string para ano
    }

    public static String dateHourToString(Date data){
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataString = formatador.format(data);
        return(dataString);
    }
    
    public static boolean verificaData(String data)
    {
       return(data.matches("\\d{2}/\\d{2}/\\d{4}"));
    }
    
    public static boolean verificaAno(String data) // verifica ano
    {
       return(data.matches("\\d{4}"));
    }
    
    public static boolean verificaDataHora(String data)
    {
       return(data.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}"));
    }
}
