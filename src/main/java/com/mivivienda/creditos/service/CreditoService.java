package com.mivivienda.creditos.service;

import com.mivivienda.creditos.model.Credito;
import com.mivivienda.creditos.repository.CreditoRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;



@Service
public class CreditoService {

    private final CreditoRepository repository;

    public CreditoService(CreditoRepository repository) {
        this.repository = repository;
    }

    public Credito registrarCredito(Credito credito) {
        return repository.save(credito);
    }

    public List<Credito> listarCreditos() {
        return repository.findAll();
    }

    public List<Credito> importarExcel(MultipartFile file) throws Exception {
        List<Credito> creditos = new ArrayList<>();

        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            // Suponiendo que la primera fila es la cabecera:
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Credito credito = new Credito();
                // Asumimos que las celdas están en el siguiente orden:
                // 0: ubigeo, 1: fechaDesembolso, 2: producto, 3: departamento, 4: provincia, 5: distrito,
                // 6: ifi, 7: tipoIfi, 8: montoCredito, 9: montoCuotaInicial, 10: plazos,
                // 11: tasa, 12: montoValorVivienda, 13: fechaCorte

                credito.setFechaDesembolso(getStringCellValue(row.getCell(0)));
                credito.setProducto(getStringCellValue(row.getCell(1)));
                credito.setDepartamento(getStringCellValue(row.getCell(2)));
                credito.setProvincia(getStringCellValue(row.getCell(3)));
                credito.setDistrito(getStringCellValue(row.getCell(4)));
                credito.setUbigeo(getStringCellValue(row.getCell(5)));
                credito.setIfi(getStringCellValue(row.getCell(6)));
                credito.setTipoIfi(getStringCellValue(row.getCell(7)));
                credito.setMontoCredito(getNumericCellValue(row.getCell(8)));
                credito.setMontoCuotaInicial(getNumericCellValue(row.getCell(9)));
                credito.setPlazos((int)getNumericCellValue(row.getCell(10)));
                credito.setTasa(getNumericCellValue(row.getCell(11)));
                credito.setMontoValorVivienda(getNumericCellValue(row.getCell(12)));
                credito.setFechaCorte(getStringCellValue(row.getCell(13)));


                System.out.println("Fila " + i +
                        " => fechaDesembolso: " + credito.getFechaDesembolso() +
                        ", producto: " + credito.getProducto() +
                        ", departamento: " + credito.getDepartamento() +
                        ", provincia: " + credito.getProvincia() +
                        ", distrito: " + credito.getDistrito()  +
                        ", ubigeo: " + credito.getUbigeo() +
                        ", ifi: " + credito.getIfi() +
                        ", tipoIfi: " + credito.getTipoIfi() +
                        ", montoCredito: " + credito.getMontoCredito() +
                        ", montoCuotaInicial: " + credito.getMontoCuotaInicial() +
                        ", plazos: " + credito.getPlazos() +
                        ", tasa: " + credito.getTasa() +
                        ", montoValorVivienda: " + credito.getMontoValorVivienda() +
                        ", fechaCorte: " + credito.getFechaCorte() );

                creditos.add(credito);
            }
        }


        // Guarda en bloque y retorna la lista importada
        return repository.saveAll(creditos);
    }

    private String getStringCellValue(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                // Convertir a String con tu formato preferido, ej. yyyyMMdd
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                return sdf.format(cell.getDateCellValue());
            } else {
                // Caso numérico general (para ubigeo numérico, etc.)
                return String.valueOf((long) cell.getNumericCellValue());
            }
        }
        return null;
    }


    private double getNumericCellValue(Cell cell) {
        if(cell == null) return 0;
        if(cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if(cell.getCellType() == CellType.STRING) {
            try {
                return Double.parseDouble(cell.getStringCellValue().trim());
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }
}
