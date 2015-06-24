package com.wonders.asset.web.action;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.dom4j.DocumentException;

import com.opensymphony.xwork2.Action;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.AreaInfo;
import com.wonders.asset.model.AssetType;
import com.wonders.asset.model.BorrowAsset;
import com.wonders.asset.model.HouseAsset;
import com.wonders.asset.model.LandAsset;
import com.wonders.asset.model.Line;
import com.wonders.asset.model.Station;
import com.wonders.asset.service.AssetTypeService;
import com.wonders.asset.service.HouseAssetService;
import com.wonders.asset.service.LineService;
import com.wonders.asset.service.ProjectService;
import com.wonders.asset.service.StationService;
import com.wonders.framework.util.ServiceProvider;
import com.wonders.utils.ExcelUtil;

public class HouseAssetAction extends AbstractBaseAction {

    private HSSFCellStyle numbericCss;
    private HSSFCellStyle defaultCss;
    private HSSFCellStyle titleCss;

    /**
     * 查询房屋信息
     *
     * @throws Exception
     */
    public String inquery() throws Exception {
        Map<String, String> filter = createFilterMap();
        Map<String, String> houseAssetFilter = new HashMap<String, String>();
        Map<String, String> areaInfoFilter = new HashMap<String, String>();

        for (Map.Entry<String, String> entry : filter.entrySet()) {
            if ("useType".equals(entry.getKey())
                    || "takeOverDep".equals(entry.getKey())) {
                areaInfoFilter.put(entry.getKey(), entry.getValue());
            } else {
                houseAssetFilter.put(entry.getKey(), entry.getValue());
            }
        }
        int pageNo = NumberUtils.toInt(getRequestParameter("pageNo"),
                1);
        int pageSize = NumberUtils.toInt(getRequestParameter("pageSize"), 10);
        Map<String, String> sort = createSortMap();
        Pagination<HouseAsset> houseAssets = ServiceProvider.getService(
                HouseAssetService.class).findHouseAssetAreaInfo(
                houseAssetFilter, areaInfoFilter, sort, pageNo, pageSize);
        List<Line> lines = ServiceProvider.getService(
                LineService.class).findAllByPublish("1");
        for(HouseAsset houseAsset : houseAssets.getResult()){
        	for(Line line : lines){
        		if(line.getCode().equals(houseAsset.getLine())){
        			houseAsset.setLine(line.getShortName());
        			break;
        		}
        	}
        }
        
        JsonConfig jsonConfig = basicJsonCfg.copy();
        String[] excusions = new String[] {};
        jsonConfig.registerPropertyExclusions(BorrowAsset.class, excusions);
//		renderJson(houseAssets.getResult(), houseAssets.getTotalCount(),
//				jsonConfig);
        request.setAttribute("records", houseAssets.getResult());

        Map<String,Integer> pageMap = new HashMap<String, Integer>();
        pageMap.put("pageNo", houseAssets.getPageNo());
        pageMap.put("pageSize", pageSize);
        pageMap.put("totalPages", houseAssets.getTotalPages());
        pageMap.put("totalCount", (int)houseAssets.getTotalCount());
        request.setAttribute("pageInfo", pageMap);

        request.setAttribute("searchMap", filter);

        return Action.SUCCESS;
    }


    /**
     * 用于显示房屋资源的详细信息
     *
     * @throws DocumentException
     */
    public String showView() throws DocumentException {
        String id = request.getParameter("id");
        HouseAsset houseAsset = ServiceProvider.getService(
                HouseAssetService.class).findByPrimaryKey(id);
        Line line = ServiceProvider.getService(LineService.class).findById(houseAsset.getLine());
        Station station = ServiceProvider.getService(StationService.class).findById(houseAsset.getStation());
        AssetType assetType = ServiceProvider.getService(AssetTypeService.class).findByAllCode(houseAsset.getAssetType());
        String locationNo = houseAsset.getLocationNo();
        Line lineTmp = null;
        Station stationTmp = null;
        if(StringUtils.isNotBlank(locationNo)){
            lineTmp = ServiceProvider.getService(LineService.class).findById(locationNo.substring(0, 2));
            stationTmp = ServiceProvider.getService(StationService.class).findById(locationNo.substring(2));
        }

        String location = "";
        String lineLoc = "";
        String stationLoc = "";
        if(lineTmp==null){
            location += lineLoc + "-";
        }else if(stationTmp==null){
            location += stationLoc;
        }else{
            location = lineTmp.getName()+"-"+stationTmp.getName();
        }

        houseAsset.setLine(line!=null ? line.getName() : "");
        houseAsset.setStation(station!=null ? station.getName() : "");
        houseAsset.setAssetType(assetType!=null ? assetType.getName() : "");
        houseAsset.setLocationNo(location);
        request.setAttribute("houseAsset", houseAsset);
        return Action.SUCCESS;
    }

