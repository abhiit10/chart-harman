package chartharman

import grails.converters.JSON
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.web.multipart.commons.CommonsMultipartFile


class UploadController {

    List colorCodes = ["#F0F8FF", "#FAEBD7", "#00FFFF", "#7FFFD4", "#F0FFFF", "#F5F5DC", "#FFE4C4", "#000000", "#FFEBCD",
                       "#0000FF", "#8A2BE2", "#A52A2A", "#DEB887", "#5F9EA0", "#7FFF00", "#D2691E", "#FF7F50", "#6495ED",
                       "#FFF8DC", "#DC143C", "#00FFFF", "#00008B", "#008B8B", "#B8860B", "#A9A9A9", "#A9A9A9", "#006400",
                       "#BDB76B", "#8B008B", "#556B2F", "#FF8C00", "#9932CC", "#8B0000", "#E9967A", "#8FBC8F", "#483D8B",
                       "#2F4F4F", "#000000", "#FFEBCD", "#0000FF", "#8A2BE2", "#A52A2A", "#DEB887", "#5F9EA0", "#7FFF00",
                       "#D2691E", "#FF7F50", "#6495ED", "#FF8C00", "#9932CC", "#8B0000", "#E9967A", "#8FBC8F", "#483D8B",
                       "#FFF8DC", "#DC143C", "#00FFFF", "#00008B", "#008B8B", "#B8860B", "#A9A9A9", "#A9A9A9", "#006400",
                       "#BDB76B", "#8B008B", "#556B2F", "#2F4F4F", "#000000", "#FFEBCD", "#0000FF", "#8A2BE2", "#A52A2A",
                       "#D2691E", "#FF7F50", "#6495ED", "#FF8C00", "#9932CC", "#8B0000", "#E9967A", "#8FBC8F", "#483D8B",
                       "#FFF8DC", "#DC143C", "#00FFFF", "#00008B", "#008B8B", "#B8860B", "#A9A9A9", "#A9A9A9", "#006400",
                       "#BDB76B", "#8B008B", "#556B2F", "#DEB887", "#5F9EA0", "#7FFF00", "#E9967A", "#8FBC8F", "#483D8B",
                       "#FFF8DC", "#DC143C", "#00FFFF", "#00008B", "#008B8B", "#B8860B", "#A9A9A9", "#A9A9A9", "#006400",
                       "#BDB76B", "#8B008B", "#556B2F", "#2F4F4F", "#000000", "#FFEBCD", "#0000FF", "#8A2BE2", "#A52A2A",
                       "#D2691E", "#FF7F50", "#6495ED", "#FF8C00", "#9932CC", "#8B0000", "#E9967A", "#8FBC8F", "#483D8B",
                       "#FFF8DC", "#DC143C", "#00FFFF", "#00008B", "#008B8B", "#B8860B", "#A9A9A9", "#A9A9A9", "#006400",
                       "#BDB76B", "#8B008B", "#556B2F", "#DEB887", "#5F9EA0", "#7FFF00", "#8A2BE2", "#A52A2A", "#DEB887",
                       "#D2691E", "#FF7F50", "#6495ED", "#FF8C00", "#9932CC", "#8B0000", "#E9967A", "#8FBC8F", "#483D8B",
                       "#FFF8DC", "#DC143C", "#00FFFF", "#00008B", "#008B8B", "#B8860B", "#A9A9A9", "#A9A9A9", "#006400",
                       "#BDB76B", "#8B008B", "#556B2F", "#2F4F4F", "#000000", "#FFEBCD", "#0000FF", "#8A2BE2", "#A52A2A",
                       "#D2691E", "#FF7F50", "#6495ED", "#FF8C00", "#9932CC", "#8B0000", "#E9967A", "#8FBC8F", "#483D8B",
                       "#FFF8DC", "#DC143C", "#00FFFF", "#00008B", "#008B8B", "#B8860B", "#A9A9A9", "#A9A9A9", "#006400",
                       "#BDB76B", "#8B008B", "#556B2F", "#DEB887", "#5F9EA0", "#7FFF00", "#E9967A", "#8FBC8F", "#483D8B",
                       "#FFF8DC", "#DC143C", "#00FFFF", "#00008B", "#008B8B", "#B8860B", "#A9A9A9", "#A9A9A9", "#006400",
                       "#BDB76B", "#8B008B", "#556B2F", "#2F4F4F", "#000000", "#FFEBCD", "#0000FF", "#8A2BE2", "#A52A2A",
                       "#D2691E", "#FF7F50", "#6495ED", "#FF8C00", "#9932CC", "#8B0000", "#E9967A", "#8FBC8F", "#483D8B",
                       "#FFF8DC", "#DC143C", "#00FFFF", "#00008B", "#008B8B", "#B8860B", "#A9A9A9", "#A9A9A9", "#006400",
                       "#BDB76B", "#8B008B", "#556B2F", "#DEB887", "#5F9EA0", "#7FFF00", "#5F9EA0", "#7FFF00",
    ]


