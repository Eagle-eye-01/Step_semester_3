package week_7.assigment_problems;

public class ReportGenerator implements Exportable {
    private final String reportName;

    public ReportGenerator(String reportName) {
        if (reportName == null || reportName.trim().isEmpty()) {
            throw new IllegalArgumentException("reportName cannot be blank");
        }
        this.reportName = reportName;
    }

    @Override
    public String exportData() {
        Exportable.recordExport();
        return "Exported report: " + reportName;
    }

    public String getReportName() {
        return reportName;
    }
}
