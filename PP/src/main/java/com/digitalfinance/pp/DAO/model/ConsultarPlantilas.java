/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalfinance.pp.DAO.model;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.digitalfinance.pp.DAO.entity.Plantilla;

public class ConsultarPlantilas {
    private static final String CSV_FILE_PATH;
    static {
        // Inicializar la variable en un bloque estático
        CSV_FILE_PATH = obtenerDirectorioActual();
    }
    public static ArrayList<Plantilla> leerCSV() throws CsvValidationException {
        ArrayList<Plantilla> listaPlantilla = new ArrayList<>();

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(CSV_FILE_PATH)).build()) {
            String[] nextRecord;
            int indice=0;
            while ((nextRecord = csvReader.readNext()) != null) {
                // Suponemos que las columnas son: Nombre, Edad
                int indicePlantilla=indice;
                String nombrePlantilla=nextRecord[0];
                String descPlantilla=nextRecord[1];
                String agente=nextRecord[2];
                
                Plantilla plantilla = new Plantilla();
                plantilla.setIndicePlantilla(indicePlantilla);
                plantilla.setNombrePlantilla(nombrePlantilla);
                plantilla.setDescPlantilla(descPlantilla);
                plantilla.setAgente(agente);
                listaPlantilla.add(plantilla);
                indice++;
            }
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Error :" +ex);
        }
        return listaPlantilla;
    }
    
    public static void agregarCSV(ArrayList<Plantilla> plantilla) {
        try (CSVWriter csvWriter = (CSVWriter) new CSVWriterBuilder(new FileWriter(CSV_FILE_PATH)).build()) {
            for (Plantilla pl : plantilla) {
                String[] data = {pl.getNombrePlantilla(),pl.getDescPlantilla(),pl.getAgente()};
                csvWriter.writeNext(data);
            }
        } catch (IOException ex) {
            System.out.println("Error :" +ex);
        }
    }
    
    public static void guardarCSV(ArrayList<Plantilla> plantilla) {
        try (CSVWriter csvWriter = (CSVWriter) new CSVWriterBuilder(new FileWriter(CSV_FILE_PATH)).build()) {
            for (Plantilla pl : plantilla) {
                String[] data = {pl.getNombrePlantilla(),pl.getDescPlantilla(),pl.getAgente()};
                csvWriter.writeNext(data);
            }
        } catch (IOException ex) {
            System.out.println("Error :" +ex);
        }
    }

    public static void modificarRegistro(ArrayList<Plantilla> plantilla, int indice, Plantilla nuevaPlantilla) {
        if (indice >= 0 && indice < plantilla.size()) {
            plantilla.set(indice, nuevaPlantilla);
            guardarCSV(plantilla);
            System.out.println("Registro modificado exitosamente.");
        } else {
            System.out.println("Índice fuera de rango.");
        }
    }
    
    public static void eliminarRegistro(ArrayList<Plantilla> plantilla, int indice) {
        if (indice >= 0 && indice < plantilla.size()) {
            plantilla.remove(indice);
            guardarCSV(plantilla);
            System.out.println("Registro eliminado exitosamente.");
        } else {
            System.out.println("Índice fuera de rango.");
        }
    }
    
    public static String obtenerDirectorioActual() {
        String directorioActual = System.getProperty("user.dir");
        //Descomentar cuando se este dentro de netbeans
        String rutaCsv=directorioActual+"\\src\\main\\java\\com\\digitalfinance\\DAO\\model\\MisPlantillas.csv";
        //Descomentar cunado se despliegue la apk
//        String rutaCsv=directorioActual+"\\MisPlantillas.csv";
        return rutaCsv;
    }
}