    def index() {}

    /**
     *
     */
    def processData() {
        println "params::::" + params
        CommonsMultipartFile f = request.getFile('doc')

        if (f.isEmpty()) {
            println "f.isEmpty()::::" + f.isEmpty()
            flash.message = "Please Upload xls file."
            render(view: 'error')
            return
        }

        List contents = []
        Set statusFilter = []

        Set status = new HashSet()
        String filerData = params.filter ?: "Status."

        try {
            //Get the workbook instance for XLS file
            //HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(f.inputStream))

            Workbook workbook = WorkbookFactory.create(f.inputStream)
            //Get first sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0)

            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.rowIterator()

            List headerValues = new ArrayList()
            List rows = new ArrayList()


            rowIterator.eachWithIndex { s, i ->
                Map map = [:]
                Row row = sheet.getRow(i)
                row.eachWithIndex { Cell entry, int j ->

                    String cellValue = ""
                    Cell cell = sheet.getRow(i).getCell(j)

                    if (cell) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_BOOLEAN:
                                cellValue = cell.getStringCellValue()
                                break
                            case Cell.CELL_TYPE_NUMERIC:
                                cellValue = cell.getNumericCellValue()
                                break
                            case Cell.CELL_TYPE_STRING:
                                cellValue = cell.getStringCellValue()
                                break
                        }

                        if (i == 0) {
                            headerValues << cellValue
                        } else {
                            map << [(headerValues.get(j)): cellValue]

                            if (headerValues.get(j) == filerData) {
                                status << cellValue
                            }

                            if (headerValues.get(j) == "Status.") {
                                statusFilter << cellValue
                            }

                            if (headerValues.get(j) == "Employment Type") {
                                ChartConstant.empFilter << cellValue
                            }

                            if (headerValues.get(j) == "Grade [stHRsuite]") {
                                ChartConstant.gradeFilter << cellValue
                            }

                            if (headerValues.get(j) == "Country") {
                                ChartConstant.countryFilter << cellValue
                            }

                            if (headerValues.get(j) == "Onsite/Offshore") {
                                ChartConstant.onSiteFilter << cellValue
                            }

                            if (headerValues.get(j) == "Location") {
                                ChartConstant.locationFilter << cellValue
                            }

                            if (headerValues.get(j) == "Division (Including Horizontals)") {
                                ChartConstant.divisionFilter << cellValue
                            }

                            if (headerValues.get(j) == "SBU [stHRsuite]") {
                                ChartConstant.sbuFilter << cellValue
                            }

                            if (headerValues.get(j) == "Final BU") {
                                ChartConstant.finalFilter << cellValue
                            }

                            if (headerValues.get(j) == "BU [stHRSuite]") {
                                ChartConstant.buFilter << cellValue
                            }

                            if (headerValues.get(j) == "Home GOC [stHRsuite]") {
                                ChartConstant.homeFilter << cellValue
                            }

                            if (headerValues.get(j) == "Reporting Manager Name") {
                                ChartConstant.rManagerFilter << cellValue
                            }

                            if (headerValues.get(j) == "Home GOC Manager") {
                                ChartConstant.homeGocFilter << cellValue
                            }
                        }
                    }
                }

                rows << map
            }