    /**
     * 显示房屋报表界面
     *
     * @return
     */
    public String toHouseAsset() {
        String param = request.getParameter("reportType");
        request.setAttribute("reportType", param);
        return Action.SUCCESS;
    }

    /**
     * 查询出房屋数据后导出数据报表
     *
     * @throws DocumentException
     * @throws IOException
     */
    public String exportDataReport() throws DocumentException, IOException{
        Map<String, String> filter = createFilterMap();
        Map<String, String> houseAssetFilter = new HashMap<String, String>();
        Map<String, String> areaInfoFilter = new HashMap<String, String>();

        for (Map.Entry<String, String> entry : filter.entrySet()) {
            if ("useType".equals(entry.getKey())
                    || "takeOverDep".equals(entry.getKey())) {
                areaInfoFilter.put(entry.getKey(), entry.getValue());
            } else {
                houseAssetFilter.put(entry.getKey(), entry.getValue());
            }
        }
        int pageNo = NumberUtils.toInt(getRequestParameter("pageNo"),
                1);
        int pageSize = NumberUtils.toInt(getRequestParameter("pageSize"), Integer.MAX_VALUE);
        Map<String, String> sort = createSortMap();
        Pagination<HouseAsset> houseAssets = ServiceProvider.getService(
                HouseAssetService.class).findHouseAssetAreaInfo(
                houseAssetFilter, areaInfoFilter, sort, pageNo, pageSize);

        Integer totalRows = 0;
        for(HouseAsset tmp : houseAssets.getResult()){
            totalRows += tmp.getAreaInfos().size();
        }
        String[] title = {"线路","建设类项目名称","车站、基地、变电所等","房屋建筑名称","房屋分类","地址","建筑面积(平方米)","层数(地上、地下)",
                "产权证或竣工证号或规划许可证","房屋用途","使用单位","实际用房面积(平方米)","其中：轨道公安/消防部门/其他","所在楼层","备注"};
        exportExcel(houseAssets.getResult(), "房屋数据报表.xls", title, totalRows);
        return Action.NONE;
    }

    /**
     * 线路商业开发用地报表
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void lineBuildAreaReport() throws DocumentException, IOException {
        String option = request.getParameter("option");
        List<Object[]> list =
                ServiceProvider.getService(HouseAssetService.class).getLineBuildAreaReport();
        String name = "线路商业开发用地报表.xls";
        String[] title = new String[]{"序号", "线路", "用于商业经营开发的面积(平米)"};
        int numIndex = 0;
        if ("export".equals(option)) {
            List<Object[]> resList = new ArrayList<Object[]>();
            for (Object[] obj : list) {
                Object[] tmp = new Object[3];
                tmp[0] = ++numIndex;
                tmp[1] = obj[0];
                tmp[2] = String.format("%.2f", obj[1]);
                resList.add(tmp);
            }
            ExcelUtil util = new ExcelUtil();
            util.exportExcel(resList, name, title, response);
        } else if ("query".equals(option)) {
            List<Map> result = new ArrayList<Map>();
            Map resMap = new HashMap();
            for (Object[] obj : list) {
                Map map = new HashMap();
                map.put("index", ++numIndex);
                map.put("line", obj[0]);
                map.put("area", obj[1] == null ?0:String.format("%.2f", obj[1]));
                result.add(map);
            }
            resMap.put("Result", "OK");
            resMap.put("Records", result);
            renderJson(resMap);
        }

    }

    /**
     * 线路报表
     *
     * @throws DocumentException
     * @throws IOException
     */
    public void lineReport() throws DocumentException, IOException {
        String option = request.getParameter("option");
        List<Object[]> list =
                ServiceProvider.getService(HouseAssetService.class).getLineStationAreaReport();
        String name = "线路报表.xls";
        String[] title = new String[]{"线路", "建设类项目", "车站、基地、变电所、控制中心等", "建筑面积(适用整栋楼或整座车站)(平方米)", "房屋用途类别", "使用单位", "实际用房面积(平方米)"};
        int numIndex = 0;
        if ("export".equals(option)) {
            ExcelUtil util = new ExcelUtil();
            util.exportExcel(list, name, title, response);
        } else if ("query".equals(option)) {
            List<Map> result = new ArrayList<Map>();
            Map resMap = new HashMap();
            for (Object[] obj : list) {
                Map map = new HashMap();
                map.put("index", ++numIndex);
                map.put("line", obj[0]);
                map.put("station", obj[1]);
                map.put("area", obj[2]);
                map.put("builderProject", obj[3]);
                map.put("useType", obj[4]);
                map.put("takeOverDep", obj[5]);
                map.put("reallyArea", obj[6]);
                result.add(map);
            }

            resMap.put("Result", "OK");
            resMap.put("Records", result);
            renderJson(resMap);
        }
    }

