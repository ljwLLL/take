package com.hpower.util;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.hpower.exception.ApiException;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * Excel导出工具类
 * </p>
 *
 * @author yangyang.jiang
 * @date 2020/4/16
 * @since 1.0
 */
public class ExcelExportUtil {

    private static String[] IEBrowserSignals = {"MSIE", "Trident", "Edge"};

    /**
     * 判断是否是IE浏览器
     *
     * @param request
     * @return
     */
    public static boolean isMSBrowser(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        for (String signal : IEBrowserSignals) {
            if (userAgent.contains(signal)) return true;
        }
        return false;
    }


    /**
     * 03版本Excel导出
     *
     * @param title
     * @param sheet
     * @param pojoClass
     * @param datas
     * @param response
     */
    public static void exportHssfExcel(String title, String sheet, Class<?> pojoClass, Collection<?> datas, HttpServletRequest request, HttpServletResponse response) {
        Workbook workbook = cn.afterturn.easypoi.excel.ExcelExportUtil.exportExcel(new ExportParams(title, sheet, ExcelType.HSSF), pojoClass, datas);
        try {
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-disposition", "attachment;filename=" + getNormalCodeFileName("数据导出结果.xls", request));
            write(workbook, response.getOutputStream());
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }

    /**
     * 07版本Excel导出
     *
     * @param title
     * @param sheet
     * @param pojoClass
     * @param datas
     * @param response
     */
    public static void exportXssfExcel(String title, String sheet, Class<?> pojoClass, Collection<?> datas, HttpServletRequest request, HttpServletResponse response) {
        Workbook workbook = cn.afterturn.easypoi.excel.ExcelExportUtil.exportExcel(new ExportParams(title, sheet, ExcelType.XSSF), pojoClass, datas);
        try {
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-disposition", "attachment;filename=" + getNormalCodeFileName(title+".xlsx", request));
            write(workbook, response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    public static String getNormalCodeFileName(String fileName, HttpServletRequest request) {
        try {
            if (isMSBrowser(request)) {
                fileName = URLEncoder.encode(fileName, CharsetKit.UTF_8);
            } else {
                fileName = new String(fileName.getBytes(CharsetKit.UTF_8), CharsetKit.ISO_8859_1);
            }
        } catch (UnsupportedEncodingException e) {
            throw new ApiException(e);
        }
        return fileName;
    }


    public static <
            T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        try {
            ImportParams params = new ImportParams();
            params.setTitleRows(titleRows);
            params.setHeadRows(headerRows);
            List<T> list = null;
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void write(Workbook wb, OutputStream out) {
        try {
            if (null != out) {
                wb.write(out);
                out.flush();
            }
        } catch (IOException var11) {
            var11.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException var10) {
                    var10.printStackTrace();
                }
            }

        }

    }
}