            ChartConstant.SHEET_DATA = rows
            ChartConstant.STATUS_FILTER = statusFilter

            status.eachWithIndex { s, i ->
                Integer size = rows.findAll { it.get(filerData) == s }.size()
                Map statusMap = ["label": "${s}", value: size, color: colorCodes.get(i)]
                contents << statusMap
            }

            println "contents:::" + contents


        } catch (FileNotFoundException e) {
            e.printStackTrace()
        } catch (Exception e) {
            e.printStackTrace()
        }

        render(view: 'graph', model: ['content'     : contents as JSON, filerData: filerData, statusFilter: statusFilter,
                                      empFilter     : ChartConstant.empFilter,
                                      gradeFilter   : ChartConstant.gradeFilter,
                                      countryFilter : ChartConstant.countryFilter,
                                      onSiteFilter  : ChartConstant.onSiteFilter,
                                      divisionFilter: ChartConstant.divisionFilter,
                                      sbuFilter     : ChartConstant.sbuFilter,
                                      locationFilter: ChartConstant.locationFilter,
                                      homeFilter    : ChartConstant.homeFilter,
                                      rManagerFilter: ChartConstant.rManagerFilter,
                                      finalFilter   : ChartConstant.finalFilter,
                                      homeGocFilter : ChartConstant.homeGocFilter])
    }

    /**
     *
     * @return
     */
    def advanceFilter() {

        List contents = []
        println ":::::" + params
        println "SHEET_DATA:::::" + ChartConstant.SHEET_DATA


        ChartConstant.STATUS_FILTER.eachWithIndex { s, i ->

            Closure closure = {}
            if (params.emp && params.grade && params.onsite && params.division) {
                closure = {
                    it.get("Status.") == s &&
                            it.get("Employment Type") == params.emp &&
                            it.get("Grade [stHRsuite]") == params.grade &&
                            it.get("Onsite/Offshore") == params.onsite &&
                            it.get("Division (Including Horizontals)") == params.division
                }
            } else if (params.emp && params.grade && params.onsite) {
                closure = {
                    it.get("Status.") == s &&
                            it.get("Employment Type") == params.emp &&
                            it.get("Grade [stHRsuite]") == params.grade &&
                            it.get("Onsite/Offshore") == params.onsite
                }
            } else if (params.emp && params.grade) {
                closure = {
                    it.get("Status.") == s &&
                            it.get("Employment Type") == params.emp &&
                            it.get("Grade [stHRsuite]") == params.grade
                }
            } else if (params.emp) {
                closure = {
                    it.get("Status.") == s &&
                            it.get("Employment Type") == params.emp
                }
            } else {
                closure = {
                    it.get("Status.") == s
                }
            }

            println "closure::::" + closure
            Integer size = ChartConstant.SHEET_DATA.findAll(closure).size()
            Map statusMap = ["label": "${s}", value: size, color: colorCodes.get(i)]
            contents << statusMap
        }

        println "contents::::" + contents
        render(view: 'graph', model: ['content'     : contents as JSON, empFilter: ChartConstant.empFilter,
                                      gradeFilter   : ChartConstant.gradeFilter,
                                      countryFilter : ChartConstant.countryFilter,
                                      onSiteFilter  : ChartConstant.onSiteFilter,
                                      divisionFilter: ChartConstant.divisionFilter,
                                      sbuFilter     : ChartConstant.sbuFilter,
                                      locationFilter: ChartConstant.locationFilter,
                                      homeFilter    : ChartConstant.homeFilter,
                                      rManagerFilter: ChartConstant.rManagerFilter,
                                      finalFilter   : ChartConstant.finalFilter,
                                      homeGocFilter : ChartConstant.homeGocFilter,
                                      params        : params
        ])

    }
}