    /**
     * 使用单位报表
     *
     * @throws DocumentException
     * @throws IOException
     */
    public String useTypeReport() throws DocumentException, IOException {
        String option = request.getParameter("option");
        String reportType = request.getParameter("reportType");
        String lineType = request.getParameter("lineType");

        List<Object[]> list = new ArrayList<Object[]>();
        List<Map<String, String>> shortNames = new ArrayList<Map<String, String>>();


        if ("0".equals(lineType)) {
            list = ServiceProvider.getService(HouseAssetService.class).getUseTypeReport();
            shortNames = ServiceProvider.getService(ProjectService.class).findShortNameAndLineCode();
        } else if ("1".equals(lineType)) {
            list = ServiceProvider.getService(HouseAssetService.class).getUseTypeReportByBigLine();
            List<Line> lines = ServiceProvider.getService(LineService.class).findAll();
            for (Line line : lines) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("shortName", line.getShortName());
                map.put("id", line.getId());
                shortNames.add(map);
            }
        }
        Map<String,String> lineMap = getLineMap(shortNames);
        String[] titleList = getTitleList(shortNames);
        List resultList =  getUseTypeReportData(list, lineMap);
        //此报表由于内容比较特殊，使用特制的Excel导出函数
        if ("export".equals(option)) {
            String name = "使用单位报表.xls";
            exportExcel(resultList, name, titleList, response);
        } else if ("query".equals(option)) {

            request.setAttribute("reportType", reportType);
            request.setAttribute("lineType", lineType);

            request.setAttribute("result", resultList);
            request.setAttribute("title", titleList);
            return Action.SUCCESS;
        }
        return Action.NONE;
    }

    private Map<String,String> getLineMap( List<Map<String, String>> shortNames){
        Map<String, String> lineMap = new HashMap<String, String>();
        for (Integer i = 0; i < shortNames.size(); i++) {
            Map<String, String> tmp = shortNames.get(i);
            String[] value = new String[2];
            int count = 0;
            for (String str : tmp.values()) {
                value[count++] = str;
            }
            lineMap.put(value[1], i.toString());
        }
        return lineMap;
    }

    private String[] getTitleList(List<Map<String, String>> shortNames){
        String[] title1 = new String[]{"序号", "使用单位", "用房性质"};
        String[] title2 = new String[shortNames.size()];
        for (int i = 0; i < shortNames.size(); i++) {
            Map<String, String> map = shortNames.get(i);
            title2[i] = map.get("shortName");
        }
        String[] title = new String[title1.length + title2.length + 1];
        System.arraycopy(title1, 0, title, 0, title1.length);
        System.arraycopy(title2, 0, title, title1.length, title2.length);
        title[title.length - 1] = "小计";
        return title;
    }

    /**
     * 获取使用单位报表的报表数据
     *
     * @param list
     * @throws IOException
     */
    public List getUseTypeReportData(List<Object[]> list, Map<String, String> lineMap) throws IOException {
        HashMap<String, HashMap> results = new HashMap<String, HashMap>();
        ArrayList resultList = new ArrayList();
        for (Object[] objects : list) {
            HashMap resultMap = new HashMap();
            String key = (String) objects[0] + (objects[1] == null ? "" : (String) objects[1]);
            if (results.containsKey(key)) {
                resultMap = results.get(key);
                ArrayList lineList = (ArrayList) resultMap.get("line");
                if (lineMap.containsKey(objects[2])) {
                    lineList.set(Integer.parseInt(lineMap.get(objects[2])), objects[3]);
                }
            } else {
                resultMap = new HashMap();
                resultMap.put("company", objects[0]);
                resultMap.put("houseType", objects[1]);
                ArrayList<BigDecimal> lineList = new ArrayList<BigDecimal>();
                int lineNum = lineMap.size();
                for (int i = 0; i < lineNum; i++) {
                    lineList.add(new BigDecimal(0));
                }
                if (lineMap.containsKey(objects[2])) {
                    lineList.set(Integer.parseInt(lineMap.get(objects[2])), (BigDecimal) objects[3]);

                }
                resultMap.put("line", lineList);
                results.put(key, resultMap);
                resultList.add(resultMap);
            }


            BigDecimal price = new BigDecimal(0);
            if (resultMap.containsKey("xj")) {
                price = (BigDecimal) resultMap.get("xj");

            }
            if (objects[3] == null)
                resultMap.put("xj", price);
            else
                resultMap.put("xj", price.add((BigDecimal) objects[3]));
        }
        return resultList;
    }

    /**
     * 导出使用单位报表的函数
     *
     * @param list
     * @param name
     * @param titleList
     * @param response
     */

    public void exportExcel(List<Map> list, String name, String[] titleList,
                            HttpServletResponse response) {

        try {
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes("GBK"), "ISO8859-1"));
            // 创建新的Excel 工作簿  
            HSSFWorkbook workbook = new HSSFWorkbook();

            HSSFSheet sheet = workbook.createSheet();

            // 创建第一行并设置标题
            HSSFRow titleRow = sheet.createRow(0);
            for (int i = 0; i < titleList.length; i++) {
                addTitle(titleRow,workbook,titleList[i],i);
            }

            // 创建单元格并为单元格赋值
            int rowNum = 1;
            for (Map map : list) {
                HSSFRow row =  sheet.createRow(rowNum);
                row.createCell(0).setCellValue(rowNum);
                row.getCell(0).setCellStyle(getDefaultCellStyle(workbook));
                row.createCell(1).setCellValue((String)map.get("company")==null ? "" : (String) map.get("company"));
                row.getCell(1).setCellStyle(getDefaultCellStyle(workbook));
                row.createCell(2).setCellValue((String)map.get("houseType")==null?"" : (String) map.get("houseType"));
                row.getCell(2).setCellStyle(getDefaultCellStyle(workbook));
                int cellNum = 3;
                for (BigDecimal line : (ArrayList<BigDecimal>)map.get("line")) {
                    row.createCell(cellNum).setCellValue(line==null ? 0d : line.doubleValue());
                    row.getCell(cellNum).setCellStyle(getNumbericCellStyle(workbook));
                    cellNum++;
                }
                row.createCell(cellNum).setCellValue((BigDecimal)map.get("xj")==null?0d:((BigDecimal)map.get("xj")).doubleValue());
                row.getCell(cellNum).setCellStyle(getNumbericCellStyle(workbook));
                rowNum++;
            }
            OutputStream out = response.getOutputStream();
            workbook.write(out);

            out.flush();
            out.close();

        } catch (Exception e) {
            System.out.println("Exception：" + e);
        }
    }

    private void addTitle(HSSFRow row,HSSFWorkbook workbook, String title,int cellNum) {
        row.createCell(cellNum).setCellValue(title);
        row.getCell(cellNum).setCellStyle(getTitleStyle(workbook));
        row.setHeight((short) 500);
    }

    private HSSFCellStyle getTitleStyle(HSSFWorkbook workbook) {

        if (titleCss == null) {
            titleCss = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 9);
            font.setFontName("宋体");
            font.setColor(HSSFColor.BLACK.index);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            titleCss.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            titleCss.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            titleCss.setFont(font); //调用字体样式对象

            titleCss.setWrapText(true);
        }


        return titleCss;
    }

    private HSSFCellStyle getNumbericCellStyle(HSSFWorkbook workbook) {
        if (numbericCss == null) {
            numbericCss = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 9);
            font.setFontName("宋体");
            font.setColor(HSSFColor.BLACK.index);
            font.setBoldweight((short) 0.8);
            numbericCss.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
            numbericCss.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            numbericCss.setFont(font); //调用字体样式对象

            numbericCss.setWrapText(true);
            HSSFDataFormat format = workbook.createDataFormat();
            numbericCss.setDataFormat(format.getFormat("#,##0.00"));
        }


        return numbericCss;

    }

    private HSSFCellStyle getDefaultCellStyle(HSSFWorkbook workbook) {

        if (defaultCss == null) {
            defaultCss = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 9);
            font.setFontName("宋体");
            font.setColor(HSSFColor.BLACK.index);
            font.setBoldweight((short) 0.8);
            defaultCss.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            defaultCss.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            defaultCss.setFont(font); //调用字体样式对象

            defaultCss.setWrapText(true);
        }


        return defaultCss;
    }

    /**
     * 房屋数据报表
     *
     * @param list
     * @param name
     * @param title
     */
    public void exportExcel(List<HouseAsset> list, String name, String[] title, Integer rowNumber) {

        try {
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes("GBK"), "ISO8859-1"));
            // 创建新的Excel 工作簿  
            HSSFWorkbook workbook = new HSSFWorkbook();

            HSSFSheet sheet = workbook.createSheet();

            for (int i = 0; i < title.length; i++) {
                sheet.autoSizeColumn((short) i);
            }

            // 创建第一行并设置标题
            HSSFRow titleRow = sheet.createRow(0);
            for (int i = 0; i < title.length; i++) {
                titleRow.createCell(i).setCellValue(title[i]);
            }
            // 创建单元格并为单元格赋值
            int rowIndex = 0;
            for (int i = 0; i < list.size(); i++) {
                HouseAsset houseAsset = list.get(i);
                List<AreaInfo> areaInfos = houseAsset.getAreaInfos();
                Integer size = areaInfos.size();

                if (size == 0) {
                    HSSFRow row = sheet.createRow(++rowIndex);
                    row.createCell(0).setCellValue(houseAsset.getLine() == null ? "" : houseAsset.getLine());
                    
                    row.createCell(1).setCellValue(houseAsset.getStation() == null ? "" : houseAsset.getStation());
                    row.createCell(2).setCellValue(houseAsset.getAssetName() == null ? "" : houseAsset.getAssetName());
                    row.createCell(3).setCellValue(houseAsset.getLocationNo() == null ? "" : houseAsset.getLocationNo());
                    row.createCell(4).setCellValue(houseAsset.getGroundFloor() == null ? "" : houseAsset.getGroundFloor()
                            + "/" + (houseAsset.getUndergroundFloor() == null ? "" : houseAsset.getUndergroundFloor()));
                    String no = houseAsset.getPropertyRightNo() != null ? houseAsset.getPropertyRightNo()
                            : (houseAsset.getCompletedLicense() != null ? houseAsset.getCompletedLicense() : houseAsset.getPlanLicense());
                    row.createCell(5).setCellValue(no == null ? "" : no);
                } else {
                    for (int j = 0; j < size; j++) {
                        HSSFRow row = sheet.createRow(++rowIndex);
                        row.createCell(0).setCellValue(houseAsset.getLine() == null ? "" : houseAsset.getLine());
                        row.createCell(1).setCellValue(houseAsset.getBuilderProject() == null ? "" : houseAsset.getBuilderProject());
                        
                        row.createCell(2).setCellValue(houseAsset.getStation() == null ? "" : houseAsset.getStation());
                        row.createCell(3).setCellValue(houseAsset.getAssetName() == null ? "" : houseAsset.getAssetName());
                        //房屋分类  地址  
                        row.createCell(4).setCellValue(houseAsset.getHouseType() == null ? "" : houseAsset.getHouseType());
                        
                        row.createCell(5).setCellValue(houseAsset.getLocationName() == null ? "" : houseAsset.getLocationName());
                        
                        row.createCell(6).setCellValue(houseAsset.getBuildArea() == null ? "" : houseAsset.getBuildArea() + "");
                        row.createCell(7).setCellValue(houseAsset.getGroundFloor() == null ? "" : houseAsset.getGroundFloor()
                                + "/" + (houseAsset.getUndergroundFloor() == null ? "" : houseAsset.getUndergroundFloor()));
                        String no = houseAsset.getPropertyRightNo() != null ? houseAsset.getPropertyRightNo()
                                : (houseAsset.getCompletedLicense() != null ? houseAsset.getCompletedLicense() : houseAsset.getPlanLicense());
                        row.createCell(8).setCellValue(no == null ? "" : no);
                       
                        AreaInfo areaInfo = areaInfos.get(j);
                        row.createCell(9).setCellValue(areaInfo.getUseType() == null ? "" : areaInfo.getUseType());
                        row.createCell(10).setCellValue(areaInfo.getTakeOverDep() == null ? "" : areaInfo.getTakeOverDep());                  
                        row.createCell(11).setCellValue(areaInfo.getReallyArea() == null ? "" : areaInfo.getReallyArea() + "");
                        
                        row.createCell(12).setCellValue(areaInfo.getDetail() == null ? "" : areaInfo.getDetail()); 
                        
                        row.createCell(13).setCellValue(areaInfo.getInFloor() == null ? "" : areaInfo.getInFloor()); 
                        row.createCell(14).setCellValue(areaInfo.getNote() == null ? "" : areaInfo.getNote());
                    }
                }
            }

            OutputStream out = response.getOutputStream();
            workbook.write(out);

            out.flush();
            out.close();

        } catch (Exception e) {
            System.out.println("Exception：" + e);
        }
    }

}
